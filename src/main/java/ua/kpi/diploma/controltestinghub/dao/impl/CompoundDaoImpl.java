package ua.kpi.diploma.controltestinghub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.kpi.diploma.controltestinghub.dao.CompoundDao;
import ua.kpi.diploma.controltestinghub.dto.ActionDtoWithIdNameVoid;
import ua.kpi.diploma.controltestinghub.dto.CompoundDto;
import ua.kpi.diploma.controltestinghub.dto.CompoundDtoWithIdName;
import ua.kpi.diploma.controltestinghub.mapper.*;
import ua.kpi.diploma.controltestinghub.model.Compound;
import ua.kpi.diploma.controltestinghub.model.CompoundActionWithActionIdAndPriority;
import ua.kpi.diploma.controltestinghub.util.Pageable;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CompoundDaoImpl implements CompoundDao {

    private final JdbcTemplate jdbcTemplate;
    private final CompoundMapper mapper;
    private final CompoundActionListMapper actionListMapper;

    @Value("${find.compound.by.id}")
    private String FIND_COMPOUND_BY_ID;
    @Value("${find.comp.action.by.id}")
    private String FIND_COMP_ACTION_BY_ID;
    @Value("${update.compound}")
    private String UPDATE_COMPOUND;

    @Value("${find.compound.quantity}")
    private String GET_QUANTITY_COMPOUNDS;

    @Value("${find.compound.all}")
    private String FIND_ALL_WITH_PAGINATION;

    @Value("${find.compound.all.with.id.name}")
    private String FIND_ALL_WITH_ID_NAME;

    @Value("${get.compound.action.with.action.id.and.priority}")
    private String FIND_COMPOUND_ACTION_WITH_ACTION_ID_AND_PRIORITY_BY_COMPOUND_ID;

    @Value("${get.compound.actions.with.id.name.void.by.compound.id}")
    private String FIND_ALL_COMPOUND_ACTION_WITH_ID_NAME_VOID_BY_COMPOUND_ID;

    @Value("${update.compound.archive}")
    private String UPDATE_COMPOUND_ARCHIVE;

    @Value("${check.if.compound.name.exist}")
    private String CHECK_IF_COMPOUND_NAME_EXIST;

    @Value("${insert.compound}")
    private String CREATE_COMPOUND;

    @Value("${insert.compound.actions}")
    private String CREATE_COMPOUND_ACTIONS;

    @Autowired
    public CompoundDaoImpl(JdbcTemplate jdbcTemplate, CompoundMapper mapper, CompoundActionListMapper actionListMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.actionListMapper = actionListMapper;
    }

    @Override
    public Integer getQuantityCompounds(String search) {
        return Objects.requireNonNull(jdbcTemplate.queryForObject(GET_QUANTITY_COMPOUNDS, Integer.class, search + "%"));
    }

    @Override
    public List<Compound> findAll(Pageable pageable) {
        String sql = String.format(
                FIND_ALL_WITH_PAGINATION,
                pageable.getSearch(),
                pageable.getSortField(),
                pageable.getPageSize(),
                pageable.getPage()
        );
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<CompoundDtoWithIdName> findAllWithIdName() {
        RowMapper<CompoundDtoWithIdName> mapper = new CompoundWithIdNameMapper();
        return jdbcTemplate.query(FIND_ALL_WITH_ID_NAME, mapper);
    }

    @Override
    public List<CompoundActionWithActionIdAndPriority> findAllCompoundActionsWithActionIdAndPriorityByCompoundId(Integer compoundId) {
        RowMapper<CompoundActionWithActionIdAndPriority> mapper =
                new CompoundActionWithActionIdAndPriorityMapper();
        return jdbcTemplate.query(
                FIND_COMPOUND_ACTION_WITH_ACTION_ID_AND_PRIORITY_BY_COMPOUND_ID,
                mapper,
                compoundId
        );
    }

    @Override
    public void archiveCompoundById(Integer id) {
        jdbcTemplate.update(UPDATE_COMPOUND_ARCHIVE, id);
    }

    @Override
    public List<ActionDtoWithIdNameVoid> findAllCompoundActionsByCompoundId(Integer compoundId) {
        RowMapper<ActionDtoWithIdNameVoid> mapper = new ActionWithIdNameVoidMapper();
        return jdbcTemplate.query(
                FIND_ALL_COMPOUND_ACTION_WITH_ID_NAME_VOID_BY_COMPOUND_ID,
                mapper,
                compoundId
        );
    }


    @Override
    public boolean checkIfNameExist(String name) {
        return jdbcTemplate.queryForObject(CHECK_IF_COMPOUND_NAME_EXIST,Boolean.class,name);
    }

    @Override
    public Integer createCompound(Compound compound) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(CREATE_COMPOUND, new String[] {"id"});
            ps.setString(1,compound.getName());
            ps.setString(2,compound.getDescription());
            return ps;
        },keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * Void method for creating compound in DB
     * @param compoundId needed for inserting compound to DB
     * @param compoundDto needed for inserting compound to DB
     */
    @Override
    public void createCompoundActions(Integer compoundId,CompoundDto compoundDto) {
        jdbcTemplate.batchUpdate(CREATE_COMPOUND_ACTIONS,compoundDto.getActionList(),compoundDto.getActionList().size(),((ps, compoundActionsValue) -> {
            ps.setLong(1,compoundId);
            ps.setLong(2,compoundActionsValue.getActionId());
            ps.setInt(3,compoundActionsValue.getPriority());
        }));
    }
    @Override
    public Optional<Compound> findCompoundById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_COMPOUND_BY_ID, mapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public void updateCompound(Compound compound) {
        jdbcTemplate.update(UPDATE_COMPOUND, compound.getName(), compound.getDescription(), compound.getId());
    }

    @Override
    public Optional<CompoundDto> findCompActionListById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_COMP_ACTION_BY_ID, actionListMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
