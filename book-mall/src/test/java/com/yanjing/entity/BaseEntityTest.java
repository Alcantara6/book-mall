package com.yanjing.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseEntityTest {

    @Nested
    class Equals {
        @Test
        public void equals_shouldReturnTrue_ifHaveReflexivity() {
            BaseEntity baseEntity = createWithId(1);

            assertEquals(true, baseEntity.equals(baseEntity));
        }

        @Test
        public void equals_shouldReturnFalse_ifNotInstanceof() {
            BaseEntity baseEntity = createWithId(1);
            CartItem cartItem = new CartItem();

            boolean expected = baseEntity.equals(cartItem);

            assertEquals(false, expected);
        }

        @Test
        public void equals_shouldReturnFalse_ifSourceIsNull() {
            BaseEntity baseEntity = createWithId(1);

            assertEquals(false, baseEntity.equals(null));
        }

        @Test
        public void equals_shouldReturnFalse_ifIdIsDifferent() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(2);

            boolean expected = baseEntity1.equals(baseEntity2);

            assertEquals(false, expected);
        }

        @Test
        public void equals_shouldReturnTrue_ifIdIsSame() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);

            boolean expected = baseEntity1.equals(baseEntity2);

            assertEquals(true, expected);
        }
    }

    private BaseEntity createWithId(Integer id) {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(id);
        return baseEntity;
    }
}
