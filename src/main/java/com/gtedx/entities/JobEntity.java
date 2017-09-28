package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lion on 25.09.17.
 */
@Entity
@Table(name = "jobs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobEntity {

    @Id
    @GeneratedValue
    @JsonProperty("job_id")
    private int jobId;

    @JsonProperty("scheduled_at")
    private String scheduledAt;

    @JoinColumn(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;

    private int execute_times;

    private String type;

    @JsonProperty("time_zone")
    private String timezone;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("task")
    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @JsonProperty("next_run_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JoinColumn(name = "next_run_at")
    private Date nextRunAt;

    @JsonProperty("last_run_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JoinColumn(name = "last_run_at")
    private Date lastRunAt;

    public JobEntity() {}

    public JobEntity(int jobId, String scheduledAt, Date start_time, Date end_time,
                     int execute_times, String type, String timezone, String callbackUrl,
                     TaskEntity task, Date nextRunAt, Date lastRunAt) {
        this.jobId = jobId;
        this.scheduledAt = scheduledAt;
        this.start_time = start_time;
        this.end_time = end_time;
        this.execute_times = execute_times;
        this.type = type;
        this.timezone = timezone;
        this.callbackUrl = callbackUrl;
        this.task = task;
        this.nextRunAt = nextRunAt;
        this.lastRunAt = lastRunAt;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(String scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getExecute_times() {
        return execute_times;
    }

    public void setExecute_times(int execute_times) {
        this.execute_times = execute_times;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Date getNextRunAt() {
        return nextRunAt;
    }

    public void setNextRunAt(Date nextRunAt) {
        this.nextRunAt = nextRunAt;
    }

    public Date getLastRunAt() {
        return lastRunAt;
    }

    public void setLastRunAt(Date lastRunAt) {
        this.lastRunAt = lastRunAt;
    }
}
