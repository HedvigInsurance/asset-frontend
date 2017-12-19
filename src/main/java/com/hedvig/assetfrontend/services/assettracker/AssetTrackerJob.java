package com.hedvig.assetfrontend.services.assettracker;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class AssetTrackerJob extends QuartzJobBean {

    private AssetTracker tracker;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        tracker.findPendingAssets();
    }

    public void setTracker(AssetTracker tracker) {
        this.tracker = tracker;
    }
}
