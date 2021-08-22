package com.tistory.namocom.mvc.controller;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import com.tistory.namocom.mvc.controller.parameter.VpnResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VPNController {

    @GetMapping(value = "/vpn/{requestNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VpnResult getVpn(@PathVariable("requestNo") VpnRequest vpnRequest) {
        return new VpnResult(vpnRequest);
    }

}
