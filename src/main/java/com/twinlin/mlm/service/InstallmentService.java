package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class InstallmentService extends GenericService
{
    private static class InstallmentServiceHolder
    {
        private static final InstallmentService is = new InstallmentService ();
    }

    public static InstallmentService getInstance()
    {
        return InstallmentServiceHolder.is;
    }
}
