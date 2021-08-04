package com.example.datajpatest.exception;

import javax.annotation.Nonnull;

import static java.text.MessageFormat.format;

public class MemberNotFoundException extends ApiBaseException {
    public MemberNotFoundException(@Nonnull Long memberId) {
        super(ExceptionCode.MEMBER_NOT_FOUND, format("Member ID {0} was not found.", memberId));
    }
}
