package com.tistory.namocom.coverage.app.memo.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository : JpaRepository<MemoJpaEntity, Long>
