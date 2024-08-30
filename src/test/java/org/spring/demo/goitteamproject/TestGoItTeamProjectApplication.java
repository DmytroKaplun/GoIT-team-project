package org.spring.demo.goitteamproject;

import org.springframework.boot.SpringApplication;

public class TestGoItTeamProjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(GoItTeamProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
