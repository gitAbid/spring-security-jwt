package com.security.springsecurity

import com.security.springsecurity.model.AuthUser
import com.security.springsecurity.repository.AuthUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class SpringSecurityApplication {
    @Bean
    fun initialize(authUserRepository: AuthUserRepository) = CommandLineRunner {
        authUserRepository.save(AuthUser(id = null,
                userName = "abid",
                password = BCryptPasswordEncoder().encode("123456"),
                roles = "SUPER_ADMIN,ADMIN"))
    }
}

fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args)
}
