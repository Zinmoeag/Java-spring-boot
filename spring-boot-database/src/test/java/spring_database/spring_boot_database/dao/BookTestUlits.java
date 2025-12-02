package spring_database.spring_boot_database.dao;

import spring_database.spring_boot_database.domain.Books;

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

    public static Books createTestBook() {
        long number = (long)(Math.random() * 1_000_000_000_0000L);
        return Books.builder()
                .isbn("978-" + String.format("%012d", number))
                .title("helo mello")
                .author_id(1L)
                .build();
    }

    public static Books createRandomBooks(Long author_id) {
        String isbn = BookTestUlits.randomISBN13();
        return Books.builder()
                .isbn(isbn)
                .title("helo mello" + isbn)
                .author_id(author_id)
                .build();
    }
}
