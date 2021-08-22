package com.tistory.namocom.mvc.controller.parameter;

import lombok.Getter;

@Getter
public class VpnRequest {
    private final String requestNo;
    private final String memberNo;

    public VpnRequest(String requestNo, String memberNo) {
        this.requestNo = requestNo;
        this.memberNo = memberNo;
    }
}
