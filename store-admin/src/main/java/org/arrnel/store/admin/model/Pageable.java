package org.arrnel.store.admin.model;

import java.util.Optional;

public record Pageable(

        Integer page,

        Sort sort

) {

    public Pageable {
        if (sort == null)
            sort = new Sort(null, null);
    }

    public Optional<Integer> getOptionalPage() {
        return Optional.ofNullable(page);
    }

}
