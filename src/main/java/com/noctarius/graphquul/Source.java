package com.noctarius.graphquul;

public interface Source {

    int getLine();

    int getColumn();

    int tokenId();
}
