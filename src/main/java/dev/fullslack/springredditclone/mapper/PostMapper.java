package dev.fullslack.springredditclone.mapper;

import dev.fullslack.springredditclone.dto.PostRequest;
import dev.fullslack.springredditclone.dto.PostResponse;
import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.Subreddit;
import dev.fullslack.springredditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    PostResponse mapToDto(Post post);
}
