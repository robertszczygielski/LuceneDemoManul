package org.cattery.manul.service.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
class LuceneReader {

    public List<String> searchCatsByName(String name) throws IOException, ParseException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));
        Analyzer analyzer = new StandardAnalyzer();

        Query q = new QueryParser(LuceneField.CAT_NAME.getName(), analyzer).parse(name);

        int maxAmountInBath = 10;
        IndexReader reader = DirectoryReader.open(directoryIndex);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, maxAmountInBath);
        ScoreDoc[] hits = docs.scoreDocs;

        List<String> cats = new ArrayList<>();

        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document d = searcher.doc(docId);
            cats.add(d.get(LuceneField.CAT_NAME.getName()) + " / " + d.get(LuceneField.SOLD_DATE.getName()));
        }

        reader.close();

        return cats;
    }

    public List<String> readAllCats() throws IOException, ParseException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Query q = new MatchAllDocsQuery();

        int maxAmountInBath = Integer.MAX_VALUE;
        IndexReader reader = DirectoryReader.open(directoryIndex);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, maxAmountInBath);
        ScoreDoc[] hits = docs.scoreDocs;

        List<String> cats = new ArrayList<>();

        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document d = searcher.doc(docId);
            cats.add(d.get(LuceneField.CAT_NAME.getName()) + " / " + d.get(LuceneField.SOLD_DATE.getName()));
        }

        reader.close();

        return cats;

    }

}
