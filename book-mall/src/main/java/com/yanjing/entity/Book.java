package com.yanjing.entity;

import com.yanjing.constants.BookStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Book entity
 *
 * @author yanjing
 * @date 2021-01-20
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "书名不能为空")
    private String name;

    @NotEmpty(message = "作者不能为空")
    private String author;

    @NotEmpty(message = "ISBN不能为空")
    private String isbn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publish_date")
    private Date publishDate;

    @NotNull(message = "价格不能为空")
    private Double price;

    private String cover;

    @Column(name = "book_abstract")
    private String bookAbstract;

    @Enumerated(EnumType.STRING)
    private BookStatus status;
}
