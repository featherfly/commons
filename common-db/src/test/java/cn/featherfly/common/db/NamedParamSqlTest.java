
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-08 21:33:08
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.repository.Execution;

/**
 * NamedParamSqlTest.
 *
 * @author zhongj
 */
public class NamedParamSqlTest {

    private String namedSql = "select * from user where id in :ids and gender = :gender and state in :states";

    private String namedSql2;

    private String namedSql3;

    private NamedParamSql sql = NamedParamSql.compile(namedSql);

    private OldNamedParamSql sql2 = OldNamedParamSql.compile(namedSql);

    private List<Integer> idList;

    private List<Integer> stateList;

    private Map<String, Serializable> params;

    private final int max = 100000;

    @BeforeClass
    void beforeClass() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            sb.append("abc ");
        }
        namedSql2 = sb.append(namedSql).toString();

        sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append("abc ");
        }
        namedSql3 = sb.append(namedSql).toString();
    }

    @SuppressWarnings("rawtypes")
    @BeforeMethod
    void beforeMethod() {
        idList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            idList.add(i);
        }
        stateList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stateList.add(i);
        }

        params = new LinkedHashMap<>();
        params.put("ids", (ArrayList) idList);
        params.put("gender", "male");
        params.put("states", (ArrayList) idList);
    }

    @Test
    public void test() {
        sql = SqlUtils.convertNamedParamSql(namedSql);
        Execution execution = sql.getExecution(params);
        //        System.out.println(sql.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql.getExecution(params);
        }
    }

    @Test
    public void test2() {
        sql = SqlUtils.convertNamedParamSql(namedSql2);
        Execution execution = sql.getExecution(params);
        //        System.out.println(sql.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql.getExecution(params);
        }
    }

    @Test
    public void test3() {
        sql = SqlUtils.convertNamedParamSql(namedSql3);
        Execution execution = sql.getExecution(params);
        //        System.out.println(sql.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql.getExecution(params);
        }
    }

    @Test
    public void testOld() {
        sql2 = OldSqlUtils.convertNamedParamSql(namedSql);
        Execution execution = sql2.getExecution(params);
        //        System.out.println(sql2.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql2.getExecution(params);
        }
    }

    @Test
    public void testOld2() {
        sql2 = OldSqlUtils.convertNamedParamSql(namedSql2);
        Execution execution = sql2.getExecution(params);
        //        System.out.println(sql2.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql2.getExecution(params);
        }
    }

    @Test
    public void testOld3() {
        sql2 = OldSqlUtils.convertNamedParamSql(namedSql3);
        Execution execution = sql2.getExecution(params);
        //        System.out.println(sql2.getSql());
        //        System.out.println(execution.getExecution());
        assertEquals(execution.getParams().length, idList.size() + stateList.size() + 1);

        for (int i = 0; i < max; i++) {
            sql2.getExecution(params);
        }
    }
}
