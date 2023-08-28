package com.cachesytem.model;

import java.util.HashMap;
import java.util.Map;

public class BlogCache {
    private int capacity;
    private int curCapacity;
    private Map<Integer,Blog> map;
    private static BlogCache blogCache;

    private BlogCache(){
        this.capacity = 10;
        this.curCapacity = 0;
        this.map = new HashMap<>();
    }

    public void addToCache(int key,Blog value){
        if(curCapacity<capacity){
            map.put(key,value);
            curCapacity++;
        }
    }
    public void removeFromCache(int key){
        if(map.containsKey(key)){
            map.remove(key);
            curCapacity--;
        }
    }
    public boolean isInCache(int key){
        return map.containsKey(key);
    }

    public Blog getBlog(int key){
        if(map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    public static BlogCache getBlogCache(){
        if(blogCache==null){
            blogCache = new BlogCache();
        }
        return blogCache;
    }
}
