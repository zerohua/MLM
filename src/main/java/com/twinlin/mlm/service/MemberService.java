package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class MemberService extends GenericService
{
    private static class MemberServiceHolder
    {
        private static final MemberService ms = new MemberService ();
    }

    public static MemberService getInstance()
    {
        return MemberServiceHolder.ms;
    }
}
