package com.security.springsecurity.controller

import com.security.springsecurity.model.UserCredential
import com.security.springsecurity.utilities.TokenUtils
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(val userDetailsService: UserDetailsService, val tokenUtils: TokenUtils) {

    @PostMapping
    fun authenticate(@RequestBody userCredential: UserCredential): String {
        return tokenUtils.getToken(userDetailsService.loadUserByUsername(userCredential.username))
    }
}