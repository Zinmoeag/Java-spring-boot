package spring_database.spring_boot_database.daos;

import spring_database.spring_boot_database.domain.Books;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    void create(Books books);
    void update(String id, Books book);
    Optional<Books> findById(String isbn);
    List<Books> findAll();

}
