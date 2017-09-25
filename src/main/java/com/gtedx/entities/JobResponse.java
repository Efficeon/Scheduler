package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lion on 25.09.17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponse {

    @JsonProperty("job_id")
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
