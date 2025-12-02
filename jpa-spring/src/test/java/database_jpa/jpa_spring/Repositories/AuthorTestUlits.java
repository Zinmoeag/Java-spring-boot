package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Author;

import java.util.Random;

public class AuthorTestUlits {
    public static Author getCreateTestAuthor() {
        return Author.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createRamdomTestAuthor() {
        return Author.builder()
                .name("name ")
                .age(80)
                .build();
    }

}
