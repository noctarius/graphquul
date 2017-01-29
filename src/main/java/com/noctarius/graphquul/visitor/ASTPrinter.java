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
import java.util.function.Function;
import java.util.function.Predicate;
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
            printObject(document, ifDefinitions);
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
            printObject(directiveDefinition, ifDirectiveLocations);
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
            printObject(enumTypeDefinition, ifEnumValueDefinitions);
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
            printObject(listValue, ifValues);
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
            printObject(objectValue, ifObjectFields);
        }

        @Override
        public void visit(OperationDefinition operationDefinition) {
            printObject(operationDefinition, ifVariableDefinitions);
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
            printObject(schemaDefinition, ifOperationTypeDefinitions);
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
            printObject(typeExtensionDefinition, ifObjectTypeDefinitions);
        }

        @Override
        public void visit(UnionMember unionMember) {
            printObject(unionMember);
        }

        @Override
        public void visit(UnionTypeDefinition unionTypeDefinition) {
            printObject(unionTypeDefinition, ifUnionMembers);
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

            // Print name for named types
            if (node instanceof NamedType) {
                printProperty("name", ((NamedType) node).name());
            }

            // Print operation type for operation typed properties
            if (node instanceof OperationTyped) {
                printProperty("operationType", ((OperationTyped) node).operationType());
            }

            // Print specific properties
            if (consumer != null) {
                consumer.accept(node);
            }

            // Print common stream properties
            ifSelections.accept(node);
            ifDirectives.accept(node);
            ifArguments.accept(node);
            ifFieldDefinitions.accept(node);
            ifImplementsInterfaces.accept(node);
            ifInputValueDefinitions.accept(node);

            // Remove last comma to meet JSON standards
            removeLastComma();

            indentation--;
            addIndentation();
            builder.append("}");
        }

        private <N, R> void printStreamProperty(Node node, Class<N> type, Predicate<N> predicate,
                                                Function<N, Stream<R>> transformer) {

            if (type.isAssignableFrom(node.getClass())) {
                N multiSubNode = type.cast(node);
                if (predicate.test(multiSubNode)) {
                    String name = type.getSimpleName();
                    String property = name.substring(0, 1).toLowerCase() + name.substring(1);
                    printProperty(property, transformer.apply(multiSubNode));
                }
            }
        }

        private <N, R> void printStreamProperty(Node node, Class<N> type, String property, Predicate<N> predicate,
                                                Function<N, Stream<R>> transformer) {

            if (type.isAssignableFrom(node.getClass())) {
                N multiSubNode = type.cast(node);
                if (predicate.test(multiSubNode)) {
                    printProperty(property, transformer.apply(multiSubNode));
                }
            }
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

        // Common stream properties
        private final Consumer<Node> ifSelections = node -> //
                printStreamProperty(node, Selections.class, Selections::hasSelections, Selections::selections);

        private final Consumer<Node> ifArguments = node -> //
                printStreamProperty(node, Arguments.class, Arguments::hasArguments, Arguments::arguments);

        private final Consumer<Node> ifDirectives = node -> //
                printStreamProperty(node, Directives.class, Directives::hasDirectives, Directives::directives);

        private final Consumer<Node> ifFieldDefinitions = node -> //
                printStreamProperty(node, FieldDefinitions.class, FieldDefinitions::hasFieldDefinitions,
                        FieldDefinitions::fieldDefinitions);

        private final Consumer<Node> ifImplementsInterfaces = node -> //
                printStreamProperty(node, ImplementsInterfaces.class, ImplementsInterfaces::hasImplementsInterfaces,
                        ImplementsInterfaces::implementsInterfaces);

        private final Consumer<Node> ifInputValueDefinitions = node -> //
                printStreamProperty(node, InputValueDefinitions.class, InputValueDefinitions::hasInputValueDefinitions,
                        InputValueDefinitions::inputValueDefinitions);

        // Specific stream properties
        private final Consumer<Node> ifUnionMembers = node -> //
                printStreamProperty(node, UnionTypeDefinition.class, "unionMembers", UnionTypeDefinition::hasUnionMembers,
                        UnionTypeDefinition::unionMembers);

        private final Consumer<Node> ifObjectTypeDefinitions = node -> //
                printStreamProperty(node, TypeExtensionDefinition.class, "objectTypeDefinitions",
                        TypeExtensionDefinition::hasObjectTypeDefinitions, TypeExtensionDefinition::objectTypeDefinitions);

        private final Consumer<Node> ifOperationTypeDefinitions = node -> //
                printStreamProperty(node, SchemaDefinition.class, "operationTypeDefinitions",
                        SchemaDefinition::hasOperationTypeDefinitions, SchemaDefinition::operationTypeDefinitions);

        private final Consumer<Node> ifVariableDefinitions = node -> //
                printStreamProperty(node, OperationDefinition.class, "variableDefinitions",
                        OperationDefinition::hasVariableDefinitions, OperationDefinition::variableDefinitions);

        private final Consumer<Node> ifObjectFields = node -> //
                printStreamProperty(node, ObjectValue.class, "objectFields", ObjectValue::hasObjectFields,
                        ObjectValue::objectFields);

        private final Consumer<Node> ifValues = node -> //
                printStreamProperty(node, ListValue.class, "values", ListValue::hasValues, ListValue::values);

        private final Consumer<Node> ifEnumValueDefinitions = node -> //
                printStreamProperty(node, EnumTypeDefinition.class, "enumValueDefinitions",
                        EnumTypeDefinition::hasEnumValueDefinitions, EnumTypeDefinition::enumValueDefinitions);

        private final Consumer<Node> ifDirectiveLocations = node -> //
                printStreamProperty(node, DirectiveDefinition.class, "directiveLocations",
                        DirectiveDefinition::hasDirectiveLocations, DirectiveDefinition::directiveLocations);

        private final Consumer<Node> ifDefinitions = node -> //
                printStreamProperty(node, Document.class, "definitions", Document::hasDefinitions, Document::definitions);
    }
}
