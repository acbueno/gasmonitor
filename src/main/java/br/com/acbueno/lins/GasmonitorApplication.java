package br.com.acbueno.lins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GasmonitorApplication {

  public static void main(String[] args) {
    SpringApplication.run(GasmonitorApplication.class, args);
  }

}
