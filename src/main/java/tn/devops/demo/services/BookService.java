package tn.devops.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.devops.demo.entities.Book;
import tn.devops.demo.repositories.IBookRepository;

@Service
public class BookService implements IBookService {
	
	@Autowired
	private IBookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBookById(long id) {
		return this.bookRepository.findById(id);
	}

	@Override
	public void createBook(Book book) {
		this.bookRepository.save(book);
		
	}

	@Override
	public void deleteBookById(long id) {
		 this.bookRepository.deleteById(id);
		
	}

}
