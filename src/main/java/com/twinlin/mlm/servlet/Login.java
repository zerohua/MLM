package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Manager;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;
import com.twinlin.mlm.util.MD5Encrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by zero on 15/4/26.
 */
@WebServlet(name = "Login", urlPatterns = "/Login.do")
public class Login extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");
        String[] flags = flag.split (",");
        String user = request.getParameter ("manager_name");
        String password = MD5Encrypt.encode (request.getParameter ("manager_pwd"));

        String[] names = {"manager_name", "manager_pwd"};
        Object[] values = {user, password};

        Properties props = new Properties ();
        props.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));

        String serviceName = props.getProperty (flags[0]);
        String className1 = props.getProperty (flags[0].replaceAll ("service", ""));
        String className2 = props.getProperty (flags[1].replaceAll ("service", ""));

        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className1);
            Object object;
            object = service.search (clazz.newInstance (), names, values);
            HttpSession session = request.getSession ();

            if (object != null)
            {
                Manager manager = (Manager) object;
                session.setAttribute ("user", manager.getNick_name ());
                session.setAttribute ("who" , "manger");
                session.setAttribute ("level","1");
                jsonObject.addProperty ("success", true);
                jsonObject.addProperty ("who", "manager");
                jsonObject.addProperty ("user" , manager.getNick_name ());
                jsonObject.addProperty ("level",manager.getLevel ());
            }
            else
            {
                names[0] = "member_no";
                names[1] = "member_pwd";
                clazz = Class.forName (className2);
                object = service.search (clazz.newInstance (), names, values);

                if (object != null)
                {
                    Member member = (Member) object;
                    session.setAttribute ("user", member.getMember_no ());
                    session.setAttribute ("who" , "member");
                    jsonObject.addProperty ("success", true);
                    jsonObject.addProperty ("who","member");
                    jsonObject.addProperty ("user" , member.getName ());
                    jsonObject.addProperty ("memberNo" , member.getMember_no ());

                }
                else
                {
                    jsonObject.addProperty ("success", false);
                }
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
