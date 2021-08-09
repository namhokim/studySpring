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
    def "사업자 등록번호가 #testBusinessNumber.length() 자리이면 길이 제약조건 위반이 발생한다."() {
        given:
        sut.setBusinessNumber(testBusinessNumber)

        when:
        Set<ConstraintViolation<MemberJoinRequest>> constraintViolations = validator.validate(sut)

        then:
        constraintViolations.size() == 1
        with(constraintViolations[0]) {
            propertyPath.toString() == 'businessNumber'
            invalidValue.toString() == testBusinessNumber
            messageTemplate == '{org.hibernate.validator.constraints.Length.message}'
        }

        where:
        testBusinessNumber << ['', '1', '123456789', '1234567890123', '12345678901234']
    }

    static def getNoConstraintValidationMemberJoinRequest() {
        def req = new MemberJoinRequest()
        req.id = 1
        req.name = "테스터"
        req.businessNumber = "1234567890"
        return req
    }

}
