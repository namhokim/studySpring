package com.tistory.namocom.coverage.app.memo.adapter.out.persistence

import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import com.tistory.namocom.coverage.core.memo.domain.vo.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.time.withConstantNow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@DataJpaTest
@Suppress("SpellCheckingInspection")
class MemoPersistenceAdapterSpec(
    private val memoRepository: MemoRepository,
) : FunSpec({

    val sut = MemoPersistenceAdapter(
        memoRepository = memoRepository,
    )
    val writer = UserId(value = 1004)

    test("저장하지 않은 메모 조회시 null 반환") {
        // given
        val notSavedMemoId = MemoId(value = 0)

        // when
        val savedMemo: Memo? = sut.findMemo(id = notSavedMemoId)

        // then
        savedMemo shouldBe null
    }

    test("saveMemo & findMemo") {
        // given
        val memo = NewMemo(writer = writer, title = "제목", content = "내용입니다.")

        // when
        val memoId: MemoId = sut.saveMemo(memo = memo)

        // then
        val savedMemo: Memo = sut.findMemo(id = memoId)!!
        with(savedMemo) {
            writer shouldBe writer
            title shouldBe "제목"
            content shouldBe "내용입니다."
        }
    }

    test("editMemo") {
        // given
        val memo = NewMemo(writer = writer, title = "제목", content = "내용입니다.")
        val memoId: MemoId = sut.saveMemo(memo = memo)

        // when
        val someLater: LocalDateTime = LocalDateTime.now().withHour(1)
        withConstantNow(someLater) {
            val edit = EditMemo(id = memoId, writer = writer, title = "수정된 제목", content = "수정된 내용입니다.")
            sut.editMemo(memo = edit)

            // then
            val memoJpaEntity: MemoJpaEntity = memoRepository.findById(memoId.value).get()
            with(memoJpaEntity) {
                this.writer shouldBe writer.value
                title shouldBe "수정된 제목"
                content shouldBe "수정된 내용입니다."
                createdAt shouldNotBe updatedAt
                updatedAt shouldBe someLater
            }
        }
    }

})
