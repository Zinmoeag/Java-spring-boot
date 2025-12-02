package spring_database.spring_boot_database.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {
    private String isbn;
    private String title;
    private Long author_id;
}
