package com.yanjing.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BaseEntityTest {

    @DisplayName("equals四大特性测试")
    @Nested
    class EqualsAttribute {
        @DisplayName("自反性")
        @Test
        public void equals_ifHaveReflexivity() {
            BaseEntity baseEntity = createWithId(1);

            assertEquals(baseEntity, baseEntity);
        }

        @DisplayName("对称性")
        @Test
        public void equals_ifHaveSymmetry() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);

            assertTrue(baseEntity1.equals(baseEntity2));
            assertTrue(baseEntity2.equals(baseEntity1));
        }

        @DisplayName("传递性")
        @Test
        public void equals_ifHaveTransitivity() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);
            BaseEntity baseEntity3 = createWithId(1);

            assertTrue(baseEntity1.equals(baseEntity2));
            assertTrue(baseEntity2.equals(baseEntity3));
            assertTrue(baseEntity1.equals(baseEntity3));
        }

        @DisplayName("一致性")
        @Test
        public void equals_ifHaveConsistency() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);

            assertTrue(baseEntity1.equals(baseEntity2));
            assertTrue(baseEntity1.equals(baseEntity2));
        }

        @DisplayName("非空性")
        @Test
        public void equals_shouldReturnFalse_ifSourceIsNull() {
            BaseEntity baseEntity = createWithId(1);

            assertFalse(baseEntity.equals(null));
        }
    }

    @Nested
    class EqualsMethod {
        @Test
        public void equals_shouldReturnTrue_ifTheSameObject() {
            BaseEntity baseEntity = createWithId(1);

            assertTrue(baseEntity.equals(baseEntity));
        }

        @Test
        public void equals_shouldReturnFalse_ifNotInstanceof() {
            BaseEntity baseEntity = createWithId(1);
            CartItem cartItem = new CartItem();

            boolean expected = baseEntity.equals(cartItem);

            assertFalse(expected);
        }

        @Test
        public void equals_shouldReturnFalse_ifIdIsDifferent() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(2);

            boolean expected = baseEntity1.equals(baseEntity2);

            assertFalse(expected);
        }

        @Test
        public void equals_shouldReturnTrue_ifIdIsSame() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);

            boolean expected = baseEntity1.equals(baseEntity2);

            assertTrue(expected);
        }
    }

    @Nested
    class hashCode {
        @Test
        public void hashCode_shouldNotEqual_whenComparedWithDifferentId() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(2);

            Integer hashCode1 = baseEntity1.hashCode();
            Integer hashCode2 = baseEntity2.hashCode();

            assertNotEquals(hashCode1, hashCode2);
        }

        @Test
        public void hashCode_shouldEqual_whenComparedWithSameId() {
            BaseEntity baseEntity1 = createWithId(1);
            BaseEntity baseEntity2 = createWithId(1);

            Integer hashCode1 = baseEntity1.hashCode();
            Integer hashCode2 = baseEntity2.hashCode();

            assertEquals(hashCode1, hashCode2);
        }
    }

    private BaseEntity createWithId(Integer id) {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(id);
        return baseEntity;
    }
}
