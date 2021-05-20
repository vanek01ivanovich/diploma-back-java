package ua.kpi.diploma.controltestinghub.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.dto.SubscribedUserTestCaseDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubscribedUserMapper implements RowMapper<SubscribedUserTestCaseDto> {

    /**
     * @param resultSet contains result from DB
     * @param i         integer
     * @return SubscribedUserTestCaseDto
     * @throws SQLException throw SQLException
     */
    @Override
    public SubscribedUserTestCaseDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return SubscribedUserTestCaseDto.builder()
                .id(resultSet.getInt(1))
                .userName(resultSet.getString(2))
                .email(resultSet.getString(3))
                .testCaseId(resultSet.getInt(4))
                .testCaseName(resultSet.getString(5))
                .build();
    }
}
