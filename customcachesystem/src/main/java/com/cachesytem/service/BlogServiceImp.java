package com.cachesytem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cachesytem.model.Blog;
import com.cachesytem.repository.BlogRepository;

@Service
public class BlogServiceImp implements BlogService {
    BlogRepository blogRepository;
    public BlogServiceImp(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getBlogs() {
        List<Blog> list = new ArrayList<>();
        blogRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean addBlog(Blog blog) {
        try{
            this.blogRepository.save(blog);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeBlog(int id) {
        try {
            this.blogRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBlog(int id, Blog blog) {
        try {
            Blog blog1 = this.blogRepository.findById(id).get();
            blog1.setAuthorName(blog.getAuthorName());
            blog1.setTitle(blog.getTitle());
            blog1.setDescription(blog.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Blog getBlog(int id) {
        if(this.blogRepository.existsById(id)){
            return blogRepository.findById(id).get();
        }
        return null;
    }
    
}
