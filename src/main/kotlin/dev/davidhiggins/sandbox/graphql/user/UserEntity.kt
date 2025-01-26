package dev.davidhiggins.sandbox.graphql.user

import dev.davidhiggins.sandbox.graphql.task.TaskEntity
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types

@Entity(name = "user")
data class UserEntity(
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    val id: String,
    val name: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    val tasks: List<TaskEntity>
)