package com.tarun.dao;


import com.tarun.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAllByOrderByBookSubnameAsc();

    public List<Book> findByBookNameContainsAndBookSubnameContainsAllIgnoreCase(
            String theBookName, String theSubName);
}
