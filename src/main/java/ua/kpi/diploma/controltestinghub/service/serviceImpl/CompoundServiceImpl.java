package ua.kpi.diploma.controltestinghub.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.service.CompoundService;


import java.util.List;

@Service
@Slf4j
public class CompoundServiceImpl implements CompoundService {


    @Override
    public Integer getNumberOfCompounds(String search) {
        return null;
    }

    @Override
    public List<Compound> getAllCompounds() {
        return null;
    }

    @Override
    public List<CompoundDtoWithIdName> getAllCompoundsWithIdName() {
        return null;
    }

    @Override
    public List<ActionDtoWithIdNameVoid> getAllCompoundActionsByCompoundId(Integer compoundId) {
        return null;
    }

    @Override
    public void archiveCompoundById(Integer id) {

    }

    @Override
    public boolean checkIfNameExist(String name) {
        return false;
    }

    @Override
    public void createCompound(CompoundDto compoundDto) {

    }

    @Override
    public CompoundDto getCompoundById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void updateCompound(Compound compound, Integer id) {

    }
}
