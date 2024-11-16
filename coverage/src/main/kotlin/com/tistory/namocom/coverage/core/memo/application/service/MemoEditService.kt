package com.tistory.namocom.coverage.core.memo.application.service

import com.tistory.namocom.coverage.core.common.annotation.ApplicationService
import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoEditUseCase
import com.tistory.namocom.coverage.core.memo.application.port.out.MemoEditPort
import com.tistory.namocom.coverage.core.memo.application.port.out.MemoQueryPort
import com.tistory.namocom.coverage.core.memo.domain.error.NotFoundMemoException
import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Suppress("SpellCheckingInspection")
class MemoEditService(
    private val memoQueryPort: MemoQueryPort,
    private val memoEditPort: MemoEditPort,
) : MemoEditUseCase {

    @Transactional
    override fun editMemo(memo: EditMemo) {
        val savedMemo: Memo = memoQueryPort.findMemo(memo.id) ?: throw NotFoundMemoException(memoId = memo.id)
        if (savedMemo.writer != memo.writer) {
            throw IllegalAccessException("작성자만 수정할 수 있습니다.")
        }
        memoEditPort.editMemo(memo = memo)
    }

}
