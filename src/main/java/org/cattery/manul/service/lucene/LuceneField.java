package org.cattery.manul.service.lucene;

import lombok.Getter;

@Getter
enum LuceneField {

    CAT_NAME("cat_name"),
    SOLD_DATE("sold_date");

    private final String name;

    LuceneField(String name) {
        this.name = name;
    }
}
