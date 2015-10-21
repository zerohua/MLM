package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class CycleService extends GenericService
{
    private static class CycleServiceHolder
    {
        private static final CycleService cs = new CycleService ();
    }

    public static CycleService getInstance()
    {
        return CycleServiceHolder.cs;
    }
}
