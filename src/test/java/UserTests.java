import ke.co.safaricom.Models.User;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTests {
    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/inventorymgt_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);

    @Test
    public void user_instantiatesCorrectly_true() {
        User testUser = new User ("Henry", "email@email.com", "admin");
        assertTrue(true);
    }
    @Test
    public void getName_userInstantiatesWithName_Henry() {
        User testUser = new User("Henry", "email@email.com", "admin");
        assertEquals("Henry", testUser.getName());
    }
    @Test
    public void getEmail_userInstantiatesWithEmail_String() {
        User testUser = new User("Henry", "email@email.com", "admin");
        assertEquals("email@email.com", testUser.getEmail());
    }
    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        User firstUser = new User("Henry", "email@email.com", "admin");
        User anotherUser = new User("Henry", "email@email.com", "admin");
        assertEquals(firstUser, anotherUser);
    }
    @Test
    public void save_insertsObjectIntoDatabase_User() {
        User testUser = new User("Henry", "[email@email.com)", "admin");
        testUser.save();
        assertEquals(User.all().get(0), testUser);
    }
    @Test
    public void all_returnsAllInstancesOfUsers_true() {
        User firstPerson = new User("Henry", "henry@henry.com", "admin");
        firstPerson.save();
        User secondPerson = new User("Harriet", "harriet@harriet.com", "admin");
        secondPerson.save();
        assertEquals(User.all().get(0), firstPerson);
        assertEquals(User.all().get(1), secondPerson);
    }
}
