package com.AdminPanel.Angular5SpringBoot.fileWorkspace.modelConvertors;

import com.AdminPanel.Angular5SpringBoot.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRows {

    private final static String PRODUCT_HEADER = "id,typeofclothes,material,size,color,dateoflastchange,price,image";
    private final static String CSV_SPLIT_BY = ",";

    private List<String> csvRows;

    public ProductRows(List<String> csvRows) {
        this.csvRows = csvRows;
    }

    public List<Product> convertRowsToProducts() {

        String headersTable = csvRows.get(0);
        if (!PRODUCT_HEADER.equals(headersTable.toLowerCase())) {
//            log.warn("Header is not valid.");
            return null;
        }
        csvRows.remove(0);
        if (csvRows == null) {
//            log.warn("Table is not have a data.");
            return null;
        }
        List<Product> products = new ArrayList<>();
        csvRows.forEach(row -> {
            String[] column = row.split(CSV_SPLIT_BY);
            products.add(new Product(
                    Long.getLong(column[0]),
                    column[1],
                    column[2],
                    column[3],
                    column[4],
                    Long.parseUnsignedLong(column[5]),
                    column[7])
            );
        });
        return products;
    }
}
