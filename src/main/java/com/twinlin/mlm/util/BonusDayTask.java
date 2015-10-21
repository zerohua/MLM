package com.twinlin.mlm.util;

import com.twinlin.mlm.dto.Bonus;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.dto.PV;
import com.twinlin.mlm.service.MemberService;
import com.twinlin.mlm.service.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zero on 15/6/10.
 */
public class BonusDayTask implements Runnable
{
    private ServletContext context;

    public BonusDayTask (ServletContext context)
    {
        this.context = context;
    }

    @Override
    public void run ()
    {
        Service service = MemberService.getInstance ();

        Bonus bonus = service.getRow (new Bonus (), 1);
        float rBonus = Float.parseFloat (bonus.getRecommend_bonus ());
        float bBonus = Float.parseFloat (bonus.getBump_bonus ());
        float eBonus = Float.parseFloat (bonus.getEqual_bonus ());

        PV pv = service.getRow (new PV (), 1);
        int pvValue = Integer.parseInt (pv.getPv_sum ());

        List<Map<String, Object>> bonusList = new ArrayList<> ();
        List<Member> dataList = service.getAll (Member.class);
        for (Member member : dataList)
        {
//            Map<String, Object> map = new HashMap<> ();
//            map.put ("memberNo", member.getMember_no ());
//            map.put ("memberName", member.getName ());
//
//            int recommendPV = BonusUtil.getRecommend (service, Member_Ext.class, member.get_id ());
//            int recommendBonus = (int) (recommendPV * pvValue * (rBonus / 100));
//            map.put ("recommendBonus", recommendBonus);
////            int bumpPV = BonusUtil.getBump (service, member.get_id ());
////            int bumpBonus = (int) (bumpPV * pvValue * (bBonus / 100));
////            map.put ("bumpBonus", bumpBonus);
//            int equalPV = BonusUtil.getEqual (service, Member_Ext.class, member.get_id ());
//            int equalBonus = (int) (equalPV * pvValue * (eBonus / 100));
//            map.put ("equalBonus", equalBonus);
//
//            bonusList.add (map);

        }
        context.setAttribute ("bonuses",bonusList);
    }
}
