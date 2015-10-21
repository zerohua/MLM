package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.*;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by zero on 15/4/29.
 */
@WebServlet(name = "CommonSearch", urlPatterns = "/CommonSearch.do")
public class CommonSearch extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);
        String className = properties.getProperty (flag.replaceAll ("service", ""));
        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);

            Object obj;
            String upperId;
            String project;
            switch (flag)
            {
                case "memberservice":
                    upperId = request.getParameter ("upperId");
                    String pos = request.getParameter ("pos");
                    String memberNo = request.getParameter ("memberNo");
                    String memberName = request.getParameter ("memberName");
                    if (upperId != null)
                    {
                        obj = service.search (clazz.newInstance (), "_id", upperId);
                        if (obj != null)
                        {
                            Member member = (Member) obj;
                            jsonObject.addProperty ("success", true);
                            if (pos != null)
                            {
                                jsonObject.addProperty ("message", member.getName ());
                            }
                            else
                            {
                                jsonObject.addProperty ("message", member.getName () + " " + member.getMember_no ());
                            }
                        }
                    }

                    if (memberNo != null)
                    {
                        obj = service.search (clazz.newInstance (), "member_no", memberNo);
                        if (obj != null)
                        {
                            Member member = (Member) obj;
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", member.get_id ());
                            jsonObject.addProperty ("memberName", member.getName ());

                        }
                        else
                        {
                            jsonObject.addProperty ("success", false);
                        }
                    }
                    if (memberName != null)
                    {
                        obj = service.search (clazz.newInstance (), "name", memberName);
                        if (obj != null)
                        {
                            Member member = (Member) obj;
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", member.get_id ());

                        }
                        else
                        {
                            jsonObject.addProperty ("success", false);
                        }

                    }
                    break;
                case "memberextservice":
                    String memberId = request.getParameter ("memberId");
                    upperId = request.getParameter ("upperId");
                    if (upperId != null)
                    {
                        obj = service.search (clazz.newInstance (), "member", upperId);
                        if (obj != null)
                        {
                            Member_Ext member_ext = (Member_Ext) obj;
                            jsonObject.addProperty ("success", true);
                            if (member_ext.getLeft_lower () == Integer.parseInt (memberId))
                            {
                                jsonObject.addProperty ("message", "左線");
                            }
                            else
                            {
                                jsonObject.addProperty ("message", "右線");
                            }
                        }
                    }
                    break;
                case "consumeservice":
                    String money = request.getParameter ("price");
                    obj = service.search (clazz.newInstance (), "money", money);
                    if (obj != null)
                    {
                        Consume consume = (Consume) obj;
                        jsonObject.addProperty ("success", true);
                        jsonObject.addProperty ("message", true);
                        jsonObject.addProperty ("pv_value", consume.getGeneration_pv ());
                    }
                    else
                    {
                        obj = service.search (clazz.newInstance (), "mode", "common");
                        if (obj != null)
                        {
                            Consume consume = (Consume) obj;
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", false);
                            jsonObject.addProperty ("money", consume.getMoney ());
                        }

                    }
                    break;
                case "amortizationservice":
                    project = request.getParameter ("project");
                    obj = service.search (clazz.newInstance (), "produce", project);
                    if (obj != null)
                    {
                        Amortization amortization = (Amortization) obj;
                        jsonObject.addProperty ("success", true);
                        jsonObject.addProperty ("stage", amortization.getStage ());
                        jsonObject.addProperty ("stage_money", amortization.getStage_money ());
                        jsonObject.addProperty ("stage_pv", amortization.getStage_pv ());
                        jsonObject.addProperty ("remain_pv", amortization.getRemain_pv ());
                    }
                    else
                    {
                        jsonObject.addProperty ("success", false);
                    }
                    break;
                case "produceservice":
                    project = request.getParameter ("project");
                    obj = service.search (clazz.newInstance (), "pname", project);
                    if (obj != null)
                    {
                        Produce produce = (Produce) obj;
                        jsonObject.addProperty ("success", true);
                        jsonObject.addProperty ("price", produce.getPrice ());
                        jsonObject.addProperty ("pv", produce.getPv ());
                    }
                    else
                    {
                        jsonObject.addProperty ("success", false);
                    }
                    break;
                case "installmentservice":
                    String projectNo = request.getParameter ("param");
                    obj = service.search (clazz.newInstance (), "project_no", projectNo);
                    if (obj != null)
                    {
                        Installment installment = (Installment) obj;
                        for (int i = 1; i <= 28; i++)
                        {
                            Method method = clazz.getDeclaredMethod ("getStage" + i);
                            if (method.invoke (installment) == null)
                            {
                                jsonObject.addProperty ("success", true);
                                jsonObject.addProperty ("message", i);
                                break;
                            }
                        }
                    }
                    break;
                case "weekaccountservice":
                    String param = request.getParameter ("param");
                    String value[] = param.split (",");
                    obj = service.search (clazz.newInstance (), new String[]{"year","week","member_no"},
                            new  Object[]{value[0],value[1],value[2]});
                    if(obj != null)
                    {
                        jsonObject.addProperty ("success", true);
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
