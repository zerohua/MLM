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
import java.util.Map;
import java.util.Properties;

/**
 * Created by zero on 15/4/10.
 */
@WebServlet(name = "Verify", urlPatterns = {"/Verify.do"})
public class Verify extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        String flag = request.getParameter ("flag");
        Map<String, String[]> map = request.getParameterMap ();

        Properties props = new Properties ();
        props.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));

        String serviceName = props.getProperty (flag);
        String className = props.getProperty (flag.replaceAll ("service", ""));

        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);
            Object object = service.search (clazz.newInstance (), map, flag);
            {
                if (object != null)
                    jsonObject.addProperty ("success", true);
                else
                    jsonObject.addProperty ("success", false);
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
