package org.arrnel.store.admin.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public record Sort(

        String[] fields,

        Order order

) {

    public Sort {
        if (fields == null)
            fields = new String[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sort sort = (Sort) o;
        return Objects.deepEquals(fields, sort.fields) && order == sort.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(fields), order);
    }

    @Override
    public String toString() {
        return "Order{" +
                "fields=" + Arrays.toString(fields) +
                ", orderType=" + order +
                '}';
    }

    public Optional<String> getSortText(){

        if(fields.length > 0 || order != null){
            String fieldsText = (fields.length > 0)
                    ? String.join(",", fields)
                    : "";
            String separator = (fields.length > 0 && order != null)
                    ? ","
                    : "";
            String orderText = (order != null)
                    ? order.name()
                    : "";
            return Optional.of(fieldsText + separator + orderText);
        }
        return Optional.empty();

    }

    public enum Order {
        ASC, DESC
    }
}
