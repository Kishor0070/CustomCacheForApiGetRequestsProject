package com.cachesytem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cachesytem.model.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog,Integer>{
    
}
