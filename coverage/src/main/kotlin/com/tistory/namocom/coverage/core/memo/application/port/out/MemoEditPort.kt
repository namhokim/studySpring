package com.tistory.namocom.coverage.core.memo.application.port.out

import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo

fun interface MemoEditPort {
    fun editMemo(memo: EditMemo)
}
