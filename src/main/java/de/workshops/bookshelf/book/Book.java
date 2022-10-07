package de.workshops.bookshelf.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String title;
    private String description;
    private String author;

    @NotBlank
    @Size(min = 10, max = 15)
    private String isbn;
}
