package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class ConsumeService extends GenericService
{
    private static class ConsumeServiceHolder
    {
        private static final ConsumeService cs = new ConsumeService ();
    }

    public static ConsumeService getInstance()
    {
        return ConsumeServiceHolder.cs;
    }
}
