package com.example.datajpatest.repository

import com.example.datajpatest.model.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

@DataJpaTest
class MemberRepositoryIntegrationSpec extends Specification {

    @Autowired
    private MemberRepository memberRepository

    @Autowired
    private JdbcTemplate jdbcTemplate

    def "createTest1"() {
        given:
        final Long memberIdParam = 1L
        final String memberNameParam = "tester 1"
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam)

        when:
        final MemberJpaEntity saved = memberRepository.save(entity)
        final MemberJpaEntity loadedById = memberRepository.findById(saved.getId()).orElseThrow()

        then:
        loadedById.getName() == memberNameParam
    }

    def "createTest2"() {
        given:
        final Long memberIdParam = 2L
        final String memberNameParam = "tester 2"
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam)

        when:
        final MemberJpaEntity saved = memberRepository.save(entity)
        memberRepository.flush()
        final String sql = "select ID as id, NAME as name from MEMBER where id = ?"
        final Member member = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new SimpleMember(rs.getLong("id"),
                rs.getString("name")), saved.getId())

        then:
        member.getName() == memberNameParam
    }

    def "createTest3"() {
        given:
        final Long memberIdParam = 3L
        final String memberNameParam = "tester 2"
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam)

        when:
        final MemberJpaEntity saved = memberRepository.save(entity)
        memberRepository.flush()
        final String sql = "select ID as id, NAME as name from MEMBER where id = ?"
        final Member member = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new SimpleMember(rs.getLong("id"),
                        rs.getString("name")), saved.getId())

        then:
        member.getName() == memberNameParam
    }

    def createBy(Long memberId, String memberName) {
        Member member = Mock()
        member.getId() >> memberId
        member.getName() >> memberName
        return MemberJpaEntity.from(member)
    }

    private static class SimpleMember implements Member {
        private final Long id
        private final String name

        SimpleMember(Long id, String name) {
            this.id = id
            this.name = name
        }

        Long getId() {
            return id
        }

        String getName() {
            return name
        }
    }
}
