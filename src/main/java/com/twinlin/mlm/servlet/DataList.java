package com.twinlin.mlm.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.execption.ServiceException;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zero on 15/4/18.
 */
@WebServlet(name = "DataList", urlPatterns = "/DataList.do")
public class DataList extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");
        Properties props = new Properties ();
        props.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        // 取得對應服務的類名
        String serviceName = props.getProperty (flag);
        // 取得對應要操作的類名
        String className = props.getProperty (flag.replaceAll ("service", ""));
        try
        {
            // 得到數據存取服務
            GenericService gs = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            // 取得操作類(java bean)的Class對象
            Class<?> clazz = Class.forName (className);
            List dataList = null;
                String param = request.getParameter ("param");
                if (param != null)
                {
                    switch (param)
                    {
                        case "no":
                            dataList = gs.getAll (clazz, new String[]{"pay"}, new Object[]{param});
                            break;
                        case "project_no":
                            String project_no = request.getParameter ("project_no");
                            dataList = gs.getAll (clazz, new String[]{"project_no"}, new Object[]{project_no});
                            break;
                        case "upper":
                            List<Member_Ext> member_extList = gs.getAll (Member_Ext.class);
                            dataList = new ArrayList ();
                            for (Member_Ext member_ext : member_extList)
                            {
                                if ((member_ext.getLeft_lower () == 0 || member_ext.getRight_lower () == 0))
                                {
                                    Member member = gs.search (new Member (), "_id", member_ext.getMember ());
                                    if (member_ext.getMember () != 1)
                                    {
                                        dataList.add (member);
                                    }
                                }
                            }
                            break;
                        case "multiTable":
                            dataList = gs.getMemberInfo ();
                            break;

                    }
                }
                else
                {
                    dataList = gs.getAll (clazz);
                }

            Gson gson = new Gson ();
            String json = gson.toJson (dataList);
            jsonObject.addProperty ("success", true);
            jsonObject.addProperty ("message", json);
        }
        catch (ServiceException ex)
        {
            jsonObject.addProperty ("success", false);
            jsonObject.addProperty ("message", ex.getMessage ());

        }
        catch (Exception ex)
        {
            jsonObject.addProperty ("success", false);
            jsonObject.addProperty ("message", "獲取不到服務");
        }

        out.print (jsonObject);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost (request, response);
    }
}
