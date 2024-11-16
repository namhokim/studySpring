package com.tistory.namocom.coverage.core.memo.application.port.out

import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId

fun interface MemoQueryPort {
    fun findMemo(id: MemoId): Memo?
}
