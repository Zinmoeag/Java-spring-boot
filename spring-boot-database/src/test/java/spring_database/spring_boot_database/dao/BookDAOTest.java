package spring_database.spring_boot_database.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import spring_database.spring_boot_database.daos.BookDAO;
import spring_database.spring_boot_database.domain.Books;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDAO underTest;

    @Test
    public void testThatCreateBookCreateGenerateSql() {
        Books book = BookTestUlits.createTestBook();
        underTest.create(book);

        verify(jdbcTemplate).update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                "dfdfd",
                "helo mello",
                1L
        );
    }

    @Test
    public void testThatFindOneGenerateSql() {
        underTest.findById("sssssssss");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDAO.BookRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindAllGenerateSql() {
        underTest.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDAO.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateBookUpdateGenerateSql() {
        Books book = BookTestUlits.createTestBook();
        underTest.update("hello", book);

        verify(jdbcTemplate).update(
                eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"),
                eq(book.getIsbn()), eq(book.getTitle()), eq(book.getAuthor_id()), eq("hello")
        );
    }
}
