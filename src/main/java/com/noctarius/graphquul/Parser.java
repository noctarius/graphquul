package com.noctarius.graphquul;

import com.noctarius.graphquul.ast.Document;

public final class Parser {

    public static Document parse(String query) {
        return InternalParser.parse(query);
    }
}
