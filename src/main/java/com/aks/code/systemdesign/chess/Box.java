package com.aks.code.systemdesign.chess;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Box {
    private Pieces pieces;
    private int x;
    private int y;
}
