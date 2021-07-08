package com.yanjing.repository;

import com.yanjing.entity.User;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.jdbc.JdbcInputFormat;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author yanjing
 * @date 2021/6/27
 */
public class ReadWriteMysqlByJDBC {


    public static DataStream<User> readFromDb() throws IOException {

        final StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        final ParameterTool parameterTool = ParameterTool.fromPropertiesFile("flink.properties");

        // 1. 定义Field类型
        TypeInformation[] fieldTypes = new TypeInformation[]{

                BasicTypeInfo.INT_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO
        };
        // 2.定义FieldNames
        String[] fieldNames = new String[]{"id", "username", "password", "email"};
        // 3.定义Row类型
        RowTypeInfo rowTypeInfo = new RowTypeInfo(fieldTypes, fieldNames);

        // 4.定义JDBCInputFormat
        JdbcInputFormat jdbcInputFormat = JdbcInputFormat
                .buildJdbcInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl(parameterTool.get("mysql-jdbc-url"))
                .setUsername(parameterTool.get("mysql-jdbc-username"))
                .setPassword(parameterTool.get("mysql-jdbc-password"))
                .setQuery("select id, username, password, email from user")
                .setRowTypeInfo(rowTypeInfo)
                .finish();

        // 5.以JDBCInputFormat形式读取MySQL DB数据
        DataStreamSource<Row> dataStreamSourceRow = streamExecutionEnvironment.createInput(jdbcInputFormat);

        // 6. 阶段性验证可以正确读取
        dataStreamSourceRow.print();

        // 7.将Row类型Stream转化为Entity类型
        DataStream<User> dataStream = dataStreamSourceRow.map(new RichMapFunction<Row, User>() {
            @Override
            public User map(Row row) throws Exception {

                User user = new User();
                user.setId((Integer) row.getField("id"));
                user.setUsername((String) row.getField("username"));
                user.setPassword((String) row.getField("password"));
                user.setEmail((String) row.getField("email"));
                return user;
            }
        });

        System.out.println("read datasource end");
        return dataStream;
    }

    public static void writeToDb() throws Exception {

        final StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        // 1.添加数据源
        // DataStream<User> userDataStream = env.addSource(new JdbcReader2());
        DataStream<User> userDataStream = ReadWriteMysqlByJDBC.readFromDb();

        EnvironmentSettings settings = EnvironmentSettings.newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();
        StreamTableEnvironment streamTableEnvironment = StreamTableEnvironment.create(streamExecutionEnvironment, settings);

        // 2.从DataStream获取数据
        Table table = streamTableEnvironment.fromDataStream(userDataStream);
        streamTableEnvironment.createTemporaryView("temp_table", table);

        // 3. 创建sink内部Table
        File destSqlFile = ResourceUtils.getFile("classpath:destination.sql");
        String destSql = FileUtils.readFileUtf8(destSqlFile);
        streamTableEnvironment.executeSql(destSql);

        // 4. 将内部Table插入到outer system
        File insertSqlFile = ResourceUtils.getFile("classpath:insert.sql");
        String insertSql = FileUtils.readFileUtf8(insertSqlFile);
        streamTableEnvironment.executeSql(insertSql);
    }
}
