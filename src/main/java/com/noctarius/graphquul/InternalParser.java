package com.noctarius.graphquul;

import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.impl.ASTBuilder;
import com.noctarius.graphquul.visitor.StreamingVisitor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

class InternalParser {

    private static final MethodHandle PARSER_PARSE;
    private static final Source NULL_SOURCE = new Source() {
        @Override
        public int getLine() {
            return 0;
        }

        @Override
        public int getColumn() {
            return 0;
        }

        @Override
        public int tokenId() {
            return 0;
        }
    };

    static {
        try {
            ClassLoader classLoader = InternalParser.class.getClassLoader();
            Class<?> parserType = classLoader.loadClass("com.noctarius.graphquul.impl.GraphQLParser");

            Method method = parserType.getMethod("parse", String.class, StreamingVisitor.class);
            method.setAccessible(true);
            PARSER_PARSE = MethodHandles.publicLookup().unreflect(method);

        } catch (Exception e) {
            throw new ParserException(NULL_SOURCE, "Could not create parser", e);
        }
    }

    static Document parse(String query) {
        try {
            ASTBuilder builder = new ASTBuilder();
            PARSER_PARSE.invokeExact(query, (StreamingVisitor) builder);
            return builder.getDocument();
        } catch (Throwable throwable) {
            if (throwable instanceof ParserException) {
                throw (ParserException) throwable;
            } else if (throwable instanceof Error) {
                throw (Error) throwable;
            }
            throw new ParserException(NULL_SOURCE, "Parsing failed", throwable);
        }
    }
}
