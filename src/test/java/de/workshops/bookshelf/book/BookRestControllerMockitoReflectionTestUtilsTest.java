package de.workshops.bookshelf.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BookRestControllerMockitoReflectionTestUtilsTest {

    private BookRepository bookRepository;

    private BookRestController bookRestController;

    @BeforeEach
    void init() {
        bookRepository = new BookRepository(new ObjectMapper());
        bookRestController = new BookRestController(new BookService(bookRepository));
    }

    @Test
    void getAllBooks() {
        ReflectionTestUtils.setField(bookRepository, "books", Collections.singletonList(new Book()));

        assertNotNull(bookRestController.getAllBooks().getBody());
        assertEquals(1, bookRestController.getAllBooks().getBody().size());
    }
}
