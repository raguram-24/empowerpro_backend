package com.backend.empowerpro.dto.Blog;

import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.BlogComment;
import com.backend.empowerpro.entity.BlogRating;
import com.backend.empowerpro.entity.BlogView;
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
public class GetBlogDto {
    private Long blogId;
    private Long userId;
    private String title;
    private List<String> searchNames;
    private String content;
    private List<BlogComment> comments;
    private List<AddFavouriteBlog> favourites;
    private List<BlogRating> ratings;
    private List<BlogView> views;
    private LocalDateTime uploadDate;

}
