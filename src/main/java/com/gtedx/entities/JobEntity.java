package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lion on 25.09.17.
 */
@Entity
@Table(name = "jobs")
public class JobEntity {

    @JsonProperty("task")
    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @Id
    @GeneratedValue
    @JsonProperty("job_id")
    private int id;

    @JsonProperty("scheduled_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date scheduledAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date start_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date end_time;

    private int execute_times;

    private String type;

    private String timezone;

    @JsonProperty("callback_url")
    private String callbackUrl;

    public JobEntity() {}

    public JobEntity(int jobId, Date scheduledAt,
                     Date start_time, Date end_time, int execute_times,
                     String type, String timezone, String callbackUrl) {
        this.id = jobId;
        this.scheduledAt = scheduledAt;
        this.start_time = start_time;
        this.end_time = end_time;
        this.execute_times = execute_times;
        this.type = type;
        this.timezone = timezone;
        this.callbackUrl = callbackUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Date scheduledAt) {
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
}
