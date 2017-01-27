package com.noctarius.graphquul;

public class IllegalParserArgumentException
        extends ParserException {

    public IllegalParserArgumentException(Source source, String message) {
        super(source, message);
    }

    public IllegalParserArgumentException(Source source, String message, Throwable cause) {
        super(source, message, cause);
    }
}
