package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
/**
 * Created by lion on 25.09.17.
 */

@Entity
@Table(name = "tasks")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    @JoinColumn(name = "task_id")
    private int taskId;

    @JsonProperty("method")
    @Pattern(regexp = "GET|POST|PUT|DELTE")
    private String method;

    @JsonProperty("url")
    private String url;

    @JsonProperty("data")
    private String data;

    @JsonProperty("headers")
    @OneToOne
    @JoinColumn(name = "header_id")
    private RequestHeaderEntity header;

    public TaskEntity() {
    }

    public TaskEntity(int taskId, String method, String url,
                      String data, RequestHeaderEntity header) {
        this.taskId = taskId;
        this.method = method;
        this.url = url;
        this.data = data;
        this.header = header;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RequestHeaderEntity getHeader() {
        return header;
    }

    public void setHeader(RequestHeaderEntity header) {
        this.header = header;
    }
}
