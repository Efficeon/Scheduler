package com.gtedx.service;

import com.gtedx.entities.JobEntity;

import com.gtedx.entities.Response;
import com.gtedx.exception.JobException;
import com.gtedx.repositories.JobsRepository;
import org.quartz.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by lion on 25.09.17.
 */
@Service
public class JobServiceImpl implements JobService {
    private final Scheduler scheduler;
    private final JobsRepository jobsRepository;

    public JobServiceImpl(Scheduler scheduler, JobsRepository jobsRepository) {
        this.scheduler = scheduler;
        this.jobsRepository = jobsRepository;
    }

    @Override
    public void startJob(int id) {
        JobEntity jobEntity = jobsRepository.findByJobId(id);
        JobDetail jobDetail = createJob(String.valueOf(jobEntity.getJobId()));

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .startAt(jobEntity.getStart_time())
                .endAt(jobEntity.getEnd_time())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getScheduledAt()))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            //sendCallback(jobEntity);
        } catch (SchedulerException e) {

            jobsRepository.save(jobEntity);
            throw new JobException(e);
        }
    }
    private JobDetail createJob(String id) {
        JobDataMap data = new JobDataMap();
        data.put("repository", jobsRepository);

        return JobBuilder
                .newJob(NotificationJob.class)
                .setJobData(data)
                .withIdentity(id)
                .build();
    }

    public static class NotificationJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
            int id = Integer.parseInt(jobExecutionContext.getJobDetail().getKey().getName());
            JobsRepository jobsRepository = (JobsRepository) data.get("repository");
            jobExecutionContext.getNextFireTime();
            JobEntity jobEntity = jobsRepository.findByJobId(id);

            try {
                CronExpression expression = new CronExpression(jobEntity.getScheduledAt());

                jobEntity.setLastRunAt(jobEntity.getNextRunAt());
                if (expression.getNextValidTimeAfter(new Date()).after(jobEntity.getEnd_time())){
                    jobEntity.setNextRunAt(null);
                } else {
                    jobEntity.setNextRunAt(expression.getNextValidTimeAfter(new Date()));
                }
                jobsRepository.save(jobEntity);

            } catch (RuntimeException e) {

                throw e;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendCallback(JobEntity jobEntity) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(jobEntity.getCallbackUrl(), new Response<>(new Response(jobEntity)), Object.class);
    }
}
