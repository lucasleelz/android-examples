package com.lucaslz.photogallery;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/9/10.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class PollServiceWithJobScheduler extends JobService {

    private static final int JOB_ID = 1;

    private PollTask mCurrentTask;

    public static boolean hasBeenScheduler(Context context) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        boolean hasBeenScheduler = false;
        for (JobInfo jobInfo: scheduler.getAllPendingJobs()) {
            if (jobInfo.getId() == JOB_ID) {
                hasBeenScheduler = true;
                break;
            }
        }
        return hasBeenScheduler;
    }

    public static void setService(Context context) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo jobInfo = new JobInfo
                .Builder(JOB_ID, new ComponentName(context, PollServiceWithJobScheduler.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPeriodic(1000 * 60 * 15)
                .setPersisted(true)
                .build();
        scheduler.schedule(jobInfo);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        mCurrentTask = new PollTask();
        mCurrentTask.execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mCurrentTask != null) {
            mCurrentTask.cancel(true);
        }
        return true;
    }

    private class PollTask extends AsyncTask<JobParameters, Void, Void> {

        @Override
        protected Void doInBackground(JobParameters... jobParameters) {
            JobParameters jobParams = jobParameters[0];
            jobFinished(jobParams, false);
            return null;
        }
    }
}
