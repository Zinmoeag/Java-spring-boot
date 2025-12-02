package spring_database.spring_boot_database.dao;

import spring_database.spring_boot_database.domain.Authors;

import java.util.Random;

public class AuthorTestUlits {
    public static Authors getCreateTestAuthor() {
        return Authors.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Authors createRamdomTestAuthor() {
        Random random = new Random();
        long id = random.nextLong();
        return Authors.builder()
                .id(id)
                .name("name " + id)
                .age(80)
                .build();
    }

}
