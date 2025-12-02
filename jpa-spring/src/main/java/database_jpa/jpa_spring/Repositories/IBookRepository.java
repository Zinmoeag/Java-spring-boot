package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, String> {
}
