package me.robbie.spring.grovvy.test;

import me.robbie.spring.grovvy.dao.TestDao;
import me.robbie.spring.grovvy.model.RuleResult;
import me.robbie.spring.grovvy.service.TestService;
import me.robbie.spring.grovvy.service.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * mock 方法测试 静态和私有方法
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/25 上午11:06
 * @since [产品/模块版本]
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtil.class,TestService.class})
public class Service2Test {

    @Mock
    private TestDao testDao;

    @InjectMocks
    private TestService testService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RuleResult ok1 = RuleResult.getSuccess("ok1");
        RuleResult ok2 = RuleResult.getSuccess("ok2");
        RuleResult error = RuleResult.getError("err");

        when(testDao.get(1)).thenReturn(ok1);
        when(testDao.get(2)).thenReturn(ok2);
        when(testDao.get(3)).thenReturn(error);


        mockStatic(TestUtil.class);
        when(TestUtil.getAbc(anyString())).thenReturn("1111");
    }

    @Test
    public void getDummyNameUpperCase() {
        RuleResult result1 = testService.get(1);
        verify(testDao, times(1)).get(1);
        assertThat(result1.getMsg(), is("ok1"));

        String value = testService.update(result1);
        verifyStatic(TestUtil.class, times(1));

        assertThat(value, equalTo("1111"));
    }


    @Test
    public void getDummyNameCase() {
        when(testDao.get(anyInt())).thenAnswer(new Answer<RuleResult>() {
            @Override
            public RuleResult answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();

                return RuleResult.getSuccess("called with arguments: " + args);
            }
        });

        System.out.println(testDao.get(5).toString());
    }


    @Test
    public void getPrivateCase() throws Exception {

        TestService powerMock = PowerMockito.spy(testService);

        PowerMockito.when(powerMock, "getSaveId", anyString()).thenReturn("mock");

        RuleResult ok1 = RuleResult.getSuccess("ok1");

        String abc = powerMock.save(ok1);

        System.out.println(abc);
    }


}
