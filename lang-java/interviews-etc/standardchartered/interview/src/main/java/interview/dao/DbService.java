package interview.dao;

import java.sql.SQLException;

// This interface is reproduced here for clarity only - it will actually arrive
// to us in a 3rd party jar from an internal team. WE WOULD ADD THIS AS A MAVEN DEPENDENCY.
//
// I presume this service will use a generic ORM approah and not require familiarity with
// our own pojos (e.g. Car, Bike etc) -> that is to say we have control over how these
// pojos are designed and not the DbService developers
//
public interface DbService {
    public Object loadFromDb(String connectionDetails, String sql, Class type) throws SQLException;

    public Object saveToDatabase(Object data, String connectionDetails) throws SQLException;

    public void delete(String id, Class type, String connectionDetails) throws SQLException;
}
