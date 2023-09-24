package com.hb.betterreadsapp.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

	private static final String IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/book/{bookId}")
	public String getBookById(@PathVariable String bookId, Model model) {
		Optional<Book> optBook = bookRepository.findById(bookId);
		if(optBook.isPresent()) {
			Book book = optBook.get();
			if(null != book.getCoverIds() && book.getCoverIds().size()>0) {
				String coverImageUrl = IMAGE_ROOT + book.getCoverIds().get(0)+"-L.jpg";
				model.addAttribute("coverImage", coverImageUrl);
			}
			model.addAttribute("book", book);
			return "book";
		}
		return "book-not-found";
	}
}
