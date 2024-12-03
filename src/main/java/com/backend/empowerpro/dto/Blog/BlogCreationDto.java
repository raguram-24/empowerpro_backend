package com.backend.empowerpro.dto.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogCreationDto {
    private Long blogId;
    private Long id;
    private String title;
    private List<String> searchNames;
    private String content;
}
