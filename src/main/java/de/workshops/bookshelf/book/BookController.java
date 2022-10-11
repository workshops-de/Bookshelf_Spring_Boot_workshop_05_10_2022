package de.workshops.bookshelf.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BookController.REQUEST_URL)
@RequiredArgsConstructor
@Slf4j
public class BookController {

    static final String REQUEST_URL = "/";

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllBooks(@AuthenticationPrincipal User user, Model model) {
        log.info("User {} is requesting all books.", user.getUsername());
        log.info("User roles: {}", user.getAuthorities().toString());

        model.addAttribute("books", bookService.getBooks());

        return "books";
    }

    @GetMapping("/success")
    public String redirectToSuccessUrl(@AuthenticationPrincipal User user, Model model) {
        return getAllBooks(user, model);
    }
}
