package com.twinlin.mlm.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by zero on 15/5/19.
 */
@WebServlet(name = "GetOrgMap", urlPatterns = "/GetOrgMap.do")
public class GetOrgMap extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();

        String flag = request.getParameter ("flag");
        String param = request.getParameter ("param");
        int memberId = 1;
        if (param != null && !"".equals (param))
        {
            memberId = Integer.parseInt (param);
        }

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);

        List<Map<String, Object>> list = new ArrayList<> ();

        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");

            int level = 1;
            Member member;
            Member_Ext member_ext;
            Queue<Integer> count = new LinkedList<> ();

            for (int x = 0; x < 5; x++)
            {

                for (int y = 0; y < level; y++)
                {
                    if (x == 0)
                    {
                        member = service.search (new Member (), "_id", memberId);
                        member_ext = service.search (new Member_Ext (), "member", memberId);
                    }
                    else
                    {
                        int a = count.poll ();

                        member = service.search (new Member (), "_id", a);
                        member_ext = service.search (new Member_Ext (), "member", a);
                    }

                    Map<String, Object> map = new HashMap<> ();
                    if(member == null)
                    {
                        map.put ("memberName", "空位");
                        map.put ("level", 0);
                    }
                    else
                    {
                        map.put("memberId",member.get_id ());
                        map.put ("memberName", member.getName ());
                        map.put ("level",member_ext.getLevel ());
                        map.put ("upper",member_ext.getUpper ());
                    }

                    list.add (map);

                    if (member_ext != null && member_ext.getLeft_lower () != 0)
                    {
                        count.offer (member_ext.getLeft_lower ());
                    }
                    else
                    {
                        count.offer (0);
                    }

                    if (member_ext != null && member_ext.getRight_lower () != 0)
                    {
                        count.offer (member_ext.getRight_lower ());
                    }
                    else
                    {
                        count.offer (0);
                    }

                }
                level *= 2;
            }

            Gson gson = new Gson ();
            String json = gson.toJson (list);
            jsonObject.addProperty ("success", true);
            jsonObject.addProperty ("message", json);


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
