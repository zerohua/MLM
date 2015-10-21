package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class ProduceService extends GenericService
{
    private static class ProduceServiceHolder
    {
        private static final ProduceService ps = new ProduceService ();
    }

    public static ProduceService getInstance()
    {
        return ProduceServiceHolder.ps;
    }
}
