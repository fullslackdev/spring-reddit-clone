package dev.fullslack.springredditclone.exception;

public class SubredditNotFoundException extends ItemNotFoundException {

    public SubredditNotFoundException(Long subredditId) {
        super("Subreddit Not Found with ID - " + subredditId.toString());
    }

    public SubredditNotFoundException(String subredditName) {
        super("Subreddit Not Found with Name - " + subredditName);
    }
}
