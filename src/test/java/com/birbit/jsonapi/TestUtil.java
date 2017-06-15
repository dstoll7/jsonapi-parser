/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.birbit.jsonapi;

import com.android.annotations.NonNull;
import com.birbit.jsonapi.vo.Article;
import com.birbit.jsonapi.vo.ArticleWithFullRelationships;
import com.birbit.jsonapi.vo.Author;
import com.birbit.jsonapi.vo.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class TestUtil {

    private TestUtil() {

    }

    static JsonApiResourceDeserializer<ArticleWithFullRelationships> ARTICLE_WITH_RELATIONSHIP_OBJECTS_DESERIALIZER
            = new JsonApiResourceDeserializer<ArticleWithFullRelationships>("articles", ArticleWithFullRelationships.class);
    static JsonApiResourceDeserializer<Author> AUTHOR_DESERIALIZER = new JsonApiResourceDeserializer<Author>("authors", Author.class);
    static JsonApiResourceDeserializer<Article> ARTICLE_DESERIALIZER = new JsonApiResourceDeserializer<Article>("articles", Article.class);
    static JsonApiResourceDeserializer<Comment> COMMENT_DESERIALIZER = new JsonApiResourceDeserializer<Comment>("comment", Comment.class);

    static Gson createGson(JsonApiResourceDeserializer... deserializers) {
        return JsonApiDeserializer.register(new GsonBuilder(), deserializers).create();
    }


    @NonNull
    public static String inputStreamToString(@NonNull InputStream stream) throws IOException {
        if(stream == null) {
            System.out.println("Stream is null");
        }
        return IOUtils.toString(stream, "UTF-8");
    }

    public static String resourceToString(@NonNull String resource) throws IOException {
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            return inputStreamToString(stream);
        }
    }
}
