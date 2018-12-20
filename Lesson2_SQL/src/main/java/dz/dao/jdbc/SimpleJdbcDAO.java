package dz.dao.jdbc;

import dz.dao.ProductDAO;
import dz.dao.UnknownProductException;
import dz.model.Id;
import dz.model.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcDAO implements ProductDAO {

    private Connection connection;

    public SimpleJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product getById(Long id) throws UnknownProductException {
        ResultSet resultSet = executeQuery("SELECT * FROM Product WHERE id = ?", ps -> {
            ps.setLong(1, id);
        });

        for (Product product : new IterableProductConverter(resultSet)) {
            return product;
        }

        throw new UnknownProductException(id);
    }

    @Override
    public List<Product> getAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM Product", null);

        List<Product> products = new ArrayList<>();
        for (Product product : new IterableProductConverter(resultSet)) {
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getByProductId(Id<Long> productId) throws UnknownProductException {
        ResultSet resultSet = executeQuery("SELECT * FROM Product WHERE product_id = ?", ps -> {
            ps.setLong(1, productId.getId());
        });

        for (Product product : new IterableProductConverter(resultSet)) {
            return product;
        }

        throw new UnknownProductException(productId);
    }

    @Override
    public BigDecimal getProductCostByTitle(String title) throws UnknownProductException {
        ResultSet resultSet = executeQuery("SELECT cost FROM Product WHERE title = ?", ps -> {
            ps.setString(1, title);
        });

        try {
            if (resultSet.next()) {
                return new BigDecimal(resultSet.getInt(1));
            }
        } catch (SQLException e) {}

        throw new UnknownProductException(title);
    }

    @Override
    public void changeProductCost(String productTitle, BigDecimal newCost) throws UnknownProductException {
        executeUpdate("UPDATE Product SET cost = ? WHERE title = ?", ps -> {
            ps.setInt(1, newCost.intValue());
            ps.setString(2, productTitle);
        });
    }

    @Override
    public List<Product> getProducts(String productAlias, String whereClause) {
        ResultSet resultSet = executeQuery(String.format("SELECT %1$s.* FROM Product as %1$s WHERE %2$s",
                productAlias,
                whereClause), null);

        List<Product> products = new ArrayList<>();
        for (Product product : new IterableProductConverter(resultSet)) {
            products.add(product);
        }

        return products;
    }

    @Override
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }
    }

    private ResultSet executeQuery(String query, ParamsPreparer paramsPreparer) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if (paramsPreparer != null) {
                paramsPreparer.prepare(ps);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }
    }

    private void executeUpdate(String query, ParamsPreparer paramsPreparer) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if (paramsPreparer != null) {
                paramsPreparer.prepare(ps);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }
    }

}
