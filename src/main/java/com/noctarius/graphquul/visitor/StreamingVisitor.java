package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.OperationType;

public interface StreamingVisitor {

    void visitDocument(int line, int column, int tokenId);
    void endDocument(int tokenId);

    void visitOperation(int line, int column, int tokenId);
    void visitOperation(int line, int column, int tokenId, OperationType operationType, String name);
    void endOperation(int tokenId);

    void visitMultiSubElement(int line, int column, int tokenId, MultiSubElementType multiSubElementType);
    void endMultiSubElement(int tokenId, MultiSubElementType multiSubElementType);

    void visitNamedElement(int line, int column, int tokenId, NamedElementType namedElementType, String name);
    void visitNamedElement(int line, int column, int tokenId, NamedElementType namedElementType, String name, String argument);
    void endNamedElement(int tokenId, NamedElementType namedElementType);

    void visitInlineFragment(int line, int column, int tokenId, String typeCondition);
    void endInlineFragment(int tokenId);

    void visitLiteral(int line, int column, int tokenId, LiteralType literalType, String literal);
    void endLiteral(int tokenId, LiteralType literalType);

    void visitListValue(int line, int column, int tokenId);
    void endListValue(int tokenId);

    void visitObjectValue(int line, int column, int tokenId);
    void endObjectValue(int tokenId);

    void visitVariableDefinition(int line, int column, int tokenId);
    void endVariableDefinition(int tokenId);

    void visitDefaultValue(int line, int column, int tokenId);
    void endDefaultValue(int tokenId);

    void visitType(int line, int column, int tokenId, String typeName, boolean nullable);
    void endType(int tokenId);

    void visitListType(int line, int column, int tokenId);
    void endListType(int tokenId, boolean nullable);

    void visitSchemaDefinition(int line, int column, int tokenId);
    void endSchemaDefinition(int tokenId);

    void visitOperationTypeDefinition(int line, int column, int tokenId, OperationType operationType, String typeName);
    void endOperationTypeDefinition(int tokenId);

    void visitEnumValueDefinition(int line, int column, int tokenId);
    void endEnumValueDefinition(int tokenId);

    void visitTypeExtensionDefinition(int line, int column, int tokenId);
    void endTypeExtensionDefinition(int tokenId);

    enum NamedElementType {
        Field, //
        FragmentSpread, //
        Directive, //
        Argument, //
        ObjectField, //
        ScalarTypeDefinition, //
        ObjectTypeDefinition, //
        FieldDefinition, //
        InputValueDefinition, //
        InterfaceTypeDefinition, //
        UnionTypeDefinition, //
        EnumTypeDefinition, //
        InputObjectTypeDefinition, //
        DirectiveDefinition, //
        DirectiveLocation, //
        FragmentDefinition, //
        UnionMember, //
        ImplementsInterface
    }

    enum MultiSubElementType {
        Selections, //
        Directives, //
        Arguments, //
        VariableDefinitions, //
        DirectiveLocations, //
        ArgumentDefinitions
    }

    enum LiteralType {
        Variable, //
        String, //
        EnumOrName, //
        Integer, //
        Float, //
        Boolean
    }
}
