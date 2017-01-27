package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface UnionTypeDefinition
        extends TypeDefinition, NamedType, Directives {

    Stream<UnionMember> unionMembers();

    boolean hasUnionMembers();
}
