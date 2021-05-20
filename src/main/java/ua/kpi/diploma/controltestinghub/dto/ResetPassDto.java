package ua.kpi.diploma.controltestinghub.dto;

import lombok.Data;

@Data
public class ResetPassDto {
    private String token;
    private String password;
}
