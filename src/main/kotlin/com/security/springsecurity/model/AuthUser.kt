package com.security.springsecurity.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class AuthUser(
        @Id @GeneratedValue
        val id: Long?,
        val userName: String,
        val password: String,
        val roles: String
)