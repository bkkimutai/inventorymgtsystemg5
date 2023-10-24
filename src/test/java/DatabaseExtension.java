import ke.co.safaricom.DB.DB;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseExtension implements BeforeEachCallback, AfterEachCallback {
    private final Sql2o sql2o;

    public DatabaseExtension(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void beforeEach(ExtensionContext context) {
        try (Connection con = sql2o.open()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY, name varchar, email varchar, usertype varchar);";
            con.createQuery(createTableQuery).executeUpdate();
        }
    }
    @Override
    public void afterEach(ExtensionContext context) {
        try (Connection con = sql2o.open()) {
            String deleteUsersQuery = "DELETE FROM users;";
            con.createQuery(deleteUsersQuery).executeUpdate();
        }
    }
}
