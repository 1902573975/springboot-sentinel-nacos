package org.hieka.conf;

import org.hieka.wsdl.WeatherInterface;
import org.hieka.wsdl.impl.WeatherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;

@SpringBootConfiguration
public class WSDLConfig {

    @Value("${wsdl.port}")
    private int port;

    /**
     * 发布wsdl接口
     * http://127.0.0.1:12345/WeatherImpl?wsdl
     * @param weatherService
     * @return
     */
    @Bean
    @Autowired
    public Endpoint publish(WeatherInterface weatherService){
        String path ="http://127.0.0.1:"+port+"/"+WeatherImpl.class.getSimpleName();
        System.out.println(path);
        return Endpoint.publish(path,weatherService);
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
