package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class MemberExtService extends GenericService
{
    private static class MemberExtServiceHolder
    {
        private static final MemberExtService mes = new MemberExtService ();
    }

    public static MemberExtService getInstance()
    {
        return MemberExtServiceHolder.mes;
    }
}
