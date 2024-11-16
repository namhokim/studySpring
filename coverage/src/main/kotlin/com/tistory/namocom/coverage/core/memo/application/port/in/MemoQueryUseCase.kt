package com.tistory.namocom.coverage.core.memo.application.port.`in`

import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId

fun interface MemoQueryUseCase {
    fun getMemo(id: MemoId): Memo
}
