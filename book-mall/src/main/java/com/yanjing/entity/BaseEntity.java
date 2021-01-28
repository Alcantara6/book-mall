package com.yanjing.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public boolean equals(Object source) {
        if (this == source) {
            return true;
        }
        if (!(source instanceof BaseEntity)) {
            return false;
        }
        return getId().equals(((BaseEntity) source).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
