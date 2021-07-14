package org.hieka.rest;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hieka.bean.CommBean;
import org.hieka.conf.ConfigValue;
import org.hieka.services.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
public class LogonController {

    @Autowired
    private ConfigValue configValue;

    @Autowired
    private CommBean bean;

    private String name = "";
    private int i=0;

    @Autowired
    private LogonService logonService;

    //如果不加其他get/post注解，默认都支持
    @RequestMapping("/logon")
    public String getLogon(){
        i++;
        System.out.println(i);

        name =name + "This is " + i;

        if(i==1){
            logonService.initBean();
        }else{
            logonService.validBean();
        }
        return name;
    }

    @SentinelResource("getLogon2")
    @RequestMapping("/logon2")
    public String getLogon2(){//注解无效
        name =  "This is " + i;
        return name;
    }

    @RequestMapping("/logon3")
    public String getLogon3(@NonNull String name){//会做校验
        name =name + "This is " + i;
        return name;
    }


    @RequestMapping("/logon4")
    public String getLogon4(){//会做校验
        System.out.println(bean.hashCode());
        return configValue.getName();
    }

    @GetMapping("/date/ser")
    public Date dateFormatSer(){
        Date date =new Date();
        System.out.println(date);
        return  date;
    }

    @GetMapping("/date/deser")
    public String dateDormatDeser(@RequestParam("date")Date date){
        //success
        //Mon Sep 28 00:09:24 CST 2020

        //fail
        //2017-11-30T10:41:44.651Z
        //2020-09-28 00:09:24
        System.out.println(date);
        return "success";
    }

    @PostMapping("/date/deser2")
    public String dateDormatDeser2(@RequestBody CommBean bean){
        //fail
        //Mon Sep 28 00:09:24 CST 2020
        //2020-09-28 00:09:24

        //Success
        //2017-11-30T10:41:44.651Z
        System.out.println(bean.getBirthday());
        return "success";
    }
}
