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
 * Created by zero on 15/5/15.
 */
@WebServlet(name = "GetOrgList", urlPatterns = "/GetOrgList.do")
public class GetOrgList extends HttpServlet
{
    GenericService service = null;

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        Gson gson = new Gson ();

        String flag = request.getParameter ("flag");
        String param = request.getParameter ("numberId");
        String line = request.getParameter ("line");
        int memberId = 1;
        if (param != null && !"".equals (param))
        {
            memberId = Integer.parseInt (param);
        }

        Properties properties = new Properties ();
        properties.load (getServletContext ().getResourceAsStream ("WEB-INF/classes/config.properties"));
        String serviceName = properties.getProperty (flag);
        String className = properties.getProperty (flag.replaceAll ("service", ""));
        try
        {
            service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);

            List<Map<String, Object>> leftOrgList = new ArrayList<> ();
            List<Map<String, Object>> rightOrgList = new ArrayList<> ();
            List<Member_Ext> member_extList = service.getAll (clazz);
            List<Map<String, Object>> leftList;
            List<Map<String, Object>> rightList;
            String orientation = null;
            if (member_extList != null)
            {
                for (Member_Ext member_ext : member_extList)
                {
                    if (member_ext.getMember () == memberId)
                    {
                        if (line != null && line.equals ("left"))
                        {
                            int left = member_ext.getLeft_lower ();
                            leftList = getOrg (leftOrgList, left, member_extList, 1);
                            orientation = gson.toJson (leftList);
                        }
                        if (line != null && line.equals ("right"))
                        {
                            int right = member_ext.getRight_lower ();
                            rightList = getOrg (rightOrgList, right, member_extList, 1);
                            orientation = gson.toJson (rightList);

                        }
                    }
                }
            }

            jsonObject.addProperty ("success", true);
            jsonObject.addProperty ("orgList", orientation);
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


    private List<Map<String, Object>> getOrg (List<Map<String, Object>> list, int Point, List<Member_Ext> member_extList, int generation)
    {

        int leftLower = 0;
        int rightLower = 0;
        for (Member_Ext member_ext : member_extList)
        {
            if (member_ext.getMember () == Point)
            {
                Map<String, Object> map = getPointData (member_ext, generation);
                list.add (map);
                leftLower = member_ext.getLeft_lower ();
                rightLower = member_ext.getRight_lower ();
            }
        }
        generation++;
        if (leftLower != 0)
        {
            getOrg (list, leftLower, member_extList, generation);
        }

        if (rightLower != 0)
        {
            getOrg (list, rightLower, member_extList, generation);
        }

        return list;
    }

    private Map<String, Object> getPointData (Member_Ext member_ext, int generation)
    {
        Map<String, Object> map = new HashMap<> ();
        Member member = service.search (new Member (), "_id", member_ext.getMember ());
        map.put ("generation", generation);
        map.put ("member_no", member.getMember_no ());
        String level = null;
        switch (member_ext.getLevel ())
        {
            case 1:
                level = "會員";
                break;
            case 2:
                level = "處長";
                break;
            case 3:
                level = "區長";
                break;
            case 4:
                level = "部長";
                break;
            case 5:
                level = "營運長";
                break;

        }
        map.put ("level", level);
        map.put ("name", member.getName ());
        map.put ("recommend", (service.search (new Member (), "_id", member_ext.getRecommend ())).getName ());
        Member memberUpper = service.search (new Member (), "_id", member_ext.getUpper ());
        map.put ("upper", (memberUpper.getName ()));
        Member_Ext member_extUpper = service.search (new Member_Ext (), "member", memberUpper.get_id ());
        String position;
        if (member_extUpper.getLeft_lower () == member.get_id ())
            position = "左線";
        else
            position = "右線";
        map.put ("position", position);
        map.put ("status", member_ext.getStatus ());
        map.put ("bump", "");
        map.put ("create_date", member.getCreate_date ());
        return map;
    }
}
