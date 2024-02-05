package com.aks.code.systemdesign.snakeandladder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Cell {
    private final int position;
    private final int nextPosition;
}
