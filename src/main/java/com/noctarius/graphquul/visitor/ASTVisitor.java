package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.Document;

public interface ASTVisitor {

    void visitDocument(Document document);

}
