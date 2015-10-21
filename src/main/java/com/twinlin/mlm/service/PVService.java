package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class PVService extends GenericService
{
    private static class PVServiceHolder
    {
        private static final PVService ps = new PVService ();
    }

    public static PVService getInstance()
    {
        return PVServiceHolder.ps;
    }
}
