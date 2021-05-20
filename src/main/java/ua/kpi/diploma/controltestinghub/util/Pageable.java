package ua.kpi.diploma.controltestinghub.util;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pageable {
    private Integer pageSize;
    private Integer page;
    private String sortField;
    private String sortOrder;
    private String search;

    public Pageable(Integer pageSize, Integer page, String sortField, String sortOrder) {
        this.pageSize = pageSize;
        this.page = page;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
}
