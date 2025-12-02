package spring_database.spring_boot_database.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import spring_database.spring_boot_database.daos.AuthorDAO;
import spring_database.spring_boot_database.domain.Authors;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDAO underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Authors author = AuthorTestUlits.getCreateTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80)
        );
    }

    @Test
    public void testThatFindByIdAuthorGeneratesCorrectSql() {
        underTest.findById(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDAO.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDAO.AuthorRowMapper>any()
        );
    }
    @Test
    public void testThatUpdateAuthorGeneratesCorrectSql() {
        Authors author = AuthorTestUlits.getCreateTestAuthor();
        underTest.update(3L, author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(author.getId()), eq(author.getName()), eq(author.getAge()), eq(3L)
        );
    }

    @Test
    public void testThatDeleteAuthorGeneratesCorrectSql() {
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"),
                eq(1L)
        );
    }
}