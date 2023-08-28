package com.cachesytem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @Column(updatable=false,nullable = false)
    @JsonProperty("blogId")
    @GeneratedValue
    private int id;

    @JsonProperty("blogTitle")
    private String title;

    @JsonProperty("blogDescription")
    private String description;

    @JsonProperty("blogAuthorName")
    private String authorName;
}
