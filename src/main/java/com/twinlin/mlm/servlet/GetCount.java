package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by zero on 15/5/8.
 */
@WebServlet(name = "GetCount", urlPatterns = "/GetCount.do")
public class GetCount extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");
        String param = request.getParameter ("param");

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);
        String className = properties.getProperty (flag.replaceAll ("service", ""));

        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);
            int count = 0;
            switch (flag)
            {
                case "projectservice":
                    param = "%" + param + "%";
                    count = service.getCount (clazz, new String[]{"project_no"}, new Object[]{param});
                    jsonObject.addProperty ("success", true);
                    jsonObject.addProperty ("count", count);
                    break;

                case "memberservice":
                    if(param != null)
                    {
                        param = "%" + param + "%";

                            count =service.getCount (clazz,new String[]{"member_no"},new Object[]{param});
                    }
                    else
                    {
                        count = service.getCount (clazz);
                    }

                    jsonObject.addProperty ("success", true);
                    jsonObject.addProperty ("count", count);
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
        doPost (request, response);
    }
}
