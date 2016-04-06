package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Comment;

@Repository
public interface CommentDAO extends PagingAndSortingRepository<Comment, Integer>{

}
