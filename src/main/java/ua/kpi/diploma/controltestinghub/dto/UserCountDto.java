package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCountDto {
    private Integer userCount;
    private Integer adminCount;
    private Integer engineerCount;
    private Integer managerCount;

}
