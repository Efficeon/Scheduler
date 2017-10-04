package com.gtedx.service;

import com.gtedx.entities.JobEntity;
import com.gtedx.entities.Response;
import com.gtedx.entities.ResultJobEntity;
import com.gtedx.exception.JobException;
import com.gtedx.repositories.JobsRepository;
import org.quartz.*;
import org.springframework.http.*;
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
            if (jobEntity.getCallbackUrl() != null){
                sendCallback(jobEntity);
            }
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
            JobEntity jobEntity = jobsRepository.findByJobId(id);

            System.out.println("Task № "+jobEntity.getJobId()+" in progress...");
            try {
                CronExpression expression = new CronExpression(jobEntity.getScheduledAt());
                jobEntity.setLastRunAt(jobEntity.getNextRunAt());
                if (expression.getNextValidTimeAfter(new Date()).after(jobEntity.getEnd_time())){
                    jobEntity.setNextRunAt(null);
                } else {
                    jobEntity.setNextRunAt(expression.getNextValidTimeAfter(new Date()));
                }
                jobEntity.setResultJod(executionJob(jobEntity, HttpMethod.valueOf(jobEntity.getTask().getMethod())));

                jobsRepository.save(jobEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendCallback(JobEntity jobEntity) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(jobEntity.getCallbackUrl(), new Response<>(jobEntity), Object.class);
    }

    private static ResultJobEntity executionJob(JobEntity jobEntity, HttpMethod httpMethod){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        if (jobEntity.getTask().getHeader().getContentType() != null){
            headers.add("Content-Type", jobEntity.getTask().getHeader().getContentType());
        }
        if (jobEntity.getTask().getHeader().getAccept() != null){
            headers.add("Accept", jobEntity.getTask().getHeader().getAccept());
        }
        if (jobEntity.getTask().getHeader().getAuthorization() != null){
            headers.add("Authorization", jobEntity.getTask().getHeader().getAuthorization());
        }

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(jobEntity, headers);
        ResponseEntity response = restTemplate.exchange(jobEntity.getTask().getUrl(),
                                                        httpMethod,
                                                        requestEntity,
                                                        Object.class);
        ResultJobEntity resultJob = new ResultJobEntity();
        if (response.getBody() != null){
            resultJob.setBody(response.getBody().toString());
        }
        resultJob.setCode(response.getStatusCode().value());
        resultJob.setTimeExecution(jobEntity.getNextRunAt());

        return resultJob;
    }
}
