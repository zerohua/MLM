package com.twinlin.mlm.servlet;

import com.google.gson.JsonObject;
import com.twinlin.mlm.dto.Member;
import com.twinlin.mlm.dto.Member_Ext;
import com.twinlin.mlm.service.GenericService;
import com.twinlin.mlm.service.Service;
import com.twinlin.mlm.util.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zero on 15/4/11.
 */
@WebServlet(name = "CheckLowerPosition", urlPatterns = {"/CheckLowerPosition.do"})
public class CheckLowerPosition extends HttpServlet
{
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter ();
        JsonObject jsonObject = new JsonObject ();
        String member_no = request.getParameter ("member_no");
        try
        {
            GenericService service = new GenericService ();
            Member member = service.search (new Member (), "member_no", member_no);
            {
                if (member != null)
                {
                    int id = member.get_id ();
                    Member_Ext member_ext = service.search (new Member_Ext (), "member", id);
                    if (member_ext != null)
                    {
                        if (member_ext.getLeft_lower () == 0 && member_ext.getRight_lower () == 0)
                        {
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", "lr");
                        }
                        else if (member_ext.getRight_lower () == 0)
                        {
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", "r");
                        }
                        else if (member_ext.getLeft_lower () == 0)
                        {
                            jsonObject.addProperty ("success", true);
                            jsonObject.addProperty ("message", "l");
                        }
                        else
                        {
                            jsonObject.addProperty ("success", false);
                        }
                    }
                    else
                    {
                        jsonObject.addProperty ("success", false);
                        jsonObject.addProperty ("message", "查找不到此會員");
                    }
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
