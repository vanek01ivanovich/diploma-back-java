package ua.kpi.diploma.controltestinghub.dto;

import lombok.Data;

@Data
public class ResetPassDto {
    String token;
    String password;
}
