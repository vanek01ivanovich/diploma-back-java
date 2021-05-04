package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.netcracker.group3.automaticallytesting.model.TestCaseUpd;
@Data
@Builder
public class TestCaseWithUserDto {
    private TestCaseUpd testCaseUpd;
    private String email;
}
