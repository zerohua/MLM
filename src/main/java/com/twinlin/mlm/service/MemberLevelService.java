package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class MemberLevelService extends GenericService
{
    private static class Member_LevelServiceHolder
    {
        private static final MemberLevelService mls = new MemberLevelService ();
    }

    public static MemberLevelService getInstance()
    {
        return Member_LevelServiceHolder.mls;
    }
}
