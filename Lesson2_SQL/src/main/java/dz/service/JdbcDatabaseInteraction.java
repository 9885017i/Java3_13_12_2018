package dz.service;

import dz.dao.ProductDAO;
import dz.dao.jdbc.SimpleJdbcDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDatabaseInteraction implements DatabaseInteraction {

    public static final int PRODUCT_COUNT = 10_000;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find Jdbc driver", e);
        }
    }

    private String url;
    private Connection connection;
    private ProductDAO dao;

    public JdbcDatabaseInteraction(String url) {
        this.url = url;
    }

    @Override
    public void initialize() {
        try {
            this.connection = DriverManager.getConnection(url);
            dao = new SimpleJdbcDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create connection to DB", e);
        }
        prepareDatabase();
    }

    private void prepareDatabase() {
        try {
            Statement statement = connection.createStatement();

            createTableIfRequired(statement);
            clearTable(statement);
            insertProducts(statement);

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to prepare database", e);
        }


    }

    private void insertProducts(Statement statement) throws SQLException {
        connection.setAutoCommit(false);
        try {
            for (int i = 1; i <= PRODUCT_COUNT; i++) {
                statement.addBatch(String.format("INSERT INTO Product (product_id, title, cost) VALUES (%1$d, 'product%1$d', %1$d0)", i));
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void clearTable(Statement statement) throws SQLException {
        statement.execute("DELETE FROM Product");
    }

    private void createTableIfRequired(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Product\n" +
                "(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "product_id INTEGER NOT NULL UNIQUE,\n" +
                "title TEXT NOT NULL,\n" +
                "cost INTEGER NOT NULL\n" +
                ");");
    }

    @Override
    public ProductDAO getDAO() {
        checkInitialize();
        return dao;
    }

    @Override
    public void close() throws Exception {
        checkInitialize();
        connection.close();
    }

    private void checkInitialize() {
        if (connection == null) {
            throw new IllegalStateException("initialize must be invoked");
        }
    }
}
