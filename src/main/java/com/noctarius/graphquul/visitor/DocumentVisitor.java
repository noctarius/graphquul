package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.Source;

public interface DocumentVisitor
        extends Visitor {

    void beginVisitDocument(Source source);

    void endVisitDocument(Document document);

}
