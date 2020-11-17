package org.cattery.manul.service.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.BytesRef;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
class LuceneSearch {

    public List<String> searchCatsBySubStringInName(String subStringInName) throws IOException, ParseException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));

        Query q = new WildcardQuery(
                new Term(LuceneField.CAT_NAME.getName(), "*" + subStringInName + "*"));

        int maxAmountInBath = 2;
        IndexReader reader = DirectoryReader.open(directoryIndex);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, maxAmountInBath);
        ScoreDoc[] hits = docs.scoreDocs;

        List<String> cats = new ArrayList<>();

        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document d = searcher.doc(docId);
            cats.add(
                    d.get(LuceneField.CAT_NAME.getName()) + " / " + d.get(LuceneField.SOLD_DATE.getName()));
        }

        reader.close();

        return cats;

    }

    public List<String> searchCatBySubStringInNameAndDateRange(
            String subStringInName,
            Instant from,
            Instant to) throws IOException, ParseException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));

        Query qName = new WildcardQuery(
                new Term(LuceneField.CAT_NAME.getName(), "*" + subStringInName + "*"));
        Query qRangeDate = new TermRangeQuery(
                LuceneField.SOLD_DATE.getName(),
                new BytesRef(from.toString()),
                new BytesRef(to.toString()),
                true,
                true
        );

        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        booleanQuery.add(qName, BooleanClause.Occur.MUST);
        booleanQuery.add(qRangeDate, BooleanClause.Occur.MUST);

        int maxAmountInBath = Integer.MAX_VALUE;
        IndexReader reader = DirectoryReader.open(directoryIndex);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(booleanQuery.build(), maxAmountInBath);
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

    public List<String> searchSortedCatBySubStringInNameAndDateRange(
            String subStringInName,
            Instant from,
            Instant to) throws IOException, ParseException {
        Directory directoryIndex = new MMapDirectory(Paths.get("./LuceneDemo"));

        Query qName = new WildcardQuery(new Term(LuceneField.CAT_NAME.getName(), "*" + subStringInName + "*"));
        Query qRangeDate = new TermRangeQuery(
                LuceneField.SOLD_DATE.getName(),
                new BytesRef(from.toString()),
                new BytesRef(to.toString()),
                true,
                true
        );
        Sort sortByData = new Sort(
                new SortedSetSortField(LuceneField.SOLD_DATE.getName(), false));

        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        booleanQuery.add(qName, BooleanClause.Occur.MUST);
        booleanQuery.add(qRangeDate, BooleanClause.Occur.MUST);

        int maxAmountInBath = Integer.MAX_VALUE;
        IndexReader reader = DirectoryReader.open(directoryIndex);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(booleanQuery.build(), maxAmountInBath, sortByData);
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
