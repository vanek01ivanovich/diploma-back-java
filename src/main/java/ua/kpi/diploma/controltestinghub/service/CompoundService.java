package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;

public interface CompoundService {

    Integer getQuantityCompounds(String search);

    List<Compound> getAllCompounds(Pageable pageable);

    List<CompoundDtoWithIdName> getAllCompoundsWithIdName();

    List<ActionDtoWithIdNameVoid> getAllCompoundActionsByCompoundId(Integer compoundId);
    void archiveCompoundById(Integer id);

    boolean checkIfNameExist(String name);

    void createCompound(CompoundDto compoundDto);

    //void createCompoundActions(List<CompoundAction> compoundActions);

    CompoundDto getCompoundById(Integer id) throws Exception;

    void updateCompound(Compound compound, Integer id);


}
