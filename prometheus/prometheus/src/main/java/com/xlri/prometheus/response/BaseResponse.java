package com.xlri.prometheus.response;

import lombok.Data;

@Data
public class BaseResponse {

    private int resultCode;
    private String message;
}
