package com.booksapi.booksAPI.service;

import com.booksapi.booksAPI.entity.Book;
import com.booksapi.booksAPI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    // Get all books
    public List<Book> getAllBooks(){
        try{
            return bookRepository.findAll();
        } catch(Exception e){
            throw new RuntimeException("Failed to fetch all the books!!" + e.getMessage());
        }
    }


    // Get a book by Id
    public Optional<Book> getBookById(Long id){
        try{
            return bookRepository.findById(id);
        } catch(Exception e){
            throw new RuntimeException("Failed to get book : " + e.getMessage());
        }
    }


    // Save a book
    public Book saveBook(Book book){
        try{
            return bookRepository.save(book);
        } catch(Exception e){
            throw new RuntimeException("Failed to save book : " + e.getMessage());
        }
    }


    // Delete a book
    public void deleteBook(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id : " + id);
        }
    }


    // Update a book
    public Optional<Book> updateBook(Long id, Book updatedBook){
        try {
            Optional<Book> existingBookOptional = bookRepository.findById(id);
            if(existingBookOptional.isPresent()){
               Book existingProduct = existingBookOptional.get();
               existingProduct.setTitle(updatedBook.getTitle());
               existingProduct.setAuthor(updatedBook.getAuthor());
               existingProduct.setIsbn(updatedBook.getIsbn());
               Book savedBook = bookRepository.save(existingProduct);
               return Optional.of(savedBook);
            } else {
                return Optional.empty();
            }
        } catch(Exception e){
            throw new RuntimeException("Failed to update product!!" + e.getMessage());
        }
    }

}
