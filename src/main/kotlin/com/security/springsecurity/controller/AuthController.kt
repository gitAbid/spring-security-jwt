package com.security.springsecurity.controller

import com.security.springsecurity.dto.UserCredentialDTO
import com.security.springsecurity.utilities.TokenUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(val authenticationManager: AuthenticationManager, val userDetailsService: UserDetailsService, val tokenUtils: TokenUtils) {

    @PostMapping
    fun authenticate(@RequestBody userCredentialDTO: UserCredentialDTO): String {
        SecurityContextHolder.getContext().authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userCredentialDTO.username, userCredentialDTO.password))
        return tokenUtils.getToken(userDetailsService.loadUserByUsername(userCredentialDTO.username))
    }
}