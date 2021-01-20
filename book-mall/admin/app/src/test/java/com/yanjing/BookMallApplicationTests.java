package com.yanjing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMallApplicationTests {

    @DisplayName("金丝雀测试")
    @Test
    void contextLoads() {
        assertEquals(true, true);
    }
}
