package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.Source;
import com.noctarius.graphquul.visitor.StreamingVisitor;
import com.noctarius.graphquul.ast.ASTVisitorException;
import com.noctarius.graphquul.visitor.DocumentVisitor;
import com.noctarius.graphquul.visitor.Visitor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

class ASTStreamingVisitor
        implements StreamingVisitor {

    private Deque<Object> stack = new ArrayDeque<>();

    private final Visitor[] visitors;

    ASTStreamingVisitor(Visitor[] visitors) {
        this.visitors = visitors;
    }

    @Override
    public void visitDocument(int line, int column, int tokenId) {
        stack.add(new DocumentImpl(source(line, column, tokenId)));
        System.err.println("!!! Document: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endDocument(int tokenId) {
        if (!isStackTopOfType(Document.class)) {
            throw new ASTVisitorException("No Document element on top of AST");
        }
        Document document = stackPop();
        notifiyVisitors(document, IS_DOCUMENT, DOCUMENT);
        if (stack.size() != 0) {
            throw new ASTVisitorException("Stack not empty on end of document");
        }
        System.err.println("!!! Document end: " + tokenId);
    }

    @Override
    public void visitOperation(int line, int column, int tokenId) {
        visitOperation(line, column, tokenId, OperationType.QUERY, null);
    }

    @Override
    public void visitOperation(int line, int column, int tokenId, OperationType operationType, String name) {
        System.err.println("!!! Operation: " + line + ":" + column + ", tokenId: " + tokenId +
                ", operationType: " + operationType + ", name: " + name);
    }

    @Override
    public void endOperation(int tokenId) {
        System.err.println("!!! Operation end: " + tokenId);
    }

    @Override
    public void visitSelectionSet(int line, int column, int tokenId) {
        System.err.println("!!! SelectionSet: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endSelectionSet(int tokenId) {
        System.err.println("!!! SelectionSet end: " + tokenId);
    }

    @Override
    public void visitField(int line, int column, int tokenId, String name) {
        visitField(line, column, tokenId, name, null);
    }

    @Override
    public void visitField(int line, int column, int tokenId, String name, String alias) {
        System.err.println("!!! Field: " + line + ":" + column + ", tokenId: " + tokenId + ", name: " + name + ", alias: " + alias);
    }

    @Override
    public void endField(int tokenId) {
        System.err.println("!!! Field end: " + tokenId);
    }

    @Override
    public void visitFragmentSpread(int line, int column, int tokenId, String fragmentName) {
        System.err.println("!!! FragmentSpread: " + line + ":" + column + ", tokenId: " + tokenId + ", fragmentName: " + fragmentName);
    }

    @Override
    public void endFragmentSpread(int tokenId) {
        System.err.println("!!! FragmentSpread end: " + tokenId);
    }

    @Override
    public void visitInlineFragment(int line, int column, int tokenId, String typeCondition) {
        System.err.println("!!! InlineFragment: " + line + ":" + column + ", tokenId: " + tokenId + ", typeCondition: " + typeCondition);
    }

    @Override
    public void endInlineFragment(int tokenId) {
        System.err.println("!!! InlineFragment end: " + tokenId);
    }

    @Override
    public void visitDirectives(int line, int column, int tokenId) {
        System.err.println("!!! Directives: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endDirectives(int tokenId) {
        System.err.println("!!! Directives end: " + tokenId);
    }

    @Override
    public void visitDirective(int line, int column, int tokenId, String name) {
        System.err.println("!!! Directives: " + line + ":" + column + ", tokenId: " + tokenId + ", name: " + name);
    }

    @Override
    public void endDirective(int tokenId) {
        System.err.println("!!! Directive end: " + tokenId);
    }

    @Override
    public void visitArguments(int line, int column, int tokenId) {
        System.err.println("!!! Arguments: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endArguments(int tokenId) {
        System.err.println("!!! Arguments end: " + tokenId);
    }

    @Override
    public void visitArgument(int line, int column, int tokenId, String name) {
        System.err.println("!!! Argument: " + line + ":" + column + ", tokenId: " + tokenId + ", name: " + name);
    }

    @Override
    public void endArgument(int tokenId) {
        System.err.println("!!! Argument end: " + tokenId);
    }

    @Override
    public void visitVariable(int line, int column, int tokenId, String variable) {
        System.err.println("!!! Variable: " + line + ":" + column + ", tokenId: " + tokenId + ", variable: " + variable);
    }

    @Override
    public void visitStringLiteral(int line, int column, int tokenId, String literal) {
        System.err.println("!!! StringLiteral: " + line + ":" + column + ", tokenId: " + tokenId + ", literal: " + literal);
    }

    @Override
    public void visitEnumLiteral(int line, int column, int tokenId, String literal) {
        System.err.println("!!! EnumLiteral: " + line + ":" + column + ", tokenId: " + tokenId + ", literal: " + literal);
    }

    @Override
    public void visitIntLiteral(int line, int column, int tokenId, int literal) {
        System.err.println("!!! IntLiteral: " + line + ":" + column + ", tokenId: " + tokenId + ", literal: " + literal);
    }

    @Override
    public void visitFloatLiteral(int line, int column, int tokenId, double literal) {
        System.err.println("!!! FloatLiteral: " + line + ":" + column + ", tokenId: " + tokenId + ", literal: " + literal);
    }

    @Override
    public void visitBooleanLiteral(int line, int column, int tokenId, String literal) {
        System.err.println("!!! BooleanLiteral: " + line + ":" + column + ", tokenId: " + tokenId + ", literal: " + literal);
    }

    @Override
    public void visitListValue(int line, int column, int tokenId) {
        System.err.println("!!! ListValue: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endListValue(int tokenId) {
        System.err.println("!!! ListValue end: " + tokenId);
    }

    @Override
    public void visitObjectValue(int line, int column, int tokenId) {
        System.err.println("!!! ObjectValue: " + line + ":" + column + ", tokenId: " + tokenId);
    }

    @Override
    public void endObjectValue(int tokenId) {
        System.err.println("!!! ObjectValue end: " + tokenId);
    }

    @Override
    public void visitObjectField(int line, int column, int tokenId, String name) {
        System.err.println("!!! ObjectField: " + line + ":" + column + ", tokenId: " + tokenId + ", name: " + name);
    }

    @Override
    public void endObjectField(int tokenId) {
        System.err.println("!!! ObjectField end: " + tokenId);
    }

    @Override
    public void visitVariableDefinitions(int line, int column, int tokenId) {

    }

    @Override
    public void endVariableDefinitions(int tokenId) {

    }

    @Override
    public void visitVariableDefinition(int line, int column, int tokenId) {

    }

    @Override
    public void endVariableDefinition(int tokenId) {

    }

    @Override
    public void visitDefaultValue(int line, int column, int tokenId) {

    }

    @Override
    public void endDefaultValue(int tokenId) {

    }

    @Override
    public void visitType(int line, int column, int tokenId, String typeName, boolean nullable) {

    }

    @Override
    public void visitListType(int line, int column, int tokenId) {

    }

    @Override
    public void endListType(int tokenId, boolean nullable) {

    }

    @Override
    public void visitFragmentDefinition(int line, int column, int tokenId, String fragmentName, String typeCondition) {

    }

    @Override
    public void endFragmentDefinition(int tokenId) {

    }

    @Override
    public void visitSchemaDefinition(int line, int column, int tokenId) {

    }

    @Override
    public void endSchemaDefinition(int tokenId) {

    }

    @Override
    public void visitOperationTypeDefinition(int line, int column, int tokenId, OperationType operationType, String typeName) {

    }

    @Override
    public void visitScalarTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endScalarTypeDefinition(int tokenId) {

    }

    @Override
    public void visitObjectTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endObjectTypeDefinition(int tokenId) {

    }

    @Override
    public void visitImplementsType(int line, int column, int tokenId, String typeName) {

    }

    @Override
    public void visitFieldDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endFieldDefinition(int tokenId) {

    }

    @Override
    public void visitArgumentsDefinition(int line, int column, int tokenId) {

    }

    @Override
    public void endArgumentsDefinition(int tokenId) {

    }

    @Override
    public void visitInputValueDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endInputValueDefinition(int tokenId) {

    }

    @Override
    public void visitInterfaceTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endInterfaceTypeDefinition(int tokenId) {

    }

    @Override
    public void visitUnionTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endUnionTypeDefinition(int tokenId) {

    }

    @Override
    public void visitUnionMember(int line, int column, int tokenId, String typeName) {

    }

    @Override
    public void visitEnumTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endEnumTypeDefinition(int tokenId) {

    }

    @Override
    public void visitEnumValueDefinition(int line, int column, int tokenId) {

    }

    @Override
    public void endEnumValueDefinition(int tokenId) {

    }

    @Override
    public void visitInputObjectTypeDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endInputObjectTypeDefinition(int tokenId) {

    }

    @Override
    public void visitTypeExtensionDefinition(int line, int column, int tokenId) {

    }

    @Override
    public void endTypeExtensionDefinition(int tokenId) {

    }

    @Override
    public void visitDirectiveDefinition(int line, int column, int tokenId, String name) {

    }

    @Override
    public void endDirectiveDefinition(int tokenId) {

    }

    @Override
    public void visitDirectiveLocation(int line, int column, int tokenId, String name) {

    }

    @Override
    public void visitDirectiveLocations(int line, int column, int tokenId) {

    }

    @Override
    public void endDirectiveLocations(int tokenId) {

    }

    private <T> T stackPop() {
        return (T) stack.pop();
    }

    private boolean isStackTopOfType(Class<? extends Node> type) {
        Object stackTop = stack.peek();
        if (stackTop == null) {
            return false;
        }
        return type.isAssignableFrom(stackTop.getClass());
    }

    private <T> void notifiyVisitors(T element, Predicate<Visitor> filter, BiConsumer<? extends Visitor, T> notifier) {
        for (Visitor visitor : visitors) {
            if (filter.test(visitor)) {
                ((BiConsumer) notifier).accept(visitor, element); // TODO FIX RAW TYPE
            }
        }
    }

    private Source source(int line, int column, int tokenId) {
        return new SourceImpl(line, column, tokenId);
    }

    private static final Predicate<Visitor> IS_DOCUMENT = (v) -> v instanceof DocumentVisitor;
    private static final BiConsumer<DocumentVisitor, Document> DOCUMENT = (v, d) -> v.endVisitDocument(d);
}
