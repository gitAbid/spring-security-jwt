package com.security.springsecurity.utilities

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap


@Component
class TokenUtils {
    private val SECRET = "SignatureAlgorithm?MySecretKey"

    fun getToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims["user"] = userDetails.username
        claims["created"] = Date()
        claims["roles"] = userDetails.authorities.stream().map { it.authority }.collect(Collectors.joining(","))
        return generateToken(claims)
    }

    fun generateToken(claims: HashMap<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact()
    }
}