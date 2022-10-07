package de.workshops.bookshelf.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(BookRestController.REQUEST_URL)
@RequiredArgsConstructor
public class BookRestController {

    static final String REQUEST_URL = "/book";

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> result = bookService.getBooks();

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{isbn}")
    public Book getSingleBook(@PathVariable @NotBlank @Size(min = 10, max = 15) String isbn) throws BookException {
        return bookService.getSingleBook(isbn);
    }

    @GetMapping(params = "author")
    public Book searchBookByAuthor(@RequestParam String author) throws BookException {
        return bookService.searchBookByAuthor(author);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody @Valid BookSearchRequest bookSearchRequest) {
        return bookService.searchBooks(bookSearchRequest);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> error(BookException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }
}
