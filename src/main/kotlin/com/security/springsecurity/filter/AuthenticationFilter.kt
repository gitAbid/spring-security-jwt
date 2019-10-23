package com.security.springsecurity.filter

import com.security.springsecurity.utilities.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {

    private val TOKEN_HEADER = "Authorization"

    @Autowired
    lateinit var tokenUtils: TokenUtils

    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {

        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, $TOKEN_HEADER")

        val request = req as HttpServletRequest

        request.getHeader(TOKEN_HEADER)?.let {
            try {
                val userDetails = tokenUtils.getUserFromToken(it)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e: Exception) {
                e.printStackTrace()
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid")
            }
        }
        chain.doFilter(request, response)

    }
}