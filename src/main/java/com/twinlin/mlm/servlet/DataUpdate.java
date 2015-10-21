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
 * Created by zero on 15/5/9.
 */
@WebServlet(name = "DataUpdate", urlPatterns = "/DataUpdate.do")
public class DataUpdate extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");
        String[] paramNames = request.getParameter ("paramName").split (",");
        String[] paramValues = request.getParameter ("paramValue").split (",");
        String columnName = request.getParameter ("columnName");
        String columnValue = request.getParameter ("columnValue");

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);
        String className = properties.getProperty (flag.replaceAll ("service", ""));

        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);

            service.update (clazz.newInstance (), paramNames, paramValues,columnName, columnValue);
            jsonObject.addProperty ("success", true);
            jsonObject.addProperty ("message", "更新成功");

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
