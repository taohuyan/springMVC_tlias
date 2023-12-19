package org.huyantao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringbootwebItheimaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootwebItheimaApplication.class, args);
    }

}
