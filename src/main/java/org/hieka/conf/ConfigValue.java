package org.hieka.conf;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/***
 * ConfigurationProperties
 * 设置加载spring.application下的配置.
 * 如果单独配置了@ConfigurationProperties，而没有配置@Configuration ，不会实例化实例，
 * 因为@Configuration 继承了@Component，所以在这里会实例化。
 */
@Configuration
@ConfigurationProperties(prefix = "spring.application")
@Data
public class ConfigValue {

    private String name;
}
