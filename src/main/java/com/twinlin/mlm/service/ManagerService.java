package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class ManagerService extends GenericService
{
    private static class ManagerServiceHolder
    {
        private static final ManagerService ms = new ManagerService ();
    }

    public static ManagerService getInstance()
    {
        return ManagerServiceHolder.ms;
    }
}
