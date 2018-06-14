package me.robbie.spring.grovvy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class Cmd implements CommandLineRunner, ApplicationRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====应用已经成功启动=====" + Arrays.asList(args));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("----------------");
        for (String name : args.getOptionNames()) {
            System.out.println("name=" + name + ",value=" + args.getOptionValues(name));
        }
    }
}
