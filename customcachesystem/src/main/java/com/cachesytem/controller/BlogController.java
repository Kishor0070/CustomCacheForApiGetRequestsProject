package com.cachesytem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cachesytem.model.Blog;
import com.cachesytem.model.LRUBlogCache;
import com.cachesytem.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    BlogService blogService;
    LRUBlogCache blogCache;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
        blogCache = LRUBlogCache.getInstance();
    }
    @GetMapping
    public ResponseEntity<List<Blog>> getBlogs(){
        List<Blog> list = new ArrayList<>();
        list = blogService.getBlogs();
        return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable("id") int id){
        Blog response;
        if(!blogCache.isInCache(id)){
            response = blogService.getBlog(id);
            if(response==null){
                return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
            }
            blogCache.addToCache(id, response);
        }
        else{
            response = blogCache.getFromCache(id);
        }
        return new ResponseEntity<Blog>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
        if(blogService.addBlog(blog)){
            return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
        }
        return new ResponseEntity<Blog>(blog,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id,@RequestBody Blog blog){
        if(blogService.updateBlog(id, blog)){
            return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
        }
        return new ResponseEntity<Blog>(blog,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id){
        if(blogService.removeBlog(id)){
            return new ResponseEntity<Blog>(HttpStatus.OK);
        }
        return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
    }
}
