package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class SlideService extends GenericService
{
    private static class SlideServiceHolder
    {
        private static final SlideService ss = new SlideService ();
    }

    public static SlideService getInstance()
    {
        return SlideServiceHolder.ss;
    }
}
