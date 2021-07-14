package org.hieka.wsdl.impl;

import org.hieka.wsdl.WeatherInterface;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @WebService 设置服务为web service ,并在  WSDLConfig 类把接口暴露出去
 */
@WebService
@Service
public class WeatherImpl implements WeatherInterface {
    @Override
    public String getUserName(String name) {
        return "hello world !" +name;
    }
}
