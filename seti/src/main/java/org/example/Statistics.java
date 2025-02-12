package org.example;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Object> values = new ArrayList<>();
    private double sum = 0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public void addValue(Object value) {
        values.add(value);
        if (value instanceof Number) {
            double num = ((Number) value).doubleValue();
            sum += num;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
    }

    public List<?> getValues() {
        return values;
    }

    public String getSummary(boolean full) {
        if (values.isEmpty()) return "0 elements";

        if (full) {
            if (values.get(0) instanceof Number) {
                return String.format("%d elements, min=%.2f, max=%.2f, sum=%.2f, avg=%.2f",
                        values.size(), min, max, sum, sum / values.size());
            } else {
                int minLength = Integer.MAX_VALUE;
                int maxLength = 0;
                for (Object value : values) {
                    String str = value.toString();
                    minLength = Math.min(minLength, str.length());
                    maxLength = Math.max(maxLength, str.length());
                }
                return String.format("%d elements, shortest=%d, longest=%d",
                        values.size(), minLength, maxLength);
            }
        } else {
            return values.size() + " elements";
        }
    }
}