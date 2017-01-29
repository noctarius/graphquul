package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ParserException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;

import java.lang.reflect.Field;
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

    protected Stream<Node> asChildren(Object... children) {
        return Stream.of(children).filter(Objects::nonNull).flatMap(this::asStream).filter(Objects::nonNull);
    }

    private Stream<Node> asStream(Object value) {
        if (value instanceof List) {
            return ((List<Node>) value).stream();
        }
        return Stream.of((Node) value);
    }

    void validate() {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Source.class.isAssignableFrom(field.getType())) {
                continue;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                boolean zeroOrMore = field.isAnnotationPresent(ZeroOrMore.class);
                List<?> list = readField(field);
                if (!zeroOrMore && list.size() == 0) {
                    String message = String.format("At least one element expected in %s", field.getName());
                    throw new ParserException(source, message);
                }
                continue;
            }

            boolean nullable = field.isAnnotationPresent(Optional.class);
            Object value = readField(field);
            if (!nullable && value == null) {
                String message = String.format("Element %s must be set", field.getName());
                throw new ParserException(source, message);
            }
        }

        children().map(c -> (AbstractNode) c).forEach(AbstractNode::validate);
    }

    private <R> R readField(Field field) {
        try {
            field.setAccessible(true);
            return (R) field.get(this);
        } catch (IllegalAccessException e) {
            throw new ParserException(source, "Could not read field", e);
        }
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
