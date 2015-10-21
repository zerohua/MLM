package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class WeekAccountService extends GenericService
{
    private static class WeekAccountServiceHolder
    {
        private static final WeekAccountService was = new WeekAccountService ();
    }

    public static WeekAccountService getInstance()
    {
        return WeekAccountServiceHolder.was;
    }
}
