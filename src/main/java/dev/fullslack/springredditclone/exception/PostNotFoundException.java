package dev.fullslack.springredditclone.exception;

public class PostNotFoundException extends ItemNotFoundException {

    public PostNotFoundException(Long postId) {
        super("Post Not Found with ID - " + postId.toString());
    }
}
