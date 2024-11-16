package com.tistory.namocom.coverage.core.memo.application.service

import com.tistory.namocom.coverage.core.common.annotation.ApplicationService
import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoCreationUseCase
import com.tistory.namocom.coverage.core.memo.application.port.out.SaveMemoPort
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import org.springframework.transaction.annotation.Transactional

@ApplicationService
class MemoCreationService(
    private val saveMemoPort: SaveMemoPort,
): MemoCreationUseCase {

    @Transactional
    override fun createMemo(memo: NewMemo): MemoId = saveMemoPort.saveMemo(memo = memo)

}
