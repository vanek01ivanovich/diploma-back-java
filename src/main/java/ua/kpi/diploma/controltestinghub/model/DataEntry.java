package ua.kpi.diploma.controltestinghub.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DataEntry {
    private Long id;
    private Long data_set_id;
    private String value;
    private String key;
}
