package com.twinlin.mlm.servlet;


import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.dto.Project;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by zero on 15/4/6.
 */
@WebServlet(name = "MemberAdd", urlPatterns = {"/MemberAdd.do"})
public class MemberAdd extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        Map<String, String[]> map = request.getParameterMap ();
        GenericService service = new GenericService ();
        try
        {
            service.memberAdd (new Member (), new Member_Ext (),new Project (), map);
            jsonObject.addProperty ("success", true);
            jsonObject.addProperty ("message", "會員帳號新增成功");
        }
        catch (Exception ex)
        {
            jsonObject.addProperty ("success", false);
            jsonObject.addProperty ("message", ex.getMessage ());
        }
        out.print (jsonObject);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost (request, response);
    }
}
