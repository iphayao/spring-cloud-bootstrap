package com.iphayao.bookservice;

import com.iphayao.bookservice.book.Book;
import com.iphayao.bookservice.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private BookService bookService;

    @Autowired
    public DataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.bookService.createBook(create("Brave new world", "Aldous Huxley"));
        this.bookService.createBook(create("Animal Farm", "George Orwell"));
    }

    private Book create(String title, String author) {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
