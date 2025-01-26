package dev.davidhiggins.sandbox.graphql.task

import dev.davidhiggins.sandbox.graphql.user.User
import dev.davidhiggins.sandbox.graphql.user.UserEntity
import graphql.language.Description
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID

@Entity(name = "task")
data class TaskEntity(
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    val id: String,
    val title: String,
    val description: String,
    @Enumerated(EnumType.STRING)
    val importance: Importance,
    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserEntity
)

enum class Importance {
    LOW, MEDIUM, HIGH
}