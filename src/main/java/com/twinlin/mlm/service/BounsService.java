package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class BounsService extends GenericService
{
    private static class BounsServiceHolder
    {
        private static final BounsService bs = new BounsService ();
    }

    public static BounsService getInstance()
    {
        return BounsServiceHolder.bs;
    }
}
