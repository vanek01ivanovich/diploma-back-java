package ua.kpi.diploma.controltestinghub.dao;

import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.model.CompoundActionWithActionIdAndPriority;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompoundDao {

    Integer getQuantityCompounds(String search);

    List<Compound> findAll(Pageable pageable);

    List<CompoundDtoWithIdName> findAllWithIdName();

    List<CompoundActionWithActionIdAndPriority> findAllCompoundActionsWithActionIdAndPriorityByCompoundId(Integer compoundId);

    List<ActionDtoWithIdNameVoid> findAllCompoundActionsByCompoundId(Integer compoundId);

    void archiveCompoundById(Integer id);

    boolean checkIfNameExist(String name);

    Integer createCompound(Compound compound);

    void createCompoundActions(Integer compoundId, CompoundDto compoundDto);


    Optional<Compound> findCompoundById(Integer id);
    void updateCompound(Compound compound);

    Optional<CompoundDto> findCompActionListById(Integer id);

}
