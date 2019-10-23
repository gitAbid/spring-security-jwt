package com.security.springsecurity.repository

import com.security.springsecurity.model.AuthUser
import org.springframework.data.jpa.repository.JpaRepository


interface AuthUserRepository : JpaRepository<AuthUser, Long> {
    fun findByUserName(userName: String): AuthUser?
}