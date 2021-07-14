package org.hieka.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hieka.bean.CommBean;
import org.hieka.bean.RequestScopeBean;
import org.hieka.bean.SessionScopeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @SpringBootConfiguration 注解相当于spring里面的一个配置文件
 */
@SpringBootConfiguration
public class AutoConfigMain {

    /**
     * 设置作用域， 并添加lazy
     * @return
     */
    @Bean
    @Lazy
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestScopeBean getRequestBean(){
        return new RequestScopeBean();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionScopeBean getSessionBean(){
        return new SessionScopeBean();
    }


    /** @ConditionalOnProperty
     * 根据配置 来确定是否需要加载
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "org.hieka",name = "commBean", havingValue = "true")
    @Primary
    public CommBean getCommonBean(){
        System.out.println("doing");
        return new CommBean();
    }

    /**
     * 动态设置序列化配置
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter custJacksonM(){
        MappingJackson2HttpMessageConverter converter =new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper =new ObjectMapper();
        JavaTimeModule javaTimeModule =new JavaTimeModule();
        //在数据的前后端交互时使用到了
        javaTimeModule.addSerializer(Date.class,new DateSerializer(false,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(Date.class,new DateDeserializers.DateDeserializer());

        mapper.registerModule(javaTimeModule);
        converter.setObjectMapper(mapper);

        return converter;
    }

}
