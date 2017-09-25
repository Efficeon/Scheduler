package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
/**
 * Created by lion on 25.09.17.
 */

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @JsonProperty("method")
    @Pattern(regexp = "GET|POST|PUT|DELTE")
    private String method;

    @JsonProperty("url")
    private String url;

    @JsonProperty("data")
    private String data;
}
