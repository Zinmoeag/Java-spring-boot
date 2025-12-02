package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface IAuthorRepository extends CrudRepository<Author, Long> {
}
