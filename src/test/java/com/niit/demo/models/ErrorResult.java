package com.niit.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response
 */
public class ErrorResult {
    @JsonProperty("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
