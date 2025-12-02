package spring_database.spring_boot_database;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class SpringBootDatabaseApplication implements CommandLineRunner {

    private final DataSource dataSource;

    public SpringBootDatabaseApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatabaseApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Hello World");
        log.info("Hello World " + dataSource.toString());
        final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
        restTemplate.execute("select 1");
    }
}
