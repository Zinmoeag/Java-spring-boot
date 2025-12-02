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

    @Test
    public void testThatBookCanBeUpdatedAndRecalled() {
        Author author = AuthorTestUlits.getCreateTestAuthor();
        System.out.println(author + "author");
        final String ISBN = "HELO";
        Book book = BookTestUlits.createRandomBooks(author);
        book.setIsbn(ISBN);
        underTest.save(book);
        Book newBook = BookTestUlits.createRandomBooks(author);
        newBook.setIsbn(ISBN);
        newBook.setTitle("update ==>");
        underTest.save(newBook);

        Optional<Book> result = underTest.findById(book.getIsbn());
        Iterable<Book> results = underTest.findAll();

        assertThat(result.isPresent()).isTrue();
        assertThat(results).hasSize(1);
    }

    @Test
    public void testThatBookCanBeDeletedAndRecalled() {
        Author author = AuthorTestUlits.getCreateTestAuthor();
        Book book = BookTestUlits.createRandomBooks(author);
        underTest.save(book);
        underTest.deleteById(book.getIsbn());

        Iterable<Book> results = underTest.findAll();
        assertThat(results).hasSize(0).doesNotContain(book);
    }
}
