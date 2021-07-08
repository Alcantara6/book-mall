package com.yanjing;

import com.yanjing.repository.ReadWriteMysqlByJDBC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMallApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BookMallApplication.class, args);
        ReadWriteMysqlByJDBC.writeToDb();
    }
}
