package me.robbie.spring.grovvy.test;

import me.robbie.spring.grovvy.Run;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * @RunWith(SpringRunner.class) 等价于
 * @RunWith(SpringJUnit4ClassRunner.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class)
@WebAppConfiguration
public class AppTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RestTemplateBuilder builder;

    private MockMvc mvc;

    private RestTemplate restTemplate;



    @Before
    public void setUp() {
        //mvc = MockMvcBuilders.standaloneSetup(new SimpleAction()).build();//new 出来的对象无法注入
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        restTemplate = builder.build();
    }

    @Test
    public void testIndex() throws Exception{
        ResultActions actions =
                mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_PLAIN));
                //.andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Hello World!"));

        System.out.println(actions.andReturn().getResponse().getContentAsString());
    }


    @Test
    public void testDoNot() throws Exception{
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/net",String.class);
        System.out.println(entity.getBody());
    }
}
