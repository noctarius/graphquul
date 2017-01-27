package com.noctarius.graphquul;

public class IllegalParserStateException
        extends ParserException {

    public IllegalParserStateException(Source source, String message) {
        super(source, message);
    }
}
