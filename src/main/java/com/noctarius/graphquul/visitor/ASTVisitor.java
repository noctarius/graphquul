package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.BooleanLiteral;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.DirectiveDefinition;
import com.noctarius.graphquul.ast.DirectiveLocation;
import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.EnumOrNameLiteral;
import com.noctarius.graphquul.ast.EnumTypeDefinition;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.Field;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.FloatLiteral;
import com.noctarius.graphquul.ast.FragmentDefinition;
import com.noctarius.graphquul.ast.FragmentSpread;
import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.InlineFragment;
import com.noctarius.graphquul.ast.InputObjectTypeDefinition;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.IntegerLiteral;
import com.noctarius.graphquul.ast.InterfaceTypeDefinition;
import com.noctarius.graphquul.ast.ListType;
import com.noctarius.graphquul.ast.ListValue;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectField;
import com.noctarius.graphquul.ast.ObjectTypeDefinition;
import com.noctarius.graphquul.ast.ObjectValue;
import com.noctarius.graphquul.ast.OperationDefinition;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.ast.ScalarTypeDefinition;
import com.noctarius.graphquul.ast.SchemaDefinition;
import com.noctarius.graphquul.ast.StringLiteral;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.ast.TypeExtensionDefinition;
import com.noctarius.graphquul.ast.UnionMember;
import com.noctarius.graphquul.ast.UnionTypeDefinition;
import com.noctarius.graphquul.ast.Variable;
import com.noctarius.graphquul.ast.VariableDefinition;

public interface ASTVisitor {

    void visit(Node node);

    void visit(Document document);

    void visit(Argument argument);

    void visit(BooleanLiteral booleanLiteral);

    void visit(DefaultValue defaultValue);

    void visit(Directive directive);

    void visit(DirectiveDefinition directiveDefinition);

    void visit(DirectiveLocation directiveLocation);

    void visit(EnumOrNameLiteral enumOrNameLiteral);

    void visit(EnumTypeDefinition enumTypeDefinition);

    void visit(EnumValueDefinition enumValueDefinition);

    void visit(Field field);

    void visit(FieldDefinition fieldDefinition);

    void visit(FloatLiteral floatLiteral);

    void visit(FragmentDefinition fragmentDefinition);

    void visit(FragmentSpread fragmentSpread);

    void visit(ImplementsInterface implementsInterface);

    void visit(InlineFragment inlineFragment);

    void visit(InputObjectTypeDefinition inputObjectTypeDefinition);

    void visit(InputValueDefinition inputValueDefinition);

    void visit(IntegerLiteral integerLiteral);

    void visit(InterfaceTypeDefinition interfaceTypeDefinition);

    void visit(ListType listType);

    void visit(ListValue listValue);

    void visit(ObjectField objectField);

    void visit(ObjectTypeDefinition objectTypeDefinition);

    void visit(ObjectValue objectValue);

    void visit(OperationDefinition operationDefinition);

    void visit(OperationTypeDefinition operationTypeDefinition);

    void visit(ScalarTypeDefinition scalarTypeDefinition);

    void visit(SchemaDefinition schemaDefinition);

    void visit(StringLiteral stringLiteral);

    void visit(Type type);

    void visit(TypeExtensionDefinition typeExtensionDefinition);

    void visit(UnionMember unionMember);

    void visit(UnionTypeDefinition unionTypeDefinition);

    void visit(Variable variable);

    void visit(VariableDefinition variableDefinition);
}
