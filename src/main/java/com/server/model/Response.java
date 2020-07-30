package com.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String code;
    private String message;
    private String result;
    private String description;
    private String transactionId;
    private com.server.model.Data data;
}
