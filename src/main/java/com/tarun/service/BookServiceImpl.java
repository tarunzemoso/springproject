package com.tarun.service;

import com.tarun.entity.Book;
import com.tarun.exception.NotFoundException;
import com.tarun.dao.BookRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAllByOrderByBookSubnameAsc();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }


    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }


    @Override
    public List<Book> searchBy(String theBookName) {

        return bookRepository.
                findByBookNameContainsAndBookSubnameContainsAllIgnoreCase(theBookName, theSubName);
    }
}
