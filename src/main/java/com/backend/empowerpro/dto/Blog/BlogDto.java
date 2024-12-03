package com.backend.empowerpro.dto.Blog;

import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.BlogComment;
import com.backend.empowerpro.entity.BlogRating;
import com.backend.empowerpro.entity.BlogView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDto {
    private Long blogId;
    private Long id;
    private String title;
    private List<String> searchNames;
    private String content;
    private List<BlogComment> comments;
    private List<AddFavouriteBlog> favourites;
    private List<BlogRating> ratings;
    private List<BlogView> views;
    private LocalDateTime uploadDate;

    public BlogDto(Long blogId, Long userId, String title, List<String> searchNames, String content, List<BlogComment> comments, List<BlogRating> ratings, List<BlogView> views, LocalDateTime uploadDate) {
    }
}
