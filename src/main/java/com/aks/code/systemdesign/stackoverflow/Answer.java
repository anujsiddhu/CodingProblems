package com.aks.code.systemdesign.stackoverflow;

import lombok.Data;

import java.util.List;

@Data
public class Answer extends Content {
    private boolean solvedProblem;
    private long upVotes;
    private List<Comment> comments;
}
