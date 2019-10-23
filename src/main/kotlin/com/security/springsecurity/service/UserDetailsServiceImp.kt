package com.security.springsecurity.service

import com.security.springsecurity.dto.AuthUserDetailsDTO
import com.security.springsecurity.repository.AuthUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service("userDetailsService")
class UserDetailsServiceImp(@Autowired val repository: AuthUserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        repository.findByUserName(username)?.let {
            return AuthUserDetailsDTO(authUserName = it.userName, authPassword = it.password, roles = it.roles)
        } ?: throw Throwable("User not found with username : $username")
    }
}