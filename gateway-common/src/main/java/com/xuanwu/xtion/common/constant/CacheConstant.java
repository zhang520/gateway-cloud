package com.xuanwu.xtion.common.constant;

public enum CacheConstant {

    RABBIT_CONFIG_CACHE_KEY("RABBIT_CONFIG_CACHE_KEY");

    private String key;

    private CacheConstant(String key) {
        this.key = key;
    }

    public String value() {
        return this.key;
    }
}
