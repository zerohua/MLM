package com.twinlin.mlm.service;

import com.twinlin.mlm.execption.DataAccessException;
import com.twinlin.mlm.execption.DataRedundancyException;
import com.twinlin.mlm.execption.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by zero on 15/3/25.
 */
public interface Service
{
    int getCount (Class<?> clazz) throws DataAccessException;

    int getCount (Class<?> clazz, String[] columnName, final Object[] rowValue) throws DataAccessException;

    <T> T getRow (final T t, int number) throws DataAccessException;

    <T> int add (T t, Map<String, String[]> map, String flag) throws DataAccessException;

    <T> void add (Class<T> clazz, List<Map<String, String[]>> list) throws DataAccessException;

    <M, E, P> boolean memberAdd (M m, E e, P p, Map<String, String[]> map) throws DataAccessException;

    <T> boolean update (final T t, String[] column, final Object[] value, String columnName, final String rowValue)
            throws DataAccessException;

    <T> void update (T t, Map<String, String[]> map, String column, String columnValue, String flag) throws
            DataAccessException;

    <T> void update (Class<T> clazz, List<Map<String, String[]>> list) throws DataAccessException;

    void updateMemberInfo (Map<String, String[]> map, String column, String columnValue, String flag) throws
            DataAccessException;

    <T> T search (T t, Map<String, String[]> map, String flag) throws DataRedundancyException;

    <T> T search (T t, String name, Object value) throws DataAccessException;

    <T> T search (T t, String[] name, Object[] value) throws DataAccessException;

    <T> List<T> getAll (final Class<?> clazz) throws ServiceException;

    <T> List<T> getAll (final Class<?> clazz, String[] columnName, Object[] rowValue) throws ServiceException;

    <T> List<T> getAllByDate (final Class<?> clazz, String columnName, String startDate, String endDate) throws
            ServiceException;

    List<Map<String, Object>> getMemberInfo () throws ServiceException;

    List<Map<String, Object>> getMember (Class<?> clazz1, Class<?> clazz2, String value1, String value2,
                                         String column, String columnValue);


}
