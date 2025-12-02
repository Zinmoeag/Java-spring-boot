package spring_database.spring_boot_database.dao;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_database.spring_boot_database.daos.AuthorDAO;
import spring_database.spring_boot_database.daos.BookDAO;
import spring_database.spring_boot_database.domain.Authors;
import spring_database.spring_boot_database.domain.Books;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOIntegrationTest {

    private AuthorDAO authorDAO;
    private BookDAO underTest;

    @Autowired
    public BookDAOIntegrationTest(BookDAO underTest, AuthorDAO authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCreateAndRecalled() {
        Authors authors = AuthorTestUlits.createRamdomTestAuthor();
        authorDAO.create(authors);
        Books book = BookTestUlits.createRandomBooks(authors.getId());
        underTest.create(book);
        Optional<Books> result = underTest.findById(book.getIsbn());

        System.out.println(result.toString() + "hi");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test
    public void testThatBookCreateManyAndRecalledMany() {
        Authors author = AuthorTestUlits.createRamdomTestAuthor();
        authorDAO.create(author);
        Books book1 = BookTestUlits.createRandomBooks(author.getId());
        underTest.create(book1);
        Books book2 = BookTestUlits.createRandomBooks(author.getId());
        underTest.create(book2);
        Books book3 = BookTestUlits.createRandomBooks(author.getId());
        underTest.create(book3);
        List<Books> results = underTest.findAll();

        assertThat(results).hasSize(3).contains(book1, book2, book3);
    }

    @Test
    public void testThatBookUpdateAndRecalled() {
        Authors author = AuthorTestUlits.createRamdomTestAuthor();
        authorDAO.create(author);

        Books book = BookTestUlits.createTestBook();
        book.setAuthor_id(author.getId());
        underTest.create(book);

        Books newBook = BookTestUlits.createRandomBooks(author.getId());
        underTest.update(book.getIsbn(), newBook);

        Optional<Books> bookResult = underTest.findById(newBook.getIsbn());
        List<Books> results = underTest.findAll();

        assertThat(bookResult.isPresent()).isTrue();
        assertThat(results).hasSize(1).contains(newBook);
    }
}
