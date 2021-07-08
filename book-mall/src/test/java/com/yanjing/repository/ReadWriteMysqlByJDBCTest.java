package com.yanjing.repository;

import org.junit.jupiter.api.Test;

/**
 * @author yanjing
 * @date 2021/6/27
 */
class ReadWriteMysqlByJDBCTest {

    @Test
    void flink() throws Exception {

        ReadWriteMysqlByJDBC.writeToDb();
    }
}