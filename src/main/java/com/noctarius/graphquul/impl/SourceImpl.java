package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;

final class SourceImpl
        implements Source {

    private final int line;
    private final int column;
    private final int tokenId;

    SourceImpl(int line, int column, int tokenId) {
        this.line = line;
        this.column = column;
        this.tokenId = tokenId;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int tokenId() {
        return tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SourceImpl)) {
            return false;
        }

        SourceImpl source = (SourceImpl) o;

        if (line != source.line) {
            return false;
        }
        if (column != source.column) {
            return false;
        }
        return tokenId == source.tokenId;
    }

    @Override
    public int hashCode() {
        int result = line;
        result = 31 * result + column;
        result = 31 * result + tokenId;
        return result;
    }

    @Override
    public String toString() {
        return "SourceImpl{" + "line=" + line + ", column=" + column + ", tokenId=" + tokenId + '}';
    }
}
