package com.tistory.namocom.coverage.core.memo.domain.error

import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId

class NotFoundMemoException(
    private val memoId: MemoId,
) : RuntimeException() {
    override val message: String
        get() = "메모를 찾을 수 없습니다. ID: ${memoId.value}"
}
