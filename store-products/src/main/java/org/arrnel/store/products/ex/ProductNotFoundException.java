package org.arrnel.store.products.ex;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Product with id = [%d] not found";
    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super(DEFAULT_MESSAGE.formatted(productId));
        this.productId = productId;
    }

    public ProductNotFoundException(Long productId, Throwable throwable) {
        super(DEFAULT_MESSAGE.formatted(productId), throwable);
        this.productId = productId;
    }

}
