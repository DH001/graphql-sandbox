package dev.davidhiggins.sandbox.graphql.user

import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
            .map { it.toModel() }
    }

    fun getUserById(id: String): User? {
        return userRepository.findById(id)
            .getOrNull()
            ?.toModel()
    }

    fun createUser(name: String): User {
        return userRepository.save(
            UserEntity(
                id = UUID.randomUUID().toString(),
                name = name,
                tasks = listOf()
            )
        ).toModel()
    }
}

data class User(
    val id: String,
    val name: String
)

fun UserEntity.toModel() = User(id = this.id, name = this.name)