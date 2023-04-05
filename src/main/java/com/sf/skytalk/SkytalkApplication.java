
package com.sf.skytalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.sf.skytalk.mapper")
@SpringBootApplication
public class SkytalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkytalkApplication.class, args);
    }

}
