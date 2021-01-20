package dev.fullslack.springredditclone.service;

import dev.fullslack.springredditclone.dto.PostRequest;
import dev.fullslack.springredditclone.dto.PostResponse;
import dev.fullslack.springredditclone.exception.PostNotFoundException;
import dev.fullslack.springredditclone.exception.SubredditNotFoundException;
import dev.fullslack.springredditclone.mapper.PostMapper;
import dev.fullslack.springredditclone.model.Post;
import dev.fullslack.springredditclone.model.Subreddit;
import dev.fullslack.springredditclone.model.User;
import dev.fullslack.springredditclone.repository.PostRepository;
import dev.fullslack.springredditclone.repository.SubredditRepository;
import dev.fullslack.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        Post savedPost = postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));

        subreddit.getPosts().add(savedPost);
        subredditRepository.save(subreddit);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId));
        return postRepository.findAllBySubreddit(subreddit)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
