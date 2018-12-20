package dz.service;

import dz.dao.ProductDAO;

public interface DatabaseInteraction extends AutoCloseable {

    void initialize();

    ProductDAO getDAO();
}
