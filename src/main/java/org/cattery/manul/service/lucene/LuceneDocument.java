package org.cattery.manul.service.lucene;

import org.apache.lucene.document.*;
import org.apache.lucene.util.BytesRef;
import org.cattery.manul.dto.CatDto;
import org.springframework.stereotype.Service;

@Service
class LuceneDocument {

    public Document createDocument(CatDto dto) {
        Document document = new Document();

        document.add(new TextField(LuceneField.CAT_NAME.getName(), dto.getName(), Field.Store.YES));

        document.add(new TextField(LuceneField.SOLD_DATE.getName(), dto.getSold().toString(), Field.Store.YES));
        document.add(new SortedDocValuesField(
                LuceneField.SOLD_DATE.getName(),
                new BytesRef(dto.getSold().toString())
        ));

        return document;
    }

}
