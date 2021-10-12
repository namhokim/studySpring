package com.tistory.namocom.mvc.controller.converter;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import javax.inject.Named;
import java.util.UUID;

//@Named
@Slf4j
public class VpnRequestConverter implements Converter<String, VpnRequest> {

    @Override
    public VpnRequest convert(@Nonnull String source) {
        final UUID uuid = UUID.randomUUID();
        try {
            log.info("[{}] Convert {} to VpnRequest", uuid, source);
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[{}] Convert completed {} to VpnRequest", uuid, source);
        return new VpnRequest(source);
    }
}
