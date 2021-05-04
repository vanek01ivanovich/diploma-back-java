package ua.kpi.diploma.controltestinghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectListPaginationDto {

    private String name;
    private String link;
    private boolean isArchived;
    private int page;
    private int pageSize;
    private String sortOrder;
    private String sortField;

    public ProjectListPaginationDto(String name, String link, boolean isArchived) {
        this.name = name;
        this.link = link;
        this.isArchived = isArchived;
    }

    /**
     * This method calculates parameter OFFSET for SQL query
     * and redefine field 'page'.
     */
    public ProjectListPaginationDto setPage(int page) {
        this.page = (page > 0 ? page - 1 : 0) * pageSize;
        return this;
    }
}
