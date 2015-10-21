package com.twinlin.mlm.listener;
/**
 * Created by zero on 15/6/9.
 */

import com.twinlin.mlm.util.AccessUtil;
import com.twinlin.mlm.util.BonusDayTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener
{
    private ScheduledExecutorService executor;

    // Public constructor is required by servlet spec
    public Listener () {}

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized (ServletContextEvent sce)
    {

        executor = Executors.newScheduledThreadPool (1);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay = AccessUtil.getTimeMillis ("1:35:00") - System.currentTimeMillis ();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        executor.scheduleAtFixedRate (
                new BonusDayTask (sce.getServletContext ()),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }

    public void contextDestroyed (ServletContextEvent sce)
    {

        // timer.cancel ();
        if (executor != null)
            executor.shutdown ();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated (HttpSessionEvent se)
    {
      /* Session is created. */
    }

    public void sessionDestroyed (HttpSessionEvent se)
    {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded (HttpSessionBindingEvent sbe)
    {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved (HttpSessionBindingEvent sbe)
    {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced (HttpSessionBindingEvent sbe)
    {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
