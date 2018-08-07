//package com.xuanwu.xtion.common.config;
//
//import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
//import org.glassfish.jersey.server.ResourceConfig;
//
///**
// * Created by Mr.zhang on 2018/5/12.
// * jersey配置注册类
// */
//public class JerseyConfig extends ResourceConfig {
//
//    public JerseyConfig() {
//        this.registerResources();
//    }
//
//    public void registerResources() {
//        super.register(JacksonJaxbJsonProvider.class);
//    }
//
//    public JerseyConfig register(Class<?> componentClass) { super.register(componentClass); return this; }
//
//    public JerseyConfig packages(String packages) { super.packages(packages); return this; }
//}
