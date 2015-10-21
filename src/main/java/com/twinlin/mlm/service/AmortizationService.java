package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class AmortizationService extends GenericService
{
    private static class AmortizationServiceHolder
    {
        private static final AmortizationService as = new AmortizationService ();
    }

    public static AmortizationService getInstance()
    {
        return AmortizationServiceHolder.as;
    }
}
