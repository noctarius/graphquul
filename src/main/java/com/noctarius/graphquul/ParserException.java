package com.noctarius.graphquul;

public class ParserException
        extends RuntimeException {

    private static final String MESSAGE_FORMAT = "[%s:%s][%s]: %s";

    private final Source source;

    public ParserException(Source source, String message) {
        super(initMessage(message, source));
        this.source = source;
    }

    public ParserException(Source source, String message, Throwable cause) {
        super(initMessage(message, source), cause);
        this.source = source;
    }

    public Source location() {
        return source;
    }

    private static String initMessage(String message, Source source) {
        return String.format(MESSAGE_FORMAT, source.getLine(), source.getColumn(), source.tokenId(), message);
    }
}
