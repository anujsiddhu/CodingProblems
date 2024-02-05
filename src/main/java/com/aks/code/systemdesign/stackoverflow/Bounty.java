package com.aks.code.systemdesign.stackoverflow;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bounty {
    private long reputation;
    private LocalDateTime expiry;
}
