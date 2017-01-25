package com.noctarius.graphquul.ast;

public enum OperationType {
    QUERY, MUTATION;

    public static OperationType byValue(String value) {
        return "mutation".equals(value) ? MUTATION : QUERY;
    }
}
