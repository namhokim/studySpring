package com.tistory.namocom.coverage

import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoCreationUseCase
import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoEditUseCase
import com.tistory.namocom.coverage.core.memo.application.port.`in`.MemoQueryUseCase
import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import com.tistory.namocom.coverage.core.memo.domain.vo.UserId
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.transaction.support.TransactionTemplate

@Suppress("SpellCheckingInspection")
@SpringBootApplication
class CoverageApplication {

    @Profile("!test")
    @Bean
    fun run(
        memoCreationUseCase: MemoCreationUseCase,
        memoQueryUseCase: MemoQueryUseCase,
        memoEditUseCase: MemoEditUseCase,
        transactionTemplate: TransactionTemplate,
    ) = CommandLineRunner {
        transactionTemplate.execute {
            val writer = UserId(value = 1004)
            val memo = NewMemo(writer = writer, title = "제목", content = "내용입니다.")
            val memoId: MemoId = memoCreationUseCase.createMemo(memo = memo)

            val savedMemo1: Memo = memoQueryUseCase.getMemo(id = memoId)
            println(savedMemo1)

            val editedMemo = EditMemo(id = memoId, writer = writer, title = "수정된 제목", content = "수정된 내용입니다.")
            memoEditUseCase.editMemo(memo = editedMemo)

            val savedMemo2: Memo = memoQueryUseCase.getMemo(id = memoId)
            println(savedMemo2)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<CoverageApplication>(*args)
}
