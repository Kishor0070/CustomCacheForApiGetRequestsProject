package com.cachesytem.service;

import java.util.List;

import com.cachesytem.model.Blog;

public interface BlogService {
    List<Blog> getBlogs();
    boolean addBlog(Blog blog);
    boolean removeBlog(int id);
    boolean updateBlog(int id,Blog blog);
    Blog getBlog(int id);
}
