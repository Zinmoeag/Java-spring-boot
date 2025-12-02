package spring_database.spring_boot_database.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authors {
    private Long id;
    private String name;
    private Integer age;
}
