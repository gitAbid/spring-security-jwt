package com.security.springsecurity.filter

import com.security.springsecurity.utilities.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {

    private val TOKEN_HEADER = "Authorization"

    @Autowired
    val tokenUtils=TokenUtils()

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, chain: FilterChain?) {
//        val resp = res as HttpServletResponse
//        resp.setHeader("Access-Control-Max-Age", "3600")
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, $TOKEN_HEADER")
//
//        val httpRequest = request as HttpServletRequest
//        httpRequest.getHeader(TOKEN_HEADER)?.let {
//            try {
//                val userDetails = tokenUtils.getUserDetailsFromToken(it)
//                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//                authentication.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
//                SecurityContextHolder.getContext().authentication = authentication
//            } catch (e: Exception) {
//                e.printStackTrace()
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.")
//            }
//        }
//        chain.doFilter(request, response)

        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, $TOKEN_HEADER")

        val request = req as HttpServletRequest

        request.getHeader(TOKEN_HEADER)?.let {
            tokenUtils.getUserFromToken(it)
        }

    }
}