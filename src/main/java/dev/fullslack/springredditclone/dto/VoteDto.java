package dev.fullslack.springredditclone.dto;

import dev.fullslack.springredditclone.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private Long postId;
    private VoteType voteType;
}
