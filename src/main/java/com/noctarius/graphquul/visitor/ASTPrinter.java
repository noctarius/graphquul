package com.noctarius.graphquul.visitor;

import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.Arguments;
import com.noctarius.graphquul.ast.BooleanLiteral;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.DirectiveDefinition;
import com.noctarius.graphquul.ast.DirectiveLocation;
import com.noctarius.graphquul.ast.Directives;
import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.EnumOrNameLiteral;
import com.noctarius.graphquul.ast.EnumTypeDefinition;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.Field;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.FieldDefinitions;
import com.noctarius.graphquul.ast.FloatLiteral;
import com.noctarius.graphquul.ast.FragmentDefinition;
import com.noctarius.graphquul.ast.FragmentSpread;
import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.ImplementsInterfaces;
import com.noctarius.graphquul.ast.InlineFragment;
import com.noctarius.graphquul.ast.InputObjectTypeDefinition;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.InputValueDefinitions;
import com.noctarius.graphquul.ast.IntegerLiteral;
import com.noctarius.graphquul.ast.InterfaceTypeDefinition;
import com.noctarius.graphquul.ast.ListType;
import com.noctarius.graphquul.ast.ListValue;
import com.noctarius.graphquul.ast.NamedType;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectField;
import com.noctarius.graphquul.ast.ObjectTypeDefinition;
import com.noctarius.graphquul.ast.ObjectValue;
import com.noctarius.graphquul.ast.OperationDefinition;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.ast.OperationTyped;
import com.noctarius.graphquul.ast.ScalarTypeDefinition;
import com.noctarius.graphquul.ast.SchemaDefinition;
import com.noctarius.graphquul.ast.Selections;
import com.noctarius.graphquul.ast.StringLiteral;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.ast.TypeExtensionDefinition;
import com.noctarius.graphquul.ast.UnionMember;
import com.noctarius.graphquul.ast.UnionTypeDefinition;
import com.noctarius.graphquul.ast.Variable;
import com.noctarius.graphquul.ast.VariableDefinition;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class ASTPrinter {

    private ASTPrinter() {
    }

    public static String buildSyntaxTree(Node node) {
        ASTVisitor visitor = new TreeVisitor();
        visitor.visit(node);
        return visitor.toString();
    }

    private static class TreeVisitor
            implements ASTVisitor {

        private final StringBuilder builder = new StringBuilder();
        private int indentation = 0;

        public String toString() {
            return builder.toString();
        }

        @Override
        public void visit(Node node) {
            node.acceptVisitor(this);
        }

        @Override
        public void visit(Document document) {
            printObject(document, d -> {
                if (d.hasDefinitions()) {
                    printProperty("definitions", d.definitions());
                }
            });
        }

        @Override
        public void visit(Argument argument) {
            printObject(argument, a -> printProperty("value", a.value()));
        }

        @Override
        public void visit(BooleanLiteral booleanLiteral) {
            printObject(booleanLiteral, l -> printProperty("value", booleanLiteral.value()));
        }

        @Override
        public void visit(DefaultValue defaultValue) {
            printObject(defaultValue, d -> printProperty("value", defaultValue.value()));
        }

        @Override
        public void visit(Directive directive) {
            printObject(directive);
        }

        @Override
        public void visit(DirectiveDefinition directiveDefinition) {
            printObject(directiveDefinition, d -> {
                if (d.hasDirectiveLocations()) {
                    printProperty("directiveLocations", d.directiveLocations());
                }
            });
        }

        @Override
        public void visit(DirectiveLocation directiveLocation) {
            printObject(directiveLocation);
        }

        @Override
        public void visit(EnumOrNameLiteral enumOrNameLiteral) {
            printObject(enumOrNameLiteral, e -> printProperty("value", e.value()));
        }

        @Override
        public void visit(EnumTypeDefinition enumTypeDefinition) {
            printObject(enumTypeDefinition, e -> {
                if (e.hasEnumValueDefinitions()) {
                    printProperty("enumValueDefinitions", e.enumValueDefinitions());
                }
            });
        }

        @Override
        public void visit(EnumValueDefinition enumValueDefinition) {
            printObject(enumValueDefinition, e -> printProperty("enumValue", e.enumValue()));
        }

        @Override
        public void visit(Field field) {
            printObject(field, f -> printProperty("alias", f.alias()));
        }

        @Override
        public void visit(FieldDefinition fieldDefinition) {
            printObject(fieldDefinition, f -> printProperty("type", f.type()));
        }

        @Override
        public void visit(FloatLiteral floatLiteral) {
            printObject(floatLiteral, f -> printProperty("value", f.value()));
        }

        @Override
        public void visit(FragmentDefinition fragmentDefinition) {
            printObject(fragmentDefinition, f -> printProperty("typeCondition", f.typeCondition()));
        }

        @Override
        public void visit(FragmentSpread fragmentSpread) {
            printObject(fragmentSpread);
        }

        @Override
        public void visit(ImplementsInterface implementsInterface) {
            printObject(implementsInterface);
        }

        @Override
        public void visit(InlineFragment inlineFragment) {
            printObject(inlineFragment, i -> printProperty("typeCondition", i.typeCondition()));
        }

        @Override
        public void visit(InputObjectTypeDefinition inputObjectTypeDefinition) {
            printObject(inputObjectTypeDefinition);
        }

        @Override
        public void visit(InputValueDefinition inputValueDefinition) {
            printObject(inputValueDefinition, i -> {
                printProperty("type", i.type());
                printProperty("defaultValue", i.defaultValue());
            });
        }

        @Override
        public void visit(IntegerLiteral integerLiteral) {
            printObject(integerLiteral, i -> printProperty("value", i.value()));
        }

        @Override
        public void visit(InterfaceTypeDefinition interfaceTypeDefinition) {
            printObject(interfaceTypeDefinition);
        }

        @Override
        public void visit(ListType listType) {
            printObject(listType, l -> printProperty("componentType", l.componentType()));
        }

        @Override
        public void visit(ListValue listValue) {
            printObject(listValue, l -> {
                if (l.hasValues()) {
                    printProperty("values", l.values());
                }
            });
        }

        @Override
        public void visit(ObjectField objectField) {
            printObject(objectField, o -> printProperty("value", o.value()));
        }

        @Override
        public void visit(ObjectTypeDefinition objectTypeDefinition) {
            printObject(objectTypeDefinition);
        }

        @Override
        public void visit(ObjectValue objectValue) {
            printObject(objectValue, o -> {
                if (o.hasObjectFields()) {
                    printProperty("objectFields", o.objectFields());
                }
            });
        }

        @Override
        public void visit(OperationDefinition operationDefinition) {
            printObject(operationDefinition, o -> {
                if (o.hasVariableDefinitions()) {
                    printProperty("variableDefinitions", o.variableDefinitions());
                }
            });
        }

        @Override
        public void visit(OperationTypeDefinition operationTypeDefinition) {
            printObject(operationTypeDefinition);
        }

        @Override
        public void visit(ScalarTypeDefinition scalarTypeDefinition) {
            printObject(scalarTypeDefinition);
        }

        @Override
        public void visit(SchemaDefinition schemaDefinition) {
            printObject(schemaDefinition, s -> {
                if (s.hasOperationTypeDefinitions()) {
                    printProperty("operationTypeDefinitions", s.operationTypeDefinitions());
                }
            });
        }

        @Override
        public void visit(StringLiteral stringLiteral) {
            printObject(stringLiteral, s -> printProperty("value", s.value()));
        }

        @Override
        public void visit(Type type) {
            printObject(type, t -> printProperty("nullable", t.nullable()));
        }

        @Override
        public void visit(TypeExtensionDefinition typeExtensionDefinition) {
            printObject(typeExtensionDefinition, t -> {
                if (t.hasObjectTypeDefinitions()) {
                    printProperty("objectTypeDefinitions", t.objectTypeDefinitions());
                }
            });
        }

        @Override
        public void visit(UnionMember unionMember) {
            printObject(unionMember);
        }

        @Override
        public void visit(UnionTypeDefinition unionTypeDefinition) {
            printObject(unionTypeDefinition, u -> {
                if (u.hasUnionMembers()) {
                    printProperty("unionMembers", u.unionMembers());
                }
            });
        }

        @Override
        public void visit(Variable variable) {
            printObject(variable);
        }

        @Override
        public void visit(VariableDefinition variableDefinition) {
            printObject(variableDefinition, v -> {
                printProperty("variable", v.variable());
                printProperty("type", v.type());
                printProperty("defaultValue", v.defaultValue());
            });
        }

        private <N extends Node> void printStream(Stream<N> stream) {
            stream.forEach(n -> {
                builder.append("\n");
                addIndentation();
                visit(n);
                builder.append(", ");
            });
            removeLastComma();
        }

        private <N extends Node> void printObject(N node) {
            printObject(node, null);
        }

        private <N extends Node> void printObject(N node, Consumer<N> consumer) {
            String type = node.getClass().getSimpleName().replace("Mutable", "");
            builder.append(type).append(" {").append("\n");
            indentation++;
            if (node instanceof NamedType) {
                printProperty("name", ((NamedType) node).name());
            }
            if (node instanceof OperationTyped) {
                printProperty("operationType", ((OperationTyped) node).operationType());
            }
            if (consumer != null) {
                consumer.accept(node);
            }
            if (node instanceof Selections) {
                if (((Selections) node).hasSelections()) {
                    printProperty("selections", ((Selections) node).selections());
                }
            }
            if (node instanceof Directives) {
                if (((Directives) node).hasDirectives()) {
                    printProperty("directives", ((Directives) node).directives());
                }
            }
            if (node instanceof Arguments) {
                if (((Arguments) node).hasArguments()) {
                    printProperty("arguments", ((Arguments) node).arguments());
                }
            }
            if (node instanceof FieldDefinitions) {
                if (((FieldDefinitions) node).hasFieldDefinitions()) {
                    printProperty("fieldDefinitions", ((FieldDefinitions) node).fieldDefinitions());
                }
            }
            if (node instanceof ImplementsInterfaces) {
                if (((ImplementsInterfaces) node).hasImplementsInterfaces()) {
                    printProperty("implementsInterfaces", ((ImplementsInterfaces) node).implementsInterfaces());
                }
            }
            if (node instanceof InputValueDefinitions) {
                if (((InputValueDefinitions) node).hasInputValueDefinitions()) {
                    printProperty("inputValueDefinitions", ((InputValueDefinitions) node).inputValueDefinitions());
                }
            }
            removeLastComma();
            indentation--;
            addIndentation();
            builder.append("}");
        }

        private void printProperty(String property, Object value) {
            if (value == null) {
                return;
            }
            addIndentation();
            builder.append(property).append(": ");
            if (value instanceof Node) {
                visit((Node) value);
            } else if (value instanceof Stream) {
                builder.append("{");
                indentation++;
                printStream((Stream<Node>) value);
                indentation--;
                builder.append("\n");
                addIndentation();
                builder.append("}");
            } else {
                builder.append(value);
            }
            builder.append(",").append("\n");
        }

        private void addIndentation() {
            IntStream.range(0, indentation).forEach(v -> builder.append("\t"));
        }

        private void removeLastComma() {
            for (int i = builder.length() - 1; i >= 0; i--) {
                if (',' == builder.charAt(i)) {
                    builder.deleteCharAt(i);
                    return;
                }
            }
        }
    }
}
