package com.tarun.service;


import com.tarun.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public Book findById(int theId);

    public void save(Book theBook);

    public void deleteById(int theId);

    public List<Book> searchBy(String theBookName);
}
