package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lion on 04.10.17.
 */

@Entity
@Table(name = "results")
public class ResultJobEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    @JoinColumn(name = "result_id")
    private int resultId;

    @JsonIgnore
    @JoinColumn(name = "time_execution")
    private Date timeExecution;

    @JsonProperty("body")
    private String body;

    @JsonProperty("code")
    private int code;

    public ResultJobEntity() {
    }

    public ResultJobEntity(int resultId, Date timeExecution, String body, int code) {
        this.resultId = resultId;
        this.timeExecution = timeExecution;
        this.body = body;
        this.code = code;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public Date getTimeExecution() {
        return timeExecution;
    }

    public void setTimeExecution(Date timeExecution) {
        this.timeExecution = timeExecution;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
