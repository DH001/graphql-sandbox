package dev.davidhiggins.sandbox.graphql.task

import dev.davidhiggins.sandbox.graphql.NotFoundException
import dev.davidhiggins.sandbox.graphql.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class TaskService(
        private val userRepository: UserRepository,
        private val taskRepository: TaskRepository
) {
    fun getTasksByUserId(userId: String): List<Task> {
        return userRepository.findById(userId).getOrNull()
                ?.tasks
                ?.map { it.toModel() }
                ?: emptyList()
    }

    fun createTask(userId: String, title: String, description: String, importance: Importance): Task {
        val user = userRepository.findById(userId).orElseThrow { NotFoundException("No user found") }
        return taskRepository.save(TaskEntity(
                user = user,
                id = UUID.randomUUID().toString(),
                importance = importance,
                title = title,
                description = description
        )).toModel()
    }
}

data class Task(
    val id: String,
    val assignee: String,
    val title: String,
    val description: String,
    val importance: Importance
)

fun TaskEntity.toModel() = Task(
        id = this.id,
        title = this.title,
        description = this.description,
        importance = this.importance,
        assignee = this.user.id
)

