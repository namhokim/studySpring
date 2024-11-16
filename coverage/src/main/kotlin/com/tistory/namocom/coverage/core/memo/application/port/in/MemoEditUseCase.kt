package com.tistory.namocom.coverage.core.memo.application.port.`in`

import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo

fun interface MemoEditUseCase {
    fun editMemo(memo: EditMemo)
}
