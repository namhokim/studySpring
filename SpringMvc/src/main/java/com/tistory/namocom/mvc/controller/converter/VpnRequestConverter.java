package com.tistory.namocom.mvc.controller.converter;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VpnRequestConverter implements Converter<String, VpnRequest> {
    @Override
    public VpnRequest convert(String source) {
        return new VpnRequest("namo", source);
    }
}
