package tn.devops.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import tn.devops.demo.entities.Book;

@RunWith(SpringRunner.class)

//@DataJpaTest for IntegrationTest persistence layer
@DataJpaTest

/**
 * in order to use configurations of DataSource that specified in application-test.properties rather then override them 
 * and use H2 DB uncomment the following annotations
@ActiveProfiles("application-test")
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) 
*/



// BookRepositoryIntegrationTest
public class BookRepositoryIT {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private IBookRepository bookRepository;
 
    @Test
    public void FindBookByTitleTest() {
        // given
        Book b = new Book("RepositoryTest","g1","description...","author...", "0", "", null, 0);
        
        entityManager.persist(b);
        entityManager.flush();
     
        // when
        Book found = bookRepository.findByTitle(b.getTitle());
     
        // then
        assertThat(found).isEqualTo(b);
    }
 
}