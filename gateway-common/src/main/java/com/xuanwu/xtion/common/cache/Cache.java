package com.xuanwu.xtion.common.cache;

public interface Cache {

    /**
     * key-value set.
     * @param key
     * @param value
     * @param timeout second
     */
    void set(String key, Object value, long timeout);

    /**
     * key-value get.
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * key-value delete.
     * @param key
     */
    void delete(String key);

    /**
     * hash set.
     * @param key
     * @param field
     * @param value
     */
    void put(String key, String field, Object value);

    /**
     * hash get.
     * @param key
     * @param field
     * @return
     */
    Object get(String key, String field);

    /**
     * hash delete.
     * @param key
     * @param field
     */
    void delete(String key, String... fields);
}
