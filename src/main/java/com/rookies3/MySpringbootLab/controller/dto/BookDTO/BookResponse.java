package com.rookies3.MySpringbootLab.controller.dto.BookDTO;

import com.rookies3.MySpringbootLab.entity.Book;
import lombok.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer price;
    private LocalDate publishDate;

    public static BookResponse from(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getPublishDate()
        );
    }
}

