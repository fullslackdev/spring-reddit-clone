package dev.fullslack.springredditclone.controller;

import dev.fullslack.springredditclone.dto.CommentDto;
import dev.fullslack.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable String username) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(username));
    }
}
