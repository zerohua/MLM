package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Installment;
import com.twinlin.mlm.dto.Project;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zero on 15/4/18.
 */
@WebServlet(name = " CommonAdd", urlPatterns = "/CommonAdd.do")
public class CommonAdd extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        String flag = request.getParameter ("flag");
        Map<String, String[]> map = request.getParameterMap ();

        Properties prop = new Properties ();
        prop.load (getServletContext ().getResourceAsStream ("/WEB-INF/classes/config.properties"));

        String serviceName = prop.getProperty (flag);
        String className = prop.getProperty (flag.replaceAll ("service", ""));
        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);

            switch(flag)
            {
                case "projectservice":
                    String pname = request.getParameter ("pname");
                    String member_no = request.getParameter ("member_no");
                    String installment = request.getParameter ("installment");
                    if(pname.equals ("入會費"))
                    {
                        Object obj = service.search(clazz.newInstance (),new String[]{"pname","member_no"},
                                new Object[]{pname,member_no});
                        if(obj == null)
                        {
                            service.add (clazz.newInstance (), map, flag);
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", "新增成功");
                        }
                        else
                        {
                            Project project = (Project) obj;
                            jsonObject.addProperty ("success", false);
                            jsonObject.addProperty ("message", "會員" + project.getMember_no () + "已有入會費記錄");
                        }
                    }
                    else if(installment.equals ("yes"))
                    {
                        Map<String, String[]> imap = new HashMap<> ();
                        String project_no = request.getParameter ("project_no");
                        String create_name = request.getParameter ("create_name");
                        String create_date = request.getParameter ("create_date");
                        imap.put ("project_no",new String[]{project_no});
                        imap.put ("member_no",new String[]{member_no});
                        imap.put ("create_name",new String[]{create_name});
                        imap.put ("create_date",new String[]{create_date});
                        imap.put ("flag",new String[]{"projectservice"});
                        service.add (new Installment (), imap, "projectservice");

                        service.add (clazz.newInstance (), map, flag);
                        jsonObject.addProperty ("success", true);
                        jsonObject.addProperty ("message", "新增成功");
                    }
                    else
                    {
                        service.add (clazz.newInstance (), map, flag);
                        jsonObject.addProperty ("success", true);
                        jsonObject.addProperty ("message", "新增成功");
                    }
                    break;
                default:
                    service.add (clazz.newInstance (), map, flag);
                    jsonObject.addProperty ("success", true);
                    jsonObject.addProperty ("message", "新增成功");
            }


        }
        catch (Exception ex)
        {
            jsonObject.addProperty ("success",false);
            jsonObject.addProperty ("message",ex.getMessage ());
        }

        out.print (jsonObject);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost (request, response);
    }
}
