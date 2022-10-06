package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    private BookRestController bookRestController;

    @Test
    void getAllBooks() {
        assertEquals(3, Objects.requireNonNull(bookRestController.getAllBooks().getBody()).size());
    }
}
