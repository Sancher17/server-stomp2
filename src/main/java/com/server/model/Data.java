package com.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private String qrcId;
    private String payload;
    private String status;
    private String code;
    private String message;
    private String trxId;
}
