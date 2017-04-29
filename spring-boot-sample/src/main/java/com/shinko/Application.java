package com.shinko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@RestController
public class Application {

    private final Jedis jedis;
    private static final String KEY = "home";

    public Application() {
        jedis = new Jedis("redis");
    }

    @RequestMapping("/")
    public String home() {
        String home = jedis.get(KEY);
        return home != null ? home : "Hello Docker World";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody String message) {
        jedis.set(KEY, message);
        return ResponseEntity.ok("Value updated");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
