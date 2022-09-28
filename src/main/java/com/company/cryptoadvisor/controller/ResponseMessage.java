package com.company.cryptoadvisor.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseMessage {

    private String messege;

    private Object responce;

}
