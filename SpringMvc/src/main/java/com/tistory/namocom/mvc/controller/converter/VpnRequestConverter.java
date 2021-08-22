package com.tistory.namocom.mvc.controller.converter;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import javax.inject.Named;

@Named
public class VpnRequestConverter implements Converter<String, VpnRequest> {

    @Override
    public VpnRequest convert(@Nonnull String source) {
        return new VpnRequest("namo", source);
    }
}
