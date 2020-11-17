package org.cattery.manul.service.lucene;

import lombok.AllArgsConstructor;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.cattery.manul.dto.CatDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class LuceneFacade {

    private final LuceneDocument luceneDocument;
    private final LuceneWriter luceneWriter;
    private final LuceneReader luceneReader;
    private final LuceneSearch luceneSearch;

    public void saveCat(CatDto dto) {
        Document document = luceneDocument.createDocument(dto);
        try {
            luceneWriter.save(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCat(String name) {
        try {
            luceneWriter.deleteCat(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchCatsByName(String name) {
        try {
            return luceneReader.searchCatsByName(name);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> readAllCats() {
        try {
            return luceneReader.readAllCats();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> searchCatsBySubStringInName(String subStringInName) {
        try {
            return luceneSearch.searchCatsBySubStringInName(subStringInName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> searchCatsBySubStringInNameEndDateRange(String subStringInName, Instant from, Instant to) {
        try {
            return luceneSearch.searchCatBySubStringInNameAndDateRange(subStringInName, from, to);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> searchSortedCatBySubStingInNameAndDateRange(String subStringInName, Instant from, Instant to) {
        try {
            return luceneSearch.searchSortedCatBySubStringInNameAndDateRange(subStringInName, from, to);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
