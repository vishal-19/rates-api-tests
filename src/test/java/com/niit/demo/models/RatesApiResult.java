package com.niit.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Api Response
 */
@JsonRootName("ratesApiResult")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RatesApiResult {

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private RatesResult ratesResult;

    @JsonProperty("date")
    private String date;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public RatesResult getRatesResult() {
        return ratesResult;
    }

    public void setRatesResult(RatesResult ratesResult) {
        this.ratesResult = ratesResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
