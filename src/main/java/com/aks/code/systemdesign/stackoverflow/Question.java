package com.aks.code.systemdesign.stackoverflow;

import lombok.Data;

import java.util.List;

@Data
public class Question extends Content {
    private String title;
    private long upVotes;
    private List<Comment> comments;
    private List<Answer> answers;
    private List<Tag> tags;
}
