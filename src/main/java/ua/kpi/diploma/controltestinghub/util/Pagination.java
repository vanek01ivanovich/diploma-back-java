package ua.kpi.diploma.controltestinghub.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.kpi.diploma.controltestinghub.exception.ValidationException;

import java.util.List;

@Component
@Slf4j
@PropertySource("classpath:constants.properties")
public class Pagination {
    @Value("${page.size}")
    private int defaultPageSize;
    @Value("${page.page}")
    private int defaultPage;
    @Value("${page.user.sort.field}")
    private String defaultPageUserSortField;
    @Value("${page.user.sort.order}")
    private String defaultPageUserSortOrder;
    @Value("${pagination.sql}")
    private String PAGINATION_SQL;

    public Pageable replaceNullsUserPage(Pageable pageable) {
        return Pageable.builder().pageSize(pageable.getPageSize() == null ? defaultPageSize : pageable.getPageSize())
                .page(pageable.getPage() == null ? defaultPage : pageable.getPage())
                .sortField(pageable.getSortField() == null ? defaultPageUserSortField : pageable.getSortField())
                .sortOrder(pageable.getSortOrder() == null ? defaultPageUserSortOrder : pageable.getSortOrder())
                .build();
    }

    public int countOffset(Pageable pageable) {
        return pageable.getPage() == null || pageable.getPageSize() == null ? 0 : (pageable.getPage() - 1) * pageable.getPageSize();
    }

    public int countPages(int numberOfRecords, Integer pageSize) {
        return (int) Math.ceil((double) numberOfRecords / (pageSize == null ? defaultPageSize : pageSize));
    }

    public void validate(Pageable pageable, List<String> possibleSortFields) throws ValidationException {
        if (!possibleSortFields.contains(pageable.getSortField()) ||
                !(pageable.getSortOrder().equalsIgnoreCase("ASC") || pageable.getSortOrder().equalsIgnoreCase("DESC"))) {

            throw new ValidationException("Not valid sortField = " + pageable.getSortField() + " or sortOrder = " + pageable.getSortOrder());
        }
    }

    public String formSqlPostgresPaginationPiece(Pageable pageable) {
        return  String.format(PAGINATION_SQL, pageable.getSortField(), pageable.getSortOrder(), pageable.getPageSize(), countOffset(pageable));
    }

    public Pageable setDefaultOrderValue(Pageable pageable){
        return Pageable.builder()
                .page(pageable.getPage())
                .pageSize(pageable.getPageSize())
                .sortField(pageable.getSortField().equals("") ? "id" : pageable.getSortField())
                .sortOrder(pageable.getSortOrder())
                .build();
    }
}
