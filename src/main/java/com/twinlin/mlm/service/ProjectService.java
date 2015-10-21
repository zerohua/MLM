package com.twinlin.mlm.service;

/**
 * Created by zero on 15/3/25.
 */
public class ProjectService extends GenericService
{
    private static class ProjectServiceHolder
    {
        private static final ProjectService ps = new ProjectService ();
    }

    public static ProjectService getInstance()
    {
        return ProjectServiceHolder.ps;
    }
}
