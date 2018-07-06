package me.robbie.spring.grovvy.script;

import groovy.lang.GroovyClassLoader;
import me.robbie.spring.grovvy.dao.RuleTemplateDao;
import me.robbie.spring.grovvy.model.RuleTemplate;
import me.robbie.spring.grovvy.model.StoreType;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:32
 * @since [产品/模块版本]
 */
@Component
public class RuleEngineFactory implements ApplicationContextAware {

    GroovyClassLoader gcl = new GroovyClassLoader(this.getClass().getClassLoader());

    Map<String, Class> classMap = new HashMap<>();

    @Autowired
    private RuleTemplateDao ruleTemplateDao;

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public RuleEngine getEngine(RuleTemplate template) {
        try {

            String script = null;
            switch (template.getStoreType()) {
                case NET:
                    UrlResource resource = new UrlResource(new URL(template.getTemplate()));
                    InputStream net = resource.getInputStream();
                    if (net == null) {
                        return null;
                    }
                    script = FileCopyUtils.copyToString(new InputStreamReader(net));
                    break;
                case LOCAL:
                    InputStream is = gcl.getResourceAsStream(template.getTemplate());
                    if (is == null) {
                        return null;
                    }

                    script = FileCopyUtils.copyToString(new InputStreamReader(is));
                    break;
                case DB:

                    script = ruleTemplateDao.getTemplate(template.getTemplate());
                    break;
                case STR:
                default:
                    script = template.getTemplate();
            }


            if (StringUtils.isEmpty(script)) {
                return null;
            }

            String md5 = DigestUtils.md5Hex(script);
            Class clazz = null;
            if (classMap.containsKey(md5)) {
                clazz = classMap.get(md5);
            } else {
                clazz = gcl.parseClass(script, md5 + ".groovy");
                classMap.put(md5, clazz);
            }

            RuleEngine engine = (RuleEngine) clazz.newInstance();
            engine.setApplication(applicationContext);

            return engine;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("文件解析出错");
    }

    @PreDestroy
    public void destroy(){
        classMap.clear();
        gcl.clearCache();
        System.out.println("RuleEngineFactory destroy");
    }
}
