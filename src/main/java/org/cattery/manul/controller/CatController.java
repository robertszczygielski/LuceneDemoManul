package org.cattery.manul.controller;

import lombok.AllArgsConstructor;
import org.cattery.manul.dto.CatDto;
import org.cattery.manul.service.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping
    public List<CatDto> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/save")
    public void saveAllCats() {
        catService.saveCats();
    }

    @GetMapping("/all")
    public List<String> readAllCats() {
        return catService.readAllCats();
    }

    @GetMapping("/remove/{name}")
    public void removeCat(@PathVariable("name") String name) {
        catService.removeCat(name);
    }

    @GetMapping("/save/id")
    public CatDto saveCatById() {
        return catService.saveOneCat();
    }

    @GetMapping("/search/{name}")
    public List<String> findCatsByName(@PathVariable String name) {
        return catService.searchCatsByName(name);
    }

    @GetMapping("/search/sub/{subStringInName}")
    public List<String> findCatsBaySubStringInName(@PathVariable String subStringInName) {
        return catService.searchCatsBySubStringInName(subStringInName);
    }

    @GetMapping("/search/sub/{subStringInName}/from/{from}/to/{to}")
    public List<String> searchCatsBySubStringInNameEndDateRange(@PathVariable String subStringInName, @PathVariable String from, @PathVariable String to) {
        return catService.searchCatsBySubStringInNameEndDateRange(subStringInName, from, to);
    }

    @GetMapping("/search/sub/{subStringInName}/from/{from}/to/{to}/sort")
    public List<String> searchSortedCatBySubStringInNameAndDateRange(@PathVariable String subStringInName, @PathVariable String from, @PathVariable String to) {
        return catService.searchSortedCatBySubStringInNameAndDateRange(subStringInName, from, to);
    }

}
