package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchDto {
    private String name = "%";
    private String surname = "%";
    private String email = "%";
    private List<String> roles;
    private Boolean onlyEnabled;

}
