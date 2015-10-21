package com.twinlin.mlm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zero on 15/4/25.
 */
@WebFilter(filterName = "MemberAuthFilter", urlPatterns = "/*")
public class MemberAuthFilter implements Filter
{
    public void destroy ()
    {
    }

    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
            IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI ();
        HttpSession session = request.getSession ();

        if (session.getAttribute ("user") == null && !(uri.endsWith ("login.html") || uri.endsWith ("Login.do")
                || uri.endsWith ("css") || uri.endsWith ("js") || uri.endsWith ("woff2") || uri.endsWith ("ttf")))
        {
            if (request.getHeader ("x-requested-with") != null && request.getHeader ("x-requested-with")
                    .equalsIgnoreCase ("XMLHttpRequest"))
            {
                response.setStatus (911);
            }

            response.sendRedirect ("/login.html");
        }
        else
        {
            chain.doFilter (req, resp);
        }

    }

    public void init (FilterConfig config) throws ServletException
    {

    }

}
