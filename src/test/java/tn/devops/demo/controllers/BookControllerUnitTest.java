package tn.devops.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import tn.devops.demo.entities.Book;
import tn.devops.demo.services.IBookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {
	
	/*
	 *  MockMvc for testing MVC controllers without starting a full HTTP server
	 */
	
	@Autowired
	private MockMvc mvc;
	
	// this is a mock for book Service
	@MockBean
    private IBookService bookService;
	
	@Before
	public void setUp() {
		
		//given
	    Book b1 = new Book("serviceTest","g1","description...","author...", "0", "", new Date(), 0);
	    Book b2 = new Book("serviceTest2","g2","description...","author...", "0", "", new Date(), 0);
	    b1.setId((long) 1);
	    b2.setId((long) 2);
	   
	    List<Book> books =  new ArrayList<Book> (Arrays.asList(b1,b2));
	   
	    Mockito.when(bookService.getAllBooks()).thenReturn(books);
	    Mockito.when(bookService.getBookById(1)).thenReturn(Optional.of(b1));
	    
	}
 
  
    /*
      In this testCase we will use MockMvc object to perform a get request to our application and to verify that it responds as expected.
     */
	@Test
    public void getAllBooksTest() throws Exception {
     
    	  mvc.perform(get("/books")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$", hasSize(2)))
	    	      .andExpect(jsonPath("$[0].title", is("serviceTest")))
	    	      .andExpect(jsonPath("$[1].title", is("serviceTest2")));
    	  
    }
	
	@Test
    public void getBookByIdTest() throws Exception {
     
    	  mvc.perform(get("/book/1")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.*", hasSize(9)))
	    	      .andExpect(jsonPath("$.id", is(1)))
	    	      .andExpect(jsonPath("$.title", is("serviceTest")));
    	  
    }
	
	@Test
    public void createBookTest() throws Exception {
     
    	  mvc.perform(get("/book/1")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.*", hasSize(9)))
	    	      .andExpect(jsonPath("$.id", is(1)))
	    	      .andExpect(jsonPath("$.title", is("serviceTest")));
    	  
    }
 
}