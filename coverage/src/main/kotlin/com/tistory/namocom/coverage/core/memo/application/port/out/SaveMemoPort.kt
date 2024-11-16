package com.tistory.namocom.coverage.core.memo.application.port.out

import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId

fun interface SaveMemoPort {
    fun saveMemo(memo: NewMemo): MemoId
}
