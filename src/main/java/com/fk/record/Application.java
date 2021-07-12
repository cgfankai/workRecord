package com.fk.record;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;

@SpringBootApplication
@MapperScan("com.fk.record.mapper")
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        File dataFile = new File("./data.db");
        if (!dataFile.exists()){
            dataFile.createNewFile();
            Resource resource = new DefaultResourceLoader().getResource("classpath:sqls/record.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String sql = "";
            String temp = bufferedReader.readLine();
            while (temp != null){
                sql = sql + " " + temp;
                temp = bufferedReader.readLine();
            }
            System.out.println(sql);
            jdbcTemplate.execute(sql);

        }
    }
}
