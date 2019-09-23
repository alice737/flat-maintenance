package com.flat.flatmaintenance;

import com.flat.flatmaintenance.product.Product;
import com.flat.flatmaintenance.product.ProductRepository;
import com.flat.flatmaintenance.user.User;
import com.flat.flatmaintenance.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, ProductRepository repositoryP) {
        return args -> {
            log.info("Preloading " + repository.save(new User("ala", "infoUa", "Alicja", "Uzar", "uzar@a.pl")));
            log.info("Preloading " + repositoryP.save(new Product()));
            log.info("Preloading " + repositoryP.save(new Product()));
        };
    }
}