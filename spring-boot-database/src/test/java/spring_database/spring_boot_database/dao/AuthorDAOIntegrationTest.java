package spring_database.spring_boot_database.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_database.spring_boot_database.daos.AuthorDAO;
import spring_database.spring_boot_database.domain.Authors;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDAOIntegrationTest {
    private final AuthorDAO underTest;

    @Autowired
    public AuthorDAOIntegrationTest(AuthorDAO underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Authors author = AuthorTestUlits.getCreateTestAuthor();
        underTest.create(author);
        Optional<Authors> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeCreateManyAndRecaledMany() {
        Authors authors1 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.create(authors1);
        Authors authors2 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.create(authors2);
        Authors authors3 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.create(authors3);

        List<Authors> result = underTest.find();
        assertThat(result).hasSize(3);
        assertThat(result).contains(authors1, authors2, authors3);
    }

    @Test
    public void testThatAuthorCanBeUpdateAndRecalled() {
        Authors author = AuthorTestUlits.getCreateTestAuthor();
        underTest.create(author);

        Authors newAuthor = AuthorTestUlits.createRamdomTestAuthor();
        underTest.update(author.getId(), newAuthor);

        Optional<Authors> result = underTest.findById(newAuthor.getId());
        List<Authors> authorsList = underTest.find();

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(newAuthor);
        assertThat(authorsList).hasSize(1);
        assertThat(authorsList).doesNotContain(author);
    }
}
