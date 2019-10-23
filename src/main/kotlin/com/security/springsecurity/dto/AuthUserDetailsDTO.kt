package com.security.springsecurity.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class AuthUserDetailsDTO(var authUserName: String, var authPassword: String, var roles: String) : UserDetails {
    override fun getUsername() = authUserName
    override fun getPassword() = authPassword
    override fun isEnabled() = true;
    override fun isCredentialsNonExpired() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            AuthorityUtils.commaSeparatedStringToAuthorityList(roles)
}