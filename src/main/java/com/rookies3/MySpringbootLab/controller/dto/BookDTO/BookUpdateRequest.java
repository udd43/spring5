package com.rookies3.MySpringbootLab.controller.dto.BookDTO;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BookUpdateRequest {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "저자는 필수입니다.")
    private String author;
    @NotNull(message = "가격은 필수입니다.")
    @Positive(message = "가격은 양수여야 합니다.")
    private Integer price;
    @NotNull(message = "출판일자는 필수입니다.")
    @PastOrPresent(message = "출판일자는 오늘 이전이어야 합니다.")
    private LocalDate publishDate;
}


