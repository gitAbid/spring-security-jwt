package com.security.springsecurity.utilities

import com.security.springsecurity.dto.AuthUserDetailsDTO
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@Component
class TokenUtils {
    private val SECRET = "SignatureAlgorithm?MySecretKey"

    fun getToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims["sub"] = userDetails.username
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

    fun getUserFromToken(token: String): UserDetails {
        return AuthUserDetailsDTO(authUserName = getUserNameFromToken(token),
                authPassword = "",
                roles = getClaimsFromToken(token)["roles"] as String
        )
    }

    private fun getClaimsFromToken(token: String): Claims = Jwts
            .parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .body?:throw RuntimeException("Unable to parse claim from token string")

    fun getUserNameFromToken(token: String): String {
        return getClaimsFromToken(token).subject ?:throw RuntimeException("Unable to parse username from token")
    }
}