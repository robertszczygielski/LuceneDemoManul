package org.cattery.manul.service.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
class LuceneWriter {

    public void save(Document document) throws IOException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directoryIndex, indexWriterConfig);

        writer.addDocument(document);

        writer.close();
    }

    public void deleteCat(String name) throws IOException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directoryIndex, indexWriterConfig);

        Term term = new Term(LuceneField.CAT_NAME.getName(), name);

        writer.deleteDocuments(term);

        writer.close();
    }
}
