package me.robbie.spring.grovvy.test;

import me.robbie.spring.grovvy.dao.TestDao;
import me.robbie.spring.grovvy.model.RuleResult;
import me.robbie.spring.grovvy.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * mock 方法测试
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/25 上午11:06
 * @since [产品/模块版本]
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Run.class)
public class ServiceTest {

    @Mock
    private TestDao testDao;

    @InjectMocks
    //@Autowired //注入系统的testService
    private TestService testService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RuleResult ok1 = RuleResult.getSuccess("ok1");
        RuleResult ok2 = RuleResult.getSuccess("ok2");
        RuleResult error = RuleResult.getError("err");


        when(testDao.get(anyInt())).thenReturn(error);
        when(testDao.get(1)).thenReturn(ok1);
        when(testDao.get(2)).thenReturn(ok2);

    }

    @Test
    public void getDummyNameUpperCase() {

        RuleResult result1 = testService.get(1);

        verify(testDao, times(1)).get(1);

        assertThat(result1.getMsg(), is("ok1"));


        RuleResult result3 = testService.get(3);

        verify(testDao, times(1)).get(3);

        assertThat(result3.getMsg(), is("err"));
    }

}
