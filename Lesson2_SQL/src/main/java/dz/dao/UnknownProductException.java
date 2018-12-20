package dz.dao;

import dz.model.Id;

public class UnknownProductException extends Exception {

    public UnknownProductException(String productTitle) {
        super(String.format("Товара '%s' не существует", productTitle));
    }

    public UnknownProductException(Long id) {
        super(String.format("Товара with id = %d не существует", id));
    }

    public UnknownProductException(Id<Long> id) {
        super(String.format("Товара with productId = %d не существует", id.getId()));
    }
}
