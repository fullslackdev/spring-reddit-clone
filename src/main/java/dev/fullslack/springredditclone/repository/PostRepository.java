package dev.fullslack.springredditclone.repository;

import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.Subreddit;
import dev.fullslack.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findAllByUser(User user);
}
