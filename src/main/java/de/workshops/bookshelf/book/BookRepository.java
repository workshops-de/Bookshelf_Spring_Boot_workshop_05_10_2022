package de.workshops.bookshelf.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepository {

    private final ObjectMapper mapper;

    private List<Book> books = Collections.emptyList();

    @PostConstruct
    void init() {
        try {
            this.books = new ArrayList<>(Arrays.asList(mapper.readValue(new File("target/classes/books.json"), Book[].class)));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public Book save(Book book) {
        Book bookToSave = Book.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
        this.books.add(bookToSave);

        return bookToSave;
    }
}
