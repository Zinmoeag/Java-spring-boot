package spring_database.spring_boot_database.daos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import spring_database.spring_boot_database.domain.Authors;
import spring_database.spring_boot_database.domain.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO implements IBookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Books books) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                books.getIsbn(),
                books.getTitle(),
                books.getAuthor_id()
        );
    }

    @Override
    public void update(String id, Books book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(), book.getTitle(), book.getAuthor_id(), id
        );
    }

    @Override
    public Optional<Books> findById(String isbn) {
        List<Books> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        System.out.println(results.toString());
        System.out.println(results.stream().toString());
        return results.stream().findFirst();
    }

    @Override
    public List<Books> findAll() {
        List<Books> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMapper()
        );
        return results;
    }

    public static class BookRowMapper implements RowMapper<Books> {
        @Override
        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Books.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id(rs.getLong("author_id"))
                    .build();
        }
    }
}
