package de.workshops.bookshelf.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookSearchRequest {

    @NotBlank
    private String author;

    @NotBlank
    @Size(min = 10, max = 15)
    private String isbn;
}
