package com.xlri.prometheus.response;

import lombok.Data;

@Data
public class UserResponse extends BaseResponse {
    private String username;
    private String password;
}
