package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class UpgradeService extends GenericService
{
    private static class UpgradeServiceeHolder
    {
        private static final UpgradeService us = new UpgradeService ();
    }

    public static UpgradeService getInstance()
    {
        return UpgradeServiceeHolder.us;
    }
}
