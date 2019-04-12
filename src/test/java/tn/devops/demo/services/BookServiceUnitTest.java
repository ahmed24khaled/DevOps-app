package tn.devops.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import tn.devops.demo.entities.Book;
import tn.devops.demo.repositories.IBookRepository;

@RunWith(SpringRunner.class)


public class BookServiceUnitTest {
	
	//Creation of our beans that will be injected ( bookService in our case)
	@TestConfiguration
	    static class BookServiceUnitTestContextConfiguration {
	  
	        @Bean
	        public IBookService bookeService() {
	            return new BookService();
	        }
	    }
	
	// this is the service that will be tested
	@Autowired
	private IBookService bookservice;
	
	// this is a mock for book repository
	@MockBean
    private IBookRepository bookRepository;
	
	private List<Book> books;
	@Before
	public void setUp() {
		
		//given
	    Book b1 = new Book("serviceTest","g1","description...","author...", "0", "", null, 0);
	    Book b2 = new Book("serviceTest2","g2","description...","author...", "0", "", null, 0);
	    b1.setId((long) 1);
	    b2.setId((long) 2);
	    this.books = new ArrayList<Book> (Arrays.asList(b1,b2));
	  
	    
	    Mockito.when(bookRepository.findAll()).thenReturn(books);
	    
	    Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(b1));
	    
	    Mockito.doAnswer((Answer) invocation -> {
	    	this.books.add(invocation.getArgument(0));
			return null;
	    })
	    .when(bookRepository).save(any(Book.class));
	    
	    Mockito.doAnswer((Answer) invocation -> {
	    	this.books.removeIf( e -> e.getId() == invocation.getArgument(0) );
			return null;
	    })
	    .when(bookRepository).deleteById(any(Long.class));
	}
 
    @Test
    public void getAllBooksTest() {
     
        // when
        List<Book> result = bookservice.getAllBooks();
     
        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getTitle()).isEqualTo("serviceTest");
        assertThat(result.get(1).getTitle()).isEqualTo("serviceTest2");
        
        verify(bookRepository,times(1)).findAll();
    }
    
    @Test
    public void getBookByIdTest() {
     
        // when
        Optional<Book> result = bookservice.getBookById(1);
     
        // then
        assertTrue(result.isPresent());
        assertThat(result.get().getTitle()).isEqualTo("serviceTest");
        
        verify(bookRepository,times(1)).findById(1L);
    }
    
    @Test
    public void createBookTest() {
    	
    	//given
    	Book b = new Book("createBookTestInServiceTest","g1","description...","author...", "0", "", null, 0);
    	b.setId(5L);
     
        // when
        bookservice.createBook(b);
     
        // then
        assertThat(this.books).contains(b);
        
        verify(bookRepository,times(1)).save(any());
    }
    
    @Test
    public void deleteBookTest() {
    	
    	Book b = this.books.get(0);
    	
        // when
        bookservice.deleteBookById(b.getId());
     
        // then
        assertThat(this.books).doesNotContain(b);
        
        verify(bookRepository,times(1)).deleteById(any());
    }
 
}