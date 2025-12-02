package database_jpa.jpa_spring.Repositories;

import database_jpa.jpa_spring.domain.Author;
import database_jpa.jpa_spring.domain.Book;

import java.util.Random;

public final class BookTestUlits {
    public static String randomISBN13() {
        Random random = new Random();
        int[] digits = new int[13];

        // Generate first 12 digits randomly
        for (int i = 0; i < 12; i++) {
            digits[i] = random.nextInt(10); // 0–9
        }

        // Calculate the ISBN-13 checksum digit
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += digits[i] * ((i % 2 == 0) ? 1 : 3);
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        digits[12] = checkDigit;

        // Build final string
        StringBuilder sb = new StringBuilder();
        for (int d : digits) {
            sb.append(d);
        }

        return sb.toString();
    }

    public static Book createTestBook(Author author) {
        long number = (long)(Math.random() * 1_000_000_000_0000L);
        return Book.builder()
                .isbn("978-" + String.format("%012d", number))
                .title("helo mello")
                .author(author)
                .build();
    }

    public static Book createRandomBooks(Author author) {
        String isbn = BookTestUlits.randomISBN13();
        return Book.builder()
                .isbn(isbn)
                .title("helo mello" + isbn)
                .author(author)
                .build();
    }
}
