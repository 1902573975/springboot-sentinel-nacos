package org.hieka.services;


import org.hieka.bean.RequestScopeBean;
import org.hieka.bean.SessionScopeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogonService {

    @Resource
    private RequestScopeBean requestScopeBean;

    @Autowired
    @Lazy
    private SessionScopeBean sessionScopeBean;

    public void validBean(){
        System.out.println(requestScopeBean.getId());
        System.out.println(sessionScopeBean.getId());
    }

    public void initBean(){
        requestScopeBean.setId("requestId1");
        sessionScopeBean.setId("sessionId1");
    }

    public void setRequestScopeBean(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
    }

    public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
        this.sessionScopeBean = sessionScopeBean;
    }
}
