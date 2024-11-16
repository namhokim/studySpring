package com.tistory.namocom.coverage.core.memo.application.service

import com.tistory.namocom.coverage.KotlinFixture.fixture
import com.tistory.namocom.coverage.core.memo.application.port.out.MemoQueryPort
import com.tistory.namocom.coverage.core.memo.domain.error.NotFoundMemoException
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

@Suppress("SpellCheckingInspection")
class MemoQueryServiceSpec : FunSpec({

    val memoQueryPort: MemoQueryPort = mockk()
    val sut = MemoQueryService(memoQueryPort = memoQueryPort)

    context("getMemo") {
        test("메모를 찾을 수 없으면 예외가 발생한다.") {
            // given
            val memoId = MemoId(value = 1)
            every { memoQueryPort.findMemo(id = memoId) } returns null

            // when & then
            val ex = shouldThrow<NotFoundMemoException> {
                sut.getMemo(id = memoId)
            }
            ex.message shouldBe "메모를 찾을 수 없습니다. ID: 1"
        }

        test("메모를 찾을 수 있으면 메모를 반환한다.") {
            // given
            val memoId = MemoId(value = 2)
            val memo: Memo = fixture<Memo>().copy(id = memoId)
            every { memoQueryPort.findMemo(id = memoId) } returns memo

            // when
            val result = sut.getMemo(id = memoId)

            // then
            result shouldBe memo
        }
    }

})
