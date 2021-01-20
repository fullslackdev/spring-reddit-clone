package dev.fullslack.springredditclone.repository;

import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.User;
import dev.fullslack.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
