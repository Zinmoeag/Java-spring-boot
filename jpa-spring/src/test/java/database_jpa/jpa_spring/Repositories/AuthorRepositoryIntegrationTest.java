package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
    private final IAuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(IAuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = AuthorTestUlits.createRamdomTestAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        System.out.println(result.toString() + "hi this is the way");
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeCreateManyAndRecaledMany() {
        Author authors1 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.save(authors1);
        Author authors2 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.save(authors2);
        Author authors3 = AuthorTestUlits.createRamdomTestAuthor();
        underTest.save(authors3);

        Iterable<Author> result = underTest.findAll();
        System.out.println(result.toString() + "hi this is the way");
        assertThat(result).hasSize(3);
        assertThat(result).contains(authors1, authors2, authors3);
    }

    @Test
    public void testThatAuthorCanBeUpdateAndRecalled() {
        Author author = AuthorTestUlits.getCreateTestAuthor();
        System.out.println(author.toString() +  "hi this is the way");
        underTest.save(author);

        Author newAuthor = AuthorTestUlits.createRamdomTestAuthor();
        author.setName("david");
        underTest.save(author);

        Optional<Author> result = underTest.findById(newAuthor.getId());
        Iterable<Author> authorsList = underTest.findAll();

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(newAuthor);
        assertThat(authorsList).hasSize(1);
        assertThat(authorsList).doesNotContain(author);
    }

    @Test
    public void testThatAuthorCanBePickByAge() {
        Author author = AuthorTestUlits.createRamdomTestAuthor();
        author.setAge(50);
        underTest.save(author);

        Author author2 = AuthorTestUlits.createRamdomTestAuthor();
        author2.setAge(20);
        underTest.save(author2);

        Author author3 = AuthorTestUlits.createRamdomTestAuthor();
        author3.setAge(80);
        underTest.save(author3);

        Iterable<Author> results = underTest.ageLessThan(40);
        assertThat(results).hasSize(1).contains(author2);
    }

    @Test
    public void playground() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(8);
    }
}
