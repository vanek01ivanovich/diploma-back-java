package ua.kpi.diploma.controltestinghub.dto;

import lombok.Builder;
import lombok.Data;
import ua.kpi.diploma.controltestinghub.model.TestCaseUpd;

@Data
@Builder
public class TestCaseWithUserDto {
    private TestCaseUpd testCaseUpd;
    private String email;
}
