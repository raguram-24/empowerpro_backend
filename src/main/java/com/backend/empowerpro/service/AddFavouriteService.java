package com.backend.empowerpro.service;

import com.backend.empowerpro.dto.Blog.AddFavouriteDto;
import com.backend.empowerpro.entity.AddFavouriteBlog;
import com.backend.empowerpro.entity.Blog;
import com.backend.empowerpro.repository.AddFavouriteRepo;

import java.util.List;

public interface AddFavouriteService {

    String createAddFavouriteBlog(Long userId, Blog blog);


    String deleteAddFavouriteBlog(Long addFavouriteBlogId);

    Long getFavouriteBlog(Long userId, Long blogId) throws Exception;

    String deleteFavouriteBlog(Long userId, Long blogId);

    List<AddFavouriteBlog> getAllFavouriteBlogsByUserId(Long userId);
}
