package ua.kpi.diploma.controltestinghub.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class User {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String role;
    private boolean isEnabled;
}
