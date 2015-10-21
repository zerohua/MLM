package com.twinlin.mlm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zero on 15/4/8.
 */
@WebFilter(filterName = "CharacterEncodingFilter" , urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter
{
    public void destroy ()
    {
    }

    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter (req, resp);
    }

    public void init (FilterConfig config) throws ServletException
    {

    }

}
