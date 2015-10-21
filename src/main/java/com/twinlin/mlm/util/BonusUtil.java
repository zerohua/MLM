package com.twinlin.mlm.util;

import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.dto.Project;
import com.twinlin.mlm.dto.WeekAccount;
import com.twinlin.mlm.service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zero on 15/6/8.
 */
public class BonusUtil
{

    public static int getRecommend (Service service, int memberId, List<Member> list)
    {
        List<Member_Ext> dataList = service.getAll (Member_Ext.class, new String[]{"recommend"}, new
                Object[]{memberId});
        int pv = 0;
        for (Member_Ext member_ext : dataList)
        {
            Member member = service.search (new Member (), "_id", member_ext.getMember ());
            if (list.contains (member))
            {
                Project p = service.search (new Project (), "member_no", member.getMember_no ());
                if (p != null)
                    pv = pv + Integer.parseInt (p.getPv ());
            }
        }
        return pv;

    }

    public static Map<String, Object> getBump (Service service, int memberId, List<Member> list)
    {

        Map<String, Object> map = new HashMap<> ();
        List<Integer> listLeft = new ArrayList<> ();
        List<Integer> listRight = new ArrayList<> ();
        Member_Ext member_ext = service.search (new Member_Ext (), "member", memberId);

        int leftPv = getAllPv (service, listLeft, member_ext.getLeft_lower (), list);
        int rightPv = getAllPv (service, listRight, member_ext.getRight_lower (), list);

        String lastPv = member_ext.getLast_pv ();
        int preValue = 0;
        if (!"".equals(lastPv))
        {
            String[] value = lastPv.split ("_");
            preValue = Integer.parseInt (value[1]);
            if (value[0].equals ("l"))
            {
                leftPv = leftPv + preValue;

            }
            else if (value[0].equals ("r"))
            {
                rightPv = rightPv + preValue;

            }
        }

        map.put ("leftPv", leftPv);
        map.put ("rightPv", rightPv);

        if (leftPv != 0 && rightPv != 0)
        {
            if (leftPv - rightPv >= 0)
            {
                map.put ("bumpPv", rightPv);
                map.put ("lastPv", "l_" + ((leftPv - rightPv) > (960 + preValue) ? 960 + preValue : leftPv - rightPv));
            }
            else
            {
                map.put ("bumpPv", leftPv);
                map.put ("lastPv", "r_" + ((rightPv - leftPv) > (960 + preValue) ? 960 + preValue : rightPv - leftPv));
            }
        }
        else
        {
            if (leftPv > rightPv)
            {
                map.put ("bumpPv", 0);
                map.put ("lastPv", "l_" + (leftPv > (960 + preValue) ? 960 + preValue : leftPv));
            }
            else if (rightPv > leftPv)
            {
                map.put ("bumpPv", 0);
                map.put ("lastPv", "r_" + (rightPv> (960 + preValue) ? 960 + preValue : rightPv));
            }
            else
            {
                map.put ("bumpPv", 0);
                map.put ("lastPv", "m_0");
            }
        }
        return map;
    }



    public static int getEqual (Service service, int memberId, String year, String week, List<Member> list)
    {
        List<Member_Ext> dataList = service.getAll (Member_Ext.class, new String[]{"recommend"}
                , new Object[]{memberId});
        int pv = 0;
        for (Member_Ext member_ext : dataList)
        {
            Member member = service.search (new Member (), "_id", member_ext.getMember ());
            if (list.contains (member))
            {
                WeekAccount weekAccount = service.search (new WeekAccount (),new String[]{"year","week","member_no"}
                ,new Object[]{year,week,member.getMember_no ()});
                pv = pv + Integer.parseInt (weekAccount.getBump_pv ());
            }

        }
        return pv;
    }

    private static int getAllPv (Service service, List<Integer> list, int memberId, List<Member> memberList)
    {
        if (memberId == 0 && list.size () == 0)
        {
            return 0;
        }

        int pv = 0;
        Member member = service.search (new Member (), "_id", memberId);
        if (memberList.contains (member))
        {
            Project p = service.search (new Project (), "member_no", member.getMember_no ());
            if (p != null)
                pv = Integer.parseInt (p.getPv ());

        }
        list.add (pv);

        Member_Ext member_ext = service.search (new Member_Ext (), "member", memberId);

        int leftLower = member_ext.getLeft_lower ();
        int rightLower = member_ext.getRight_lower ();


        if (leftLower != 0)
        {
            getAllPv (service, list, leftLower, memberList);
        }

        if (rightLower != 0)
        {
            getAllPv (service, list, rightLower, memberList);
        }
        int value = 0;
        for (Integer integer : list)
        {
            value = value + integer;
        }

        return value;
    }
}
