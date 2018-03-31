package com.AdminPanel.Angular5SpringBoot.fileWorkspace.modelConvertors;

import com.AdminPanel.Angular5SpringBoot.fileWorkspace.CsvManager;
import com.AdminPanel.Angular5SpringBoot.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRows extends CsvManager {

    private final static String CUSTOMER_HEADER = "id,firstname,lastname,username,password,email,gender,phone,access,image";
    private final static String CSV_SPLIT_BY = ",";

    private List<String> csvRows;

    public CustomerRows(List<String> csvRows) {
        this.csvRows = csvRows;
    }

    public List<Customer> convertRowsToCustomers() {

        String headersTable = csvRows.get(0);
        if (!CUSTOMER_HEADER.equals(headersTable.toLowerCase())) {
//            log.warn("Header is not valid.");
            return null;
        }
        csvRows.remove(0);
        if (csvRows == null) {
//            log.warn("Table is not have a data.");
            return null;
        }
        List<Customer> customers = new ArrayList<>();
        csvRows.forEach(row -> {
            String[] column = row.split(CSV_SPLIT_BY);
            customers.add(new Customer(
                    column[1],
                    column[2],
                    column[3],
                    column[4],
                    column[5],
                    Customer.Sex.valueOf(column[6]),
                    Long.parseUnsignedLong(column[7]),
                    Boolean.parseBoolean(column[8]),
                    column[9])
            );
        });
        return customers;
    }
}
