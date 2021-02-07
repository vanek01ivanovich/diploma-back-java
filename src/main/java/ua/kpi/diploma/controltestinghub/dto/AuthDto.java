package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
public class AuthDto {
    private String email;
    private String password;
}
