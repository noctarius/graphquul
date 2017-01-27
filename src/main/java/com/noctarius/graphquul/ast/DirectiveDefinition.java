package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface DirectiveDefinition
        extends TypeSystemDefinition, NamedType, Arguments {

    Stream<DirectiveLocation> directiveLocations();

    boolean hasDirectiveLocations();
}
