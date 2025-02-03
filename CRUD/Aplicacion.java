package p1.p1.CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Aplicacion {

    public static void main(String[] args) {
        SpringApplication.run(p1.p1.CRUD.Aplicacion.class, args);
    }
}

