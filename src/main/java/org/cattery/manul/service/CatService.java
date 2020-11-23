package org.cattery.manul.service;


import lombok.AllArgsConstructor;
import org.cattery.manul.dto.CatDto;
import org.cattery.manul.enums.CatColor;
import org.cattery.manul.service.lucene.LuceneFacade;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CatService {

    private final LuceneFacade luceneFacade;

    public List<CatDto> getAllCats() {
        // simulate repository.getAll();
        return List.of(
                cat("Ala ma kota", CatColor.BLACK, Instant.parse("2007-12-03T10:15:30.00Z")),
                cat("Kot ma ale", CatColor.RED, Instant.parse("2017-01-03T20:15:30.00Z")),
                cat("tiger", CatColor.WHITE, Instant.parse("1999-09-12T12:00:30.00Z")),
                cat("red", CatColor.RED, Instant.parse("2020-09-12T12:00:30.00Z")),
                cat("gerald1", CatColor.BLACK, Instant.parse("2000-09-12T12:00:30.00Z")),
                cat("gerald29", CatColor.WHITE, Instant.parse("2010-09-12T12:00:30.00Z")),
                cat("gerald4", CatColor.WHITE, Instant.parse("2001-09-12T12:00:30.00Z")),
                cat("yen", CatColor.RED, Instant.parse("2001-09-12T12:00:30.00Z")),
                cat("yen3", CatColor.BLACK, Instant.parse("2010-09-12T12:00:30.00Z")),
                cat("zen", CatColor.WHITE, Instant.parse("2011-09-12T12:00:30.00Z"))
        );
    }

    public void saveCats() {
        getAllCats().forEach(luceneFacade::saveCat);
    }

    public void removeCat(String name) {
        luceneFacade.removeCat(name);
    }

    public CatDto saveOneCat() {
        // simulate repository.getById();
        Random rand = new Random();
        int randomNumber = rand.nextInt(500);
        String catName = "cat" + randomNumber;

        System.out.println(catName);
        CatDto catToSave = cat(catName, CatColor.randomColor(), Instant.now());
        luceneFacade.saveCat(catToSave);

        return catToSave;
    }

    public List<String> searchCatsByName(String name) {
        return luceneFacade.searchCatsByName(name.toLowerCase());
    }

    public List<String> readAllCats() {
        return luceneFacade.readAllCats();
    }

    public List<String> searchCatsBySubStringInName(String subStringInName) {
        return luceneFacade.searchCatsBySubStringInName(subStringInName.toLowerCase());
    }

    public List<String> searchCatsBySubStringInNameEndDateRange(String subStringInName, String from, String to) {
        Instant dataFrom = Instant.parse(from + "T00:00:01.00Z");
        Instant dataTo = Instant.parse(to + "T23:59:30.00Z");

        return luceneFacade.searchCatsBySubStringInNameEndDateRange(subStringInName.toLowerCase(), dataFrom, dataTo);
    }

    public List<String> searchSortedCatBySubStringInNameAndDateRange(String subStringInName, String from, String to) {
        Instant dataFrom = Instant.parse(from + "T00:00:01.00Z");
        Instant dataTo = Instant.parse(to + "T23:59:30.00Z");

        return luceneFacade.searchSortedCatBySubStingInNameAndDateRange(subStringInName.toLowerCase(), dataFrom, dataTo);
    }

    private CatDto cat(String name, CatColor color, Instant sold) {
        return CatDto.builder()
                .color(color)
                .father(UUID.randomUUID())
                .mother(UUID.randomUUID())
                .name(name)
                .id(UUID.nameUUIDFromBytes(name.getBytes()))
                .sold(sold)
                .build();
    }

}
