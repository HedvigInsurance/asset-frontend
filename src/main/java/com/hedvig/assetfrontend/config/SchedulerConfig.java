package com.hedvig.assetfrontend.config;

import com.hedvig.assetfrontend.services.assettracker.AssetTrackerJob;
import com.hedvig.assetfrontend.services.assettracker.AssetTrackerService;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;


@Configuration
public class SchedulerConfig {

    @Value("${job.interval}")
    private int interval;

    @Autowired
    private AssetTrackerService service;

    public static final String ASSET_TRACKER_SERVICE_VAR = "service";

    @Bean("assetTrackerJob")
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(AssetTrackerJob.class);
        jobDetailFactory.setDurability(true);

        JobDataMap data = new JobDataMap();
        data.put(ASSET_TRACKER_SERVICE_VAR, service);
        jobDetailFactory.setJobDataMap(data);

        return jobDetailFactory;
    }

    @Bean("assetTrackerTrigger")
    public SimpleTriggerFactoryBean trigger(@Qualifier("assetTrackerJob") JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(interval);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean scheduler(@Qualifier("assetTrackerTrigger") Trigger trigger,
                                          @Qualifier("assetTrackerJob") JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(new SpringBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

}
