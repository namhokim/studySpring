package com.tistory.namocom.coverage.core.memo.application.port.`in`

import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo

fun interface MemoCreationUseCase {
    fun createMemo(memo: NewMemo): MemoId
}
