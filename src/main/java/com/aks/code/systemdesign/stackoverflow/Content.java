package com.aks.code.systemdesign.stackoverflow;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Content {
    private long id;
    private Member member;
    private long viewCount;
    private LocalDateTime postedTime;
    private LocalDateTime lastEdited;
    private String text;
    private ContentStatus status;
}
