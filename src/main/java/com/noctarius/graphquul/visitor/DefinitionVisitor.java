package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.Source;

public interface DefinitionVisitor
        extends Visitor {

    void beginVisitOperation(Source source);

    void endVisitOperation(Source source, String name);

}
