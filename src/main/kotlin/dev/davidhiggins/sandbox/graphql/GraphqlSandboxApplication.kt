package dev.davidhiggins.sandbox.graphql

import graphql.ErrorType
import graphql.GraphQLError
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice

@SpringBootApplication
class GraphqlSandboxApplication

fun main(args: Array<String>) {
	runApplication<GraphqlSandboxApplication>(*args)
}

@ControllerAdvice
class GraphQlExceptionHandler {
	@GraphQlExceptionHandler
	fun notFound(e: NotFoundException) =
			GraphQLError
					.newError()
					.errorType(ErrorType.DataFetchingException)
					.message("Not found")
					.build()

}

data class NotFoundException(val msg: String? = null): RuntimeException()
