package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Author;
import database_jpa.jpa_spring.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRespositoryIntegrationTest {
    private final IBookRepository underTest;

    @Autowired
    public BookRespositoryIntegrationTest(IBookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = AuthorTestUlits.createRamdomTestAuthor();
        Book book = BookTestUlits.createRandomBooks(author);
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        System.out.println(result + "hi this is book");

        assertThat(result.isPresent()).isTrue();
    }
}
