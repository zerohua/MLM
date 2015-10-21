package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.*;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.util.AccessUtil;
import com.twinlin.mlm.util.BonusUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zero on 15/7/17.
 */
@WebServlet(name = "CloseAccount", urlPatterns = "/CloseAccount.do")
public class CloseAccount extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        String flag = request.getParameter ("flag");
        String param = request.getParameter ("param");

        Properties prop = new Properties ();
        prop.load (getServletContext ().getResourceAsStream ("/WEB-INF/classes/config.properties"));

        String serviceName = prop.getProperty (flag);
        String className = prop.getProperty (flag.replaceAll ("service", ""));
        try
        {
            GenericService service = (GenericService) AccessUtil.getService (serviceName, "getInstance");
            Class<?> clazz = Class.forName (className);

            String date[] = param.split ("~");
            String year = date[0];
            String week = date[1];
            String startDate = date[2].replaceAll ("/", "-");
            String endDate = date[3].replaceAll ("/", "-");
            String systemStartDate = "2014-12-17";

            List<Member> memberList = service.getAllByDate (clazz, "create_date", startDate, endDate);
            List<Member> allMemberList = service.getAllByDate (clazz, "create_date", systemStartDate, endDate);

            if (memberList.size () != 0)
            {
                Bonus bonus = service.getRow (new Bonus (), 1);
                float rBonus = Float.parseFloat (bonus.getRecommend_bonus ());
                float bBonus = Float.parseFloat (bonus.getBump_bonus ());
                float eBonus = Float.parseFloat (bonus.getEqual_bonus ());

                PV pv = service.getRow (new PV (), 1);
                int pvValue = Integer.parseInt (pv.getPv_sum ());
                List<Map<String, String[]>> dataList = new ArrayList<> ();
                int memberId = 0;
                for (Member member : allMemberList)
                {
                    memberId = member.get_id ();
                    Map<String, String[]> map = new HashMap<> ();
                    map.put ("year", new String[]{year});
                    map.put ("week", new String[]{week});
                    map.put ("date_cycle", new String[]{startDate + "~" + endDate});

                    map.put ("member_no", new String[]{member.getMember_no ()});
                    map.put ("member_name", new String[]{member.getName ()});
                    int recommendPV = BonusUtil.getRecommend (service, memberId, memberList);
                    int recommendBonus = (int) (recommendPV * pvValue * (rBonus / 100));
                    map.put ("recommend_bonus", new String[]{recommendBonus + ""});

                    Map<String, Object> bumpMap = BonusUtil.getBump (service, memberId, memberList);
                    map.put ("left_pv", new String[]{bumpMap.get ("leftPv") + ""});
                    map.put ("right_pv", new String[]{bumpMap.get ("rightPv") + ""});
                    int bumpPv = (int) bumpMap.get ("bumpPv");
                    map.put ("bump_pv", new String[]{bumpPv + ""});
                    map.put ("last_pv", new String[]{bumpMap.get ("lastPv") + ""});
                    int bumpBonus = (int) (bumpPv * pvValue * (bBonus / 100));
                    map.put ("org_bonus", new String[]{bumpBonus + ""});


                    String name = (String) request.getSession ().getAttribute ("user");
                    map.put ("create_name", new String[]{name});
                    Date currentDate = new Date ();
                    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
                    String dateStr = sdf.format (currentDate);
                    map.put ("create_date", new String[]{dateStr});
                    dataList.add (map);
                }
                service.add (WeekAccount.class, dataList);

                List<Map<String, String[]>> equalList = new ArrayList<> ();
                Map<String, String[]> map;
                int weekAccountId;
                for (Member member : allMemberList)
                {
                    map = new HashMap<> ();
                    int equalPV = BonusUtil.getEqual (service, member.get_id (), year, week, memberList);
                    int equalBonus = (int) (equalPV * pvValue * (eBonus / 100));
                    WeekAccount weekAccount = service.search(new WeekAccount (),new String[]{"year","week","member_no"}
                    ,new Object[]{year,week,member.getMember_no ()});
                    weekAccountId = weekAccount.get_id ();
                    map.put ("equal_bonus", new String[]{equalBonus + ""});
                    map.put ("id",new String[]{weekAccountId+""});
                    equalList.add(map);
                }

                service.update(WeekAccount.class,equalList);


            }
            else
            {
                Map<String, String[]> map = new HashMap<> ();
                map.put ("year", new String[]{year});
                map.put ("week", new String[]{week});
                map.put ("date_cycle", new String[]{startDate + "~" + endDate});
                map.put ("member_no", new String[]{"CTPL0001"});
                map.put ("member_name", new String[]{"公司"});
                map.put ("recommend_bonus", new String[]{"0"});
                map.put ("left_pv", new String[]{"0"});
                map.put ("right_pv", new String[]{"0"});
                map.put ("last_pv", new String[]{"m_0"});
                map.put ("bump_pv", new String[]{"0"});
                map.put ("org_bonus", new String[]{"0"});
                map.put ("equal_bonus", new String[]{"0"});
                String name = (String) request.getSession ().getAttribute ("user");
                map.put ("create_name", new String[]{name});
                Date currentDate = new Date ();
                SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
                String dateStr = sdf.format (currentDate);
                map.put ("create_date", new String[]{dateStr});
                service.add (new WeekAccount (), map, "null");
            }

            jsonObject.addProperty ("success", true);
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

    }
}
