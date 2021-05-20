package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.diploma.controltestinghub.service.CompoundService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/compounds")
@Slf4j
public class CompoundActionController {

    private final CompoundService compoundService;

    public CompoundActionController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping()
    public ResponseEntity<?> getCompounds(){
        return null;
    }

    @GetMapping("/quantity")
    public ResponseEntity<?> getQuantityCompounds(){
        return null;
    }

    @GetMapping("/{id}/actions")
    public ResponseEntity<?> getAllActionsOfCompoundByCompoundId(){
        return null;
    }
}
