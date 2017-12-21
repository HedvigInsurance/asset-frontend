package com.hedvig.assetfrontend.services.assettracker;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class AssetTrackerJob extends QuartzJobBean {

    private AssetTrackerServiceImpl service;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        service.loadPendingAssetsFromTracker();
    }

    public void setService(AssetTrackerServiceImpl service) {
        this.service = service;
    }
}
