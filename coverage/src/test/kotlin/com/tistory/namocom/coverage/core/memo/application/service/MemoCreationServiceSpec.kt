package com.tistory.namocom.coverage.core.memo.application.service

import com.tistory.namocom.coverage.KotlinFixture.fixture
import com.tistory.namocom.coverage.core.memo.application.port.out.SaveMemoPort
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class MemoCreationServiceSpec : FunSpec({

    val saveMemoPort: SaveMemoPort = mockk()
    val sut = MemoCreationService(saveMemoPort = saveMemoPort)

    test("createMemo") {
        // given
        val memo: NewMemo = fixture()
        val memoSlot: CapturingSlot<NewMemo> = slot()
        val memoId = MemoId(value = 1)
        every { saveMemoPort.saveMemo(memo = capture(memoSlot)) } returns memoId

        // when
        val result: MemoId = sut.createMemo(memo = memo)

        // then
        result shouldBe memoId
    }

})
