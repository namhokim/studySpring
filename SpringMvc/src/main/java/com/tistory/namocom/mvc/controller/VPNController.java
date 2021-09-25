package com.tistory.namocom.mvc.controller;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import com.tistory.namocom.mvc.controller.parameter.VpnResult;
import com.tistory.namocom.mvc.external.FailingRiskClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VPNController {

    private final FailingRiskClient failingRiskClient;

    public VPNController(FailingRiskClient failingRiskClient) {
        this.failingRiskClient = failingRiskClient;
    }

    @GetMapping(value = "/vpn/{requestNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VpnResult getVpn(@PathVariable("requestNo") VpnRequest vpnRequest) {
        return new VpnResult(vpnRequest);
    }

    @GetMapping("/evaluate")
    public String evaluate() {
        return failingRiskClient.evaluate();
    }

}
