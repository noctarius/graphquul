package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface EnumTypeDefinition
        extends TypeDefinition, NamedType, Directives {

    Stream<EnumValueDefinition> enumValueDefinitions();

    boolean hasEnumValueDefinitions();
}
