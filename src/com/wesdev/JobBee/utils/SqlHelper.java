package com.wesdev.JobBee.utils;

public class SqlHelper {

    private static SqlHelper instance = new SqlHelper();


    public static final int ORDERED_BY_NONE = 1;
    public static final int ORDERED_BY_ASC = 2;
    public static final int ORDERED_BY_DESC = 3;

    public void sortBy(StringBuilder sb, int sortOrder, String orderedByCategory) {
        if (sb != null) {
            if (sortOrder != ORDERED_BY_NONE) {
                sb.append(" ORDER BY ");
                sb.append(orderedByCategory);
                sb.append(" COLLATE NOCASE ");
                if (sortOrder == ORDERED_BY_DESC) {
                    sb.append("DESC");
                } else {
                    sb.append("ASC");
                }
            }
        }
    }

    public static SqlHelper getInstance() {
        return instance;
    }
}
