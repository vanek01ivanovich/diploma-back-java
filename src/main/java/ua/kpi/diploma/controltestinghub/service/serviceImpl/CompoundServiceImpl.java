package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.CompoundDao;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.service.CompoundService;
import ua.kpi.diploma.controltestinghub.util.Pageable;


import java.util.List;

@Service
@Slf4j
public class CompoundServiceImpl implements CompoundService {

    private final CompoundDao compoundDAO;

    public CompoundServiceImpl(CompoundDao compoundDAO) {
        this.compoundDAO = compoundDAO;
    }

    /**
     * @param pageable object that includes fields for pagination
     * @return list of Compound objects by object of Pageable(it's pagination).
     */
    @Override
    public List<Compound> getAllCompounds(Pageable pageable) {
        List<Compound> compounds = compoundDAO.findAll(pageable);
        if(compounds.isEmpty()) {
            log.warn("IN getAllCompounds - no compounds found with pagination: {}", pageable);
            return compounds;
        }
        log.info("IN getAllCompounds - compounds: {} found with pagination: {}", compounds, pageable);
        return compounds;
    }

    /**
     * @param search string field that means part of compound name
     * @return long type number that means compound quantity
     * by search(in this case it's part of compound name) from database
     */
    @Override
    public Integer getQuantityCompounds(String search) {
        Integer quantity = compoundDAO.getQuantityCompounds(search);
        log.info("IN getQuantityCompounds - {} compounds found with name like: '{}'", quantity, search);
        return quantity;
    }

    /**
     * @return list of all Compound objects from database.
     * These objects have fields id and name.
     */
    @Override
    public List<CompoundDtoWithIdName> getAllCompoundsWithIdName() {
        List<CompoundDtoWithIdName> compounds = compoundDAO.findAllWithIdName();
        if(compounds.isEmpty()) {
            log.warn("IN getAllCompoundsWithIdName - no compounds found");
            return compounds;
        }
        log.info("IN getAllCompoundsWithIdName - {} compounds found", compounds.size());
        return compounds;
    }

    /**
     * @param id id of Compound object in database.
     * @return list of all Action objects from database.
     * These objects have fields id, name and void.
     */
    @Override
    public List<ActionDtoWithIdNameVoid> getAllCompoundActionsByCompoundId(Integer id) {
        List<ActionDtoWithIdNameVoid> actions = compoundDAO.findAllCompoundActionsByCompoundId(id);
        if(actions.isEmpty()) {
            log.warn("IN getAllCompoundActionsByCompoundId - no actions found with compound id: {}", id);
            return actions;
        }
        log.info("IN getAllCompoundActionsByCompoundId - actions: {} found with compound id: {}", actions, id);
        return actions;
    }

    /**
     * This method archives Compound object by id in database.
     * @param id id of Compound object in database.
     */
    @Override
    public void archiveCompoundById(Integer id) {
        compoundDAO.archiveCompoundById(id);
        log.info("IN archiveCompoundById - compound with id: {} is archived", id);
    }

    @Override
    public boolean checkIfNameExist(String name) {
        return compoundDAO.checkIfNameExist(name);
    }

    /**
     * Void method that creates compound in DB
     * @param compoundDto contains values and info for creating compound in DB
     */
    @Override
    public void createCompound(CompoundDto compoundDto) {
        Compound compound = Compound.builder().name(compoundDto.getName()).description(compoundDto.getDescription()).build();
        log.info("Will be created compound: {}",compoundDto);
        Integer compoundId = compoundDAO.createCompound(compound);
        compoundDAO.createCompoundActions(compoundId,compoundDto);
    }

    @Override
    public CompoundDto getCompoundById(Integer id) throws Exception {
        return buildCompoundByID(id);
    }

    @Override
    public void updateCompound(Compound compound, Integer id) {
        compound.setId(id);
        compoundDAO.updateCompound(compound);
    }


    private CompoundDto buildCompoundByID(Integer id) throws Exception {
        Compound compound = compoundDAO.findCompoundById(id).orElseThrow(Exception::new);
        return CompoundDto.builder()
                .id(compound.getId())
                .name(compound.getName())
                .description(compound.getDescription())
                .build();

    }

}
