package ua.kpi.diploma.controltestinghub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.service.CompoundService;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/compounds")
@Slf4j
public class CompoundActionController {


    private final CompoundService compoundService;

    @Autowired
    public CompoundActionController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping
    public ResponseEntity<?> getCompounds(@RequestParam Integer pageSize,
                                          @RequestParam Integer page,
                                          @RequestParam String search,
                                          @RequestParam String sortField) {
        Pageable pageable = new Pageable();
        pageable.setPageSize(pageSize);
        pageable.setSortField(sortField);
        pageable.setSearch(search);
        pageable.setPage((page > 0 ? page - 1 : 0) * pageSize);
        return ResponseEntity.ok(compoundService.getAllCompounds(pageable));
    }

    @GetMapping("/quantity")
    public ResponseEntity<?> getQuantityCompounds(@RequestParam String search) {
        return ResponseEntity.ok(compoundService.getQuantityCompounds(search));
    }

    @GetMapping("/{id}/actions")
    public ResponseEntity<?> getAllActionsOfCompoundByCompoundId(@PathVariable("id") Integer compoundId) {
        List<ActionDtoWithIdNameVoid> actions =
                compoundService.getAllCompoundActionsByCompoundId(compoundId);
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompoundWithById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(compoundService.getCompoundById(id));
    }

    @PutMapping("/{id}")
    public void archiveCompoundById(@PathVariable Integer id) {
        compoundService.archiveCompoundById(id);
    }

    /**
     * Returns boolean if compound name is existed
     * @param name for checking name in DB
     * @return boolean if compound name is existed
     */
    @GetMapping(value = "/create/check/{name}")
    public boolean checkIfNameExist(@PathVariable String name){
        log.info("Check if compound name exists before creating compound with name : {}",name);
        return compoundService.checkIfNameExist(name);
    }

    /**
     * Void method that create compound with compoundDto
     * @param compoundDto needed for creating compound in DB
     */
    @PostMapping(value = "/create")
    public void createCompound(@RequestBody CompoundDto compoundDto){
        log.info("Compound for creating : {}",compoundDto);
        compoundService.createCompound(compoundDto);
    }

    @GetMapping("/edit/{id}")
    public CompoundDto getCompoundById(@PathVariable Integer id) throws Exception{
        return compoundService.getCompoundById(id);
    }
    @PutMapping("/edit/{id}")
    public void updateCompound(@PathVariable Integer id, @RequestBody Compound compound){
        compoundService.updateCompound(compound, id);
    }
}
