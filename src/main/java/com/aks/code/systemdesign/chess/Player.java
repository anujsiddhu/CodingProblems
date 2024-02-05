package com.aks.code.systemdesign.chess;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {
    private String name;
    private PiecesColour side;
}
