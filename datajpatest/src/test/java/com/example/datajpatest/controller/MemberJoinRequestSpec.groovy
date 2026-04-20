package com.example.datajpatest.controller

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class MemberJoinRequestSpec extends Specification {

    @Subject
    private MemberJoinRequest sut

    @Shared
    private Validator validator

    def setupSpec() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def setup() {
        sut = getNoConstraintValidationMemberJoinRequest()
    }

    @Unroll
    def "사업자 등록번호가 #testBusinessNumber 를 허용한다."() {
        given:
        sut.setBusinessNumber(testBusinessNumber)

        when:
        Set<ConstraintViolation<MemberJoinRequest>> constraintViolations = validator.validate(sut)

        then:
        noConstraintViolation(constraintViolations)

        where:
        testBusinessNumber << ['1448125784', '144-81-25784']
    }

    def "사업자 등록번호는 null 을 허용하지 않는다."() {
        given:
        sut.setBusinessNumber(null)

        when:
        Set<ConstraintViolation<MemberJoinRequest>> constraintViolations = validator.validate(sut)

        then:
        constraintViolations.size() == 1
        with(constraintViolations[0]) {
            propertyPath.toString() == 'businessNumber'
            invalidValue == null
            messageTemplate == '{javax.validation.constraints.NotNull.message}'
        }
    }

    @Unroll
    def "사업자 등록번호는 #testBusinessNumber.length() 자리를 허용하지 않는다."() {
        given:
        sut.setBusinessNumber(testBusinessNumber)

        when:
        Set<ConstraintViolation<MemberJoinRequest>> constraintViolations = validator.validate(sut)

        then:
        constraintViolations.size() == 1
        with(constraintViolations[0]) {
            propertyPath.toString() == 'businessNumber'
            invalidValue.toString() == testBusinessNumber
            message == '10자리의 숫자 또는 대시를 포함한 12자리의 숫자만 입력가능합니다.'
        }

        where:
        testBusinessNumber << ['', '1', '123456789', '12345678901', '1234567890123', '12345678901234']
    }

    @Unroll
    def "사업자 등록번호는 문자열 #testBusinessNumber (#testBusinessNumber.length()) 을 허용하지 않는다."() {
        given:
        sut.setBusinessNumber(testBusinessNumber)

        when:
        Set<ConstraintViolation<MemberJoinRequest>> constraintViolations = validator.validate(sut)

        then:
        constraintViolations.size() == 1
        with(constraintViolations[0]) {
            propertyPath.toString() == 'businessNumber'
            invalidValue.toString() == testBusinessNumber
            message == '10자리의 숫자 또는 대시를 포함한 12자리의 숫자만 입력가능합니다.'
        }

        where:
        testBusinessNumber << ['apple', 'tablelands']
    }


    static def getNoConstraintValidationMemberJoinRequest() {
        def req = new MemberJoinRequest()
        req.id = 1
        req.name = "테스터"
        req.businessNumber = "1234567890"
        return req
    }

    static def noConstraintViolation(Set<ConstraintViolation<MemberJoinRequest>> constraintViolations) {
        constraintViolations.isEmpty()
    }

}
