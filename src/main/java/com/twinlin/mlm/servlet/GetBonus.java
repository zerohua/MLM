package com.twinlin.mlm.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.*;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;
import com.twinlin.mlm.util.BonusUtil;
import com.twinlin.mlm.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by zero on 15/5/13.
 */
@WebServlet(name = "GetBonus", urlPatterns = "/GetBonus.do")
public class GetBonus extends HttpServlet
{

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
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

            List<Map<String, Object>> bonusList = new ArrayList<> ();
            Bonus bonus = service.getRow (new Bonus (), 1);
            float rBonus = Float.parseFloat (bonus.getRecommend_bonus ());
            float bBonus = Float.parseFloat (bonus.getBump_bonus ());
            float eBonus = Float.parseFloat (bonus.getEqual_bonus ());

            PV pv = service.getRow (new PV (), 1);
            int pvValue = Integer.parseInt (pv.getPv_sum ());

            List<Member> dataList = service.getAll (clazz);
            for (Member member : dataList)
            {
//                Map<String, Object> map = new HashMap<> ();
//                map.put ("memberNo", member.getMember_no ());
//                map.put ("memberName", member.getName ());
//
//                int recommendPV = BonusUtil.getRecommend (service, Member_Ext.class, member.get_id ());
//                int recommendBonus = (int) (recommendPV * pvValue * (rBonus / 100));
//                map.put ("recommendBonus", recommendBonus);
//                Map<String, Object> bumpMap = BonusUtil.getBump (service, member.get_id ());
//                int bumpPV = (int) bumpMap.get ("bumpPv");
//                int bumpBonus = (int) (bumpPV * pvValue * (bBonus / 100));
//                map.put ("bumpBonus", bumpBonus);
//                int equalPV = BonusUtil.getEqual (service, Member_Ext.class, member.get_id ());
//                int equalBonus = (int) (equalPV * pvValue * (eBonus / 100));
//                map.put ("equalBonus", equalBonus);
//
//                map.put ("leftPv",bumpMap.get("leftPv"));
//                map.put("rightPv",bumpMap.get("rightPv"));
//
//                bonusList.add (map);
            }


            Gson gson = new Gson ();
            String json = gson.toJson (bonusList);

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
