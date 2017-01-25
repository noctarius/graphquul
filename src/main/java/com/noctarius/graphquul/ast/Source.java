package com.noctarius.graphquul.ast;

public interface Source {

    int getLine();

    int getColumn();

    int tokenId();
}
