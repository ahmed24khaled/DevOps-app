package tn.devops.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.devops.demo.entities.Book;

//integration test of different layers of our application. That also means no mocking is involved.

@RunWith(SpringRunner.class)

/*
 * Because we have a full application context, including web controllers, Spring Data repositories, and data sources, 
 * @SpringBootTest is very convenient for integration tests that go through all layers of the application.
 * It will start up an application context to be used in a test.
 * classes: tell Spring Boot which application class to use to create an application context
 */

@SpringBootTest(classes = DemoApplication.class)

/*
 Uncomment the following annotation and specify the configuration file that will be used for the test
@TestPropertySource(
		  locations = "classpath:application.properties")
*/

//The following annotation will add a MockMvc instance to the application context that will be injected to our "mvc" field 
@AutoConfigureMockMvc
public class DemoApplicationIT {
	
	 @Autowired
	 private MockMvc mvc;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @Test
	 public void createBookThroughAllLayers() throws Exception {
		 Book b1 = new Book("CreateBookThroughAllLayers","g1","description...","author...", "0", "", null, 0);
		 
		 mvc.perform(post("/book")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(b1)))
	            .andExpect(status().isCreated())
	            //to test that result is an object containing 9 members:
	            .andExpect(jsonPath("$.*", hasSize(9)))
	    	    .andExpect(jsonPath("$.title", is("CreateBookThroughAllLayers")));
		 		
		
	 }
	 
	 @Test
	 public void findAllBooksThroughAllLayers() throws Exception {
		 
		 mvc.perform(get("/books")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk());
		
	 }


}
