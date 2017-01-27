/*
 * Copyright (c) 2016, Christoph Engelbert (aka noctarius) and
 * contributors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.noctarius.graphquul.impl;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args)
            throws Exception {

        URL url = Test.class.getResource("/test-fragment.graphql");
        Path file = Paths.get(url.toURI());
        Stream<String> lines = Files.lines(file, Charset.forName("UTF-8"));
        String query = lines.collect(supplier(), StringJoiner::add, StringJoiner::merge).toString();

        ASTBuilder builder = new ASTBuilder();
        GraphQLParser.parse(query, builder);

        System.out.println(builder.getDocument());
    }

    private static Supplier<StringJoiner> supplier() {
        return () -> new StringJoiner("\n");
    }
}
