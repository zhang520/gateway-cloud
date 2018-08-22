package com.xuanwu.xtion.message.rabbitmq.config;

import java.io.Serializable;

public class RabbitConfig implements Serializable {
    private String host;

    private Integer port;

    private String username;

    private String password;

    private String virtualHost;

    private Boolean publisherConfirms;

    public RabbitConfig(String host, int port, String username, String password, String virtualHost) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public Boolean getPublisherConfirms() {
        return publisherConfirms;
    }

    public void setPublisherConfirms(Boolean publisherConfirms) {
        this.publisherConfirms = publisherConfirms;
    }

    @Override
    public String toString() {
        String split = "|";
        return new StringBuilder().append(host).append(split).append(port).append(split).append(username).append(split).append(password)
                .append(split).append(virtualHost).toString();
    }
}
