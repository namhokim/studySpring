package com.tistory.namocom.coverage.core.memo.domain.model

import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import com.tistory.namocom.coverage.core.memo.domain.vo.UserId
import java.time.LocalDateTime

data class Memo(
    val id: MemoId,
    val writer: UserId,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
