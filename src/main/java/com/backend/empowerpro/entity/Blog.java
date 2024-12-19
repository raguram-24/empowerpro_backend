package com.backend.empowerpro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id", nullable = false)
    private Long blogId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", updatable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "search_name", joinColumns = @JoinColumn(name = "blog_id"))
    @Column(name = "search_name")
    private List<String> searchNames;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogComment> comments;


    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddFavouriteBlog> favourites;

@JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogRating> ratings;

@JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogView> views;

    @CreationTimestamp
    @Column(name = "upload_date", updatable = false)
    private LocalDateTime uploadDate;

//    @JsonProperty("commentId")
//    public Long commentId() {
//        return comments != null ? comments.getCommentId() : null;
//    }

//    @JsonProperty("blogId")
//    public Long getBlogId() {
//        return favourites != null ? blog.getBlogId() : null;
//    }
//
//    @JsonProperty("blogId")
//    public Long getBlogId() {
//        return ratings != null ? blog.getBlogId() : null;
//    }
//
//    @JsonProperty("blogId")
//    public Long getBlogId() {
//        return views != null ? blog.getBlogId() : null;
//    }
    public Blog(Long favouriteId, Long blogId, Long userId) {
    }
}
