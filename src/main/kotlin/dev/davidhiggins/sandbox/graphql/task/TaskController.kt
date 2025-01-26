package dev.davidhiggins.sandbox.graphql.task

import dev.davidhiggins.sandbox.graphql.NotFoundException
import dev.davidhiggins.sandbox.graphql.user.User
import dev.davidhiggins.sandbox.graphql.user.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class TaskController(
    private val userService: UserService,
    private val taskService: TaskService
) {

    @QueryMapping
    fun getTasksByUser(@Argument userId: String): List<Task> = taskService.getTasksByUserId(userId)

    @SchemaMapping
    fun assignee(task: Task): User = userService.getUserById(task.assignee) ?: throw NotFoundException("User not found")

    @MutationMapping
    fun createTask(@Argument input: TaskInput): Task = taskService.createTask(
            userId = input.userId,
            title = input.title,
            description = input.description,
            importance = input.importance
    )
}


data class TaskInput(
    val userId: String,
    val title: String,
    val description: String,
    val importance: Importance
)