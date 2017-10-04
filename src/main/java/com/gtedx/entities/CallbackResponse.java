package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lion on 04.10.17.
 */
public class CallbackResponse {
    @JsonProperty("job_id")
    private int jobId;

    @JsonProperty("result")
    ResultJobEntity result;

    public CallbackResponse(int jobId, ResultJobEntity result) {
        this.jobId = jobId;
        this.result = result;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public ResultJobEntity getResult() {
        return result;
    }

    public void setResult(ResultJobEntity result) {
        this.result = result;
    }
}
