package com.youlintech.zodiac.chatai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author adc
 */
@SpringBootApplication
@MapperScan("com.youlintech.zodiac.chatai.mapper")
public class ZodiacChatAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZodiacChatAiApplication.class, args);
    }

}
