package dz.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ParamsPreparer {

    void prepare(PreparedStatement ps) throws SQLException;
}
