package com.tarun.controller;

import com.tarun.entity.Book;
import com.tarun.service.BookService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService theBookService) {
        bookService = theBookService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listBooks(Model theModel) {

        // get employees from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "list-books";
    }

    @GetMapping("/FormForAdd")
    public String FormForAdd(Model theModel) {

        // create model attribute to bind form data
        Book theBook = new Book();

        theModel.addAttribute("books", theBook);

        return "book-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the employee from the service
        Book theBook = bookService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("books", theBook);

        // send over to our form
        return "book-form";
    }


    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute("book") @Valid Book theBook,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        else {
            // save the employee
            bookService.save(theBook);

            // use a redirect to prevent duplicate submissions
            return "redirect:/books/list";
        }
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {

        // delete the employee
        bookService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/books/list";

    }
    @GetMapping("/search")
    public String findBookByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("books", bookService.searchBy(keyword));
        return "list-books";
    }

}

