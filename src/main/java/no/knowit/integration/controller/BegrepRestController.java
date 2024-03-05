package no.knowit.integration.controller;

import no.knowit.integration.dto.BegrepDTO;
import no.knowit.integration.service.BegrepService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/begrep")
public class BegrepRestController {
    private final BegrepService begrepService;

    public BegrepRestController(BegrepService begrepService) {
        this.begrepService = begrepService;
    }

    @GetMapping
    public List<BegrepDTO> getBegrep(@RequestParam(value = "languageCode", required = false) String languageCode) {
        return begrepService.getAllBegrep(languageCode);
    }

    @GetMapping("/{id}")
    public BegrepDTO getBegrepById(@PathVariable("id") String id, @RequestParam(value = "languageCode", required = false) String languageCode) {
        return begrepService.getBegrepById(id, languageCode);
    }

}
