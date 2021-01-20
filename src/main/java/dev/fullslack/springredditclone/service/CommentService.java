package dev.fullslack.springredditclone.service;

import dev.fullslack.springredditclone.dto.CommentDto;
import dev.fullslack.springredditclone.exception.PostNotFoundException;
import dev.fullslack.springredditclone.mapper.CommentMapper;
import dev.fullslack.springredditclone.model.NotificationEmail;
import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.User;
import dev.fullslack.springredditclone.repository.CommentRepository;
import dev.fullslack.springredditclone.repository.PostRepository;
import dev.fullslack.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = ""; //TODO Implement logic

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final MailService mailService;

    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
        User currentUser = authService.getCurrentUser();
        commentRepository.save(commentMapper.map(commentDto, post, currentUser));

        String message = currentUser.getUsername() + " posted a comment on your post. " + POST_URL;
        sendCommentNotification(message, post.getUser(), currentUser);
    }

    private void sendCommentNotification(String message, User poster, User commenter) {
        mailService.sendMail(new NotificationEmail(commenter.getUsername() +
                " commented on your post", poster.getEmail(), message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findAllByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
