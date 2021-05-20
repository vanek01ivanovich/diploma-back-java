package ua.kpi.diploma.controltestinghub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;
    private boolean isEnabled;
}
