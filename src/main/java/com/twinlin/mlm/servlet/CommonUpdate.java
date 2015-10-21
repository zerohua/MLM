package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zero on 15/5/25.
 */
@WebServlet(name = "CommonUpdate" ,urlPatterns = "/CommonUpdate.do")
public class CommonUpdate extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");

        Map<String, String[]> map = request.getParameterMap();

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);
        String className = properties.getProperty (flag.replaceAll ("service", ""));


        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);
            String memberNo;
            switch(flag)
            {

                case "memberservice":
                    memberNo = request.getParameter ("member_no");
                    service.updateMemberInfo (map, "member_no", memberNo, flag);
                    jsonObject.addProperty ("success", true);
                    jsonObject.addProperty ("message", "資料更新成功");
                    break;
            }
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
        doPost (request,response);
    }
}
