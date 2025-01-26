package dev.davidhiggins.sandbox.graphql.user

import dev.davidhiggins.sandbox.graphql.NotFoundException
import dev.davidhiggins.sandbox.graphql.task.Task
import dev.davidhiggins.sandbox.graphql.task.TaskService
import graphql.ErrorType
import graphql.GraphQLError
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice

@Controller
class UserController(
    private val userService: UserService,
    private val taskService: TaskService
) {

    @QueryMapping
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @QueryMapping
    fun getUserById(@Argument id: String): User = userService.getUserById(id)
        ?: throw NotFoundException()

    @SchemaMapping
    fun tasks(user: User): List<Task> = taskService.getTasksByUserId(user.id)

    @MutationMapping
    fun createUser(@Argument("input") input: CreateUserInput): User = userService.createUser(input.name)
}

data class CreateUserInput(
        val name: String
)