package com.tistory.namocom.mvc.controller.parameter;

import lombok.Getter;

@Getter
public class VpnResult {
    private final String requestNo;

    public VpnResult(VpnRequest vpnRequest) {
        this.requestNo = vpnRequest.getRequestNo();
    }
}
