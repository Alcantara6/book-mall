package com.yanjing.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// TODO: 购物车写进数据库
public class CartItem {
    private Book book;
    private Integer quantity;

    void increaseQuantity() {
        this.quantity += 1;
    }

    void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity -= 1;
        }
    }

    Double getSubTotal() {
        return this.book.getPrice() * this.quantity;
    }

    Integer getBookId() {
        return this.book.getId();
    }
}
