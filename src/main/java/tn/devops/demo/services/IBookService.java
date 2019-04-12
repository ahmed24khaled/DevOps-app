package tn.devops.demo.services;
import java.util.List;
import java.util.Optional;

import tn.devops.demo.entities.Book;

public interface IBookService {
	List<Book> getAllBooks();
	Optional<Book> getBookById(long id);
	void createBook(Book b);
	void deleteBookById(long id);
	
}
