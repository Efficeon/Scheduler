package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by lion on 26.09.17.
 */

@Entity
@Table(name = "headers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestHeaderEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int headerId;

    @JoinColumn(name = "content_type")
    @JsonProperty("Content-Type")
    private String contentType;

    @JoinColumn(name = "accept")
    @JsonProperty("Accept")
    private String accept;

    @JoinColumn(name = "authorization")
    @JsonProperty("Authorization")
    private String authorization;

    public RequestHeaderEntity() {
    }

    public RequestHeaderEntity(int headerId, String contentType, String accept, String authorization) {
        this.headerId = headerId;
        this.contentType = contentType;
        this.accept = accept;
        this.authorization = authorization;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}


