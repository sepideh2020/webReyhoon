package com.exmpel.resturan.Service;

import com.exmpel.resturan.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
