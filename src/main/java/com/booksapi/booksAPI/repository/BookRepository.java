package com.booksapi.booksAPI.repository;

import com.booksapi.booksAPI.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
