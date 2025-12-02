package spring_database.spring_boot_database.daos;

import spring_database.spring_boot_database.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface IAuthorDAO {
    void create(Authors author);
    void update(long l, Authors author);

    Optional<Authors> findById(Long id);

    List<Authors> find();

}
