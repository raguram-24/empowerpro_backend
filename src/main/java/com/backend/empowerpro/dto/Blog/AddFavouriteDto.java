package com.backend.empowerpro.dto.Blog;

import com.backend.empowerpro.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddFavouriteDto {
    private Long userId;
    private Blog blog;
}
