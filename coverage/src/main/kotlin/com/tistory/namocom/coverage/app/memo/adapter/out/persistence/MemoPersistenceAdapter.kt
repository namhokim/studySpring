package com.tistory.namocom.coverage.app.memo.adapter.out.persistence

import com.tistory.namocom.coverage.core.memo.application.port.out.MemoEditPort
import com.tistory.namocom.coverage.core.memo.application.port.out.MemoQueryPort
import com.tistory.namocom.coverage.core.memo.application.port.out.SaveMemoPort
import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MemoPersistenceAdapter(
    private val memoRepository: MemoRepository,
) : SaveMemoPort, MemoQueryPort, MemoEditPort {

    override fun saveMemo(memo: NewMemo): MemoId {
        val newMemo: MemoJpaEntity = memo.toJpaEntity()
        val savedMemo: MemoJpaEntity = memoRepository.save(newMemo)
        return MemoId(value = savedMemo.id)
    }

    override fun findMemo(id: MemoId): Memo? =
        memoRepository.findByIdOrNull(id.value)
            ?.toDomain()

    override fun editMemo(memo: EditMemo) =
        memoRepository.findById(memo.id.value)
            .ifPresent { memoRepository.save(it.update(memo)) }

}
