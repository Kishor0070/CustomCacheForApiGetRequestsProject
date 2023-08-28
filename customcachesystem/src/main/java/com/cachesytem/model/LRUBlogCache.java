package com.cachesytem.model;

import java.util.HashMap;
import java.util.Map;

public class LRUBlogCache {
    private static LRUBlogCache lruCache;
    private int maxCapacity;
    private int curElements;
    private Map<Integer,Node> map;
    private Node cacheList;
    private Node head,tail;

    private LRUBlogCache()
    {
        this.maxCapacity = 4;
        this.curElements = 0;
        this.map = new HashMap<>(4);
        this.cacheList = new Node(-1, null);
        head = cacheList;
        head.next = new Node(-1,null);
        tail = head.next;
    }
    public static LRUBlogCache getInstance(){
        return lruCache==null?new LRUBlogCache():lruCache;
    }

    public void addToCache(int id,Blog blog){
        Node tmp = null;
        if(map.containsKey(id)){
            Node.detachToHead(map.get(id),head,tail);
            map.get(id).value = blog;
        }else{
            if(curElements >= maxCapacity){
                map.remove(tail.prev.key);
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
            }
            tmp = head.next;
            head.next = new Node(id,blog);
            head.next.prev = head;
            head.next.next = tmp;
            tmp.prev = head.next;
            map.put(id,head.next);
            curElements = curElements < maxCapacity?curElements+1:curElements;
        }
    }
    public Blog getFromCache(int id){
        if(!map.containsKey(id)){
            return null;
        }
        Node.detachToHead(map.get(id), head, tail);
        return map.get(id).value;
    }

    public boolean isInCache(int id)
    {
        return map.containsKey(id);
    }

    static class Node{
        int key;
        Blog value;
        Node next,prev;
        public Node(int key,Blog value){
            this.key = key;
            this.value = value;
        }
        public static void detachToHead(Node node,Node head,Node tail){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            Node tmp = head.next;
            head.next = node;
            node.prev = head;
            node.next = tmp;
            tmp.prev = node;
        }
    }
}
