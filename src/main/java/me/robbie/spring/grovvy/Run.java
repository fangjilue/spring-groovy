package me.robbie.spring.grovvy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @SpringBootApplication 集成了
 * @SpringBootConfiguration 等价于Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
@ImportResource("classpath:META-INF/spring/context.xml")
public class Run {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Run.class, args);

        ApplicationArguments applicationArguments = context.getBean(ApplicationArguments.class);
        System.out.println("============Run main===========");
        for(String name : applicationArguments.getOptionNames()){
            System.out.println("name=" + name + ",value="+applicationArguments.getOptionValues(name));
        }

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("beanName=" + beanName);
        }
    }

}