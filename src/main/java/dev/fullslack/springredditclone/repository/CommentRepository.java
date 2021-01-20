package dev.fullslack.springredditclone.repository;

import dev.fullslack.springredditclone.dto.CommentDto;
import dev.fullslack.springredditclone.model.Comment;
import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}
