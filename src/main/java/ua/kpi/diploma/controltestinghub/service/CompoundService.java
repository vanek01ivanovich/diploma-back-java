package ua.kpi.diploma.controltestinghub.service;


import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.model.Compound;

import java.util.List;

public interface CompoundService {

    long getQuantityCompounds(String search);

    List<Compound> getAllCompounds();

    /*List<CompoundDtoWithIdName> getAllCompoundsWithIdName();

    List<ActionDtoWithIdNameVoid> getAllCompoundActionsByCompoundId(long compoundId);
*/
    void archiveCompoundById(long id);

    boolean checkIfNameExist(String name);

   /* void createCompound(CompoundDto compoundDto);
*/
    //void createCompoundActions(List<CompoundAction> compoundActions);

    CompoundDto getCompoundById(long id) throws Exception;

    void updateCompound(Compound compound, long id);


}
