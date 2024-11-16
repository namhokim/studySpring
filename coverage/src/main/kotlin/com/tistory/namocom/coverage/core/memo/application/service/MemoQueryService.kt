package com.tistory.namocom.coverage.core.memo.application.service

import com.tistory.namocom.coverage.core.common.annotation.ApplicationService
import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoQueryUseCase
import com.tistory.namocom.coverage.core.memo.application.port.out.MemoQueryPort
import com.tistory.namocom.coverage.core.memo.domain.error.NotFoundMemoException
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId

@ApplicationService
class MemoQueryService(
    private val memoQueryPort: MemoQueryPort,
) : MemoQueryUseCase {

    override fun getMemo(id: MemoId): Memo = memoQueryPort.findMemo(id = id)
        ?: throw NotFoundMemoException(memoId = id)

}
