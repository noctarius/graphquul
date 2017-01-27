package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ParserException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;

abstract class AbstractNode
        implements Node {

    private final Source source;

    AbstractNode(Source source) {
        this.source = source;
    }

    @Override
    public Source location() {
        return source;
    }

    @Override
    public void acceptVisitor(ASTVisitor ASTVisitor) {

    }

    protected Stream<Node> asChildren(Object... children) {
        return Stream.of(children).filter(Objects::nonNull).flatMap(this::asStream).filter(Objects::nonNull);
    }

    private Stream<Node> asStream(Object value) {
        if (value instanceof List) {
            return ((List<Node>) value).stream();
        }
        return Stream.of((Node) value);
    }

    <E extends ParserException> E buildParserException(String message, BiFunction<Source, String, E> constructor) {
        return constructor.apply(source, message);
    }

    <E extends ParserException> E buildParserException(String message, Throwable cause,
                                                       TriFunction<Source, String, Throwable, E> constructor) {

        return constructor.apply(source, message, cause);
    }

    interface TriFunction<P1, P2, P3, R> {
        R apply(P1 param1, P2 param2, P3 param3);
    }
}
