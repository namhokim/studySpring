package com.tistory.namocom.coverage.app.memo.adapter.out.persistence

import com.tistory.namocom.coverage.core.common.entity.Identifiable
import com.tistory.namocom.coverage.core.memo.domain.model.EditMemo
import com.tistory.namocom.coverage.core.memo.domain.model.Memo
import com.tistory.namocom.coverage.core.memo.domain.model.NewMemo
import com.tistory.namocom.coverage.core.memo.domain.vo.MemoId
import com.tistory.namocom.coverage.core.memo.domain.vo.UserId
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "memo")
data class MemoJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val writer: Long,

    @Column(nullable = false, length = 255)
    val title: String,

    @Column(nullable = false, length = 1024)
    val content: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime,
) {

    fun toDomain(): Memo {
        return Memo(
            id = MemoId(value = id),
            writer = UserId(value = writer),
            title = title,
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun update(memo: EditMemo): MemoJpaEntity = copy(
        title = memo.title,
        content = memo.content,
        updatedAt = LocalDateTime.now(),
    )

}

fun NewMemo.toJpaEntity() = LocalDateTime.now().let { now ->
    MemoJpaEntity(
        id = Identifiable.NEW_ID,
        writer = writer.value,
        title = title,
        content = content,
        createdAt = now,
        updatedAt = now,
    )
}
