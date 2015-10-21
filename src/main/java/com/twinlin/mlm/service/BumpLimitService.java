package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class BumpLimitService extends GenericService
{
    private static class BumpLimitServiceHolder
    {
        private static final BumpLimitService bs = new BumpLimitService ();
    }

    public static BumpLimitService getInstance()
    {
        return BumpLimitServiceHolder.bs;
    }
}
