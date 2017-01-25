package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.OperationType;

public interface StreamingVisitor {

    void visitDocument(int line, int column, int tokenId);

    void endDocument(int tokenId);

    void visitOperation(int line, int column, int tokenId);

    void visitOperation(int line, int column, int tokenId, OperationType operationType, String name);

    void endOperation(int tokenId);

    void visitSelectionSet(int line, int column, int tokenId);

    void endSelectionSet(int tokenId);

    void visitField(int line, int column, int tokenId, String name);

    void visitField(int line, int column, int tokenId, String name, String alias);

    void endField(int tokenId);

    void visitFragmentSpread(int line, int column, int tokenId, String fragmentName);

    void endFragmentSpread(int tokenId);

    void visitInlineFragment(int line, int column, int tokenId, String typeCondition);

    void endInlineFragment(int tokenId);

    void visitDirectives(int line, int column, int tokenId);

    void endDirectives(int tokenId);

    void visitDirective(int line, int column, int tokenId, String name);

    void endDirective(int tokenId);

    void visitArguments(int line, int column, int tokenId);

    void endArguments(int tokenId);

    void visitArgument(int line, int column, int tokenId, String name);

    void endArgument(int tokenId);

    void visitVariable(int line, int column, int tokenId, String variable);

    void visitStringLiteral(int line, int column, int tokenId, String literal);

    void visitEnumLiteral(int line, int column, int tokenId, String literal);

    void visitIntLiteral(int line, int column, int tokenId, int literal);

    void visitFloatLiteral(int line, int column, int tokenId, double literal);

    void visitBooleanLiteral(int line, int column, int tokenId, String literal);

    void visitListValue(int line, int column, int tokenId);

    void endListValue(int tokenId);

    void visitObjectValue(int line, int column, int tokenId);

    void endObjectValue(int tokenId);

    void visitObjectField(int line, int column, int tokenId, String name);

    void endObjectField(int tokenId);

    void visitVariableDefinitions(int line, int column, int tokenId);

    void endVariableDefinitions(int tokenId);

    void visitVariableDefinition(int line, int column, int tokenId);

    void endVariableDefinition(int tokenId);

    void visitDefaultValue(int line, int column, int tokenId);

    void endDefaultValue(int tokenId);

    void visitType(int line, int column, int tokenId, String typeName, boolean nullable);

    void visitListType(int line, int column, int tokenId);

    void endListType(int tokenId, boolean nullable);

    void visitFragmentDefinition(int line, int column, int tokenId, String fragmentName, String typeCondition);

    void endFragmentDefinition(int tokenId);

    void visitSchemaDefinition(int line, int column, int tokenId);

    void endSchemaDefinition(int tokenId);

    void visitOperationTypeDefinition(int line, int column, int tokenId, OperationType operationType, String typeName);

    void visitScalarTypeDefinition(int line, int column, int tokenId, String name);

    void endScalarTypeDefinition(int tokenId);

    void visitObjectTypeDefinition(int line, int column, int tokenId, String name);

    void endObjectTypeDefinition(int tokenId);

    void visitImplementsType(int line, int column, int tokenId, String typeName);

    void visitFieldDefinition(int line, int column, int tokenId, String name);

    void endFieldDefinition(int tokenId);

    void visitArgumentsDefinition(int line, int column, int tokenId);

    void endArgumentsDefinition(int tokenId);

    void visitInputValueDefinition(int line, int column, int tokenId, String name);

    void endInputValueDefinition(int tokenId);

    void visitInterfaceTypeDefinition(int line, int column, int tokenId, String name);

    void endInterfaceTypeDefinition(int tokenId);

    void visitUnionTypeDefinition(int line, int column, int tokenId, String name);

    void endUnionTypeDefinition(int tokenId);

    void visitUnionMember(int line, int column, int tokenId, String typeName);

    void visitEnumTypeDefinition(int line, int column, int tokenId, String name);

    void endEnumTypeDefinition(int tokenId);

    void visitEnumValueDefinition(int line, int column, int tokenId);

    void endEnumValueDefinition(int tokenId);

    void visitInputObjectTypeDefinition(int line, int column, int tokenId, String name);

    void endInputObjectTypeDefinition(int tokenId);

    void visitTypeExtensionDefinition(int line, int column, int tokenId);

    void endTypeExtensionDefinition(int tokenId);

    void visitDirectiveDefinition(int line, int column, int tokenId, String name);

    void endDirectiveDefinition(int tokenId);

    void visitDirectiveLocation(int line, int column, int tokenId, String name);

    void visitDirectiveLocations(int line, int column, int tokenId);

    void endDirectiveLocations(int tokenId);
}
