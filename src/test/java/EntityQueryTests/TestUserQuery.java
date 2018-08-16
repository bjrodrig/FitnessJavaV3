package EntityQueryTests;

import org.junit.Test;
import session.UserQueriesBean;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;

public class TestUserQuery {

    @Test
    public void testValidateUser() throws NamingException {
        final Properties p = new Properties();
        p.put("fitnessjava", "new://Resource?type=DataSource");
        p.put("fitnessjava.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("fitnessjava.JdbcUrl", "jdbc:hsqldb:mem:fitnessdb");


        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserQueriesBean instance = (UserQueriesBean) container.getContext().lookup
                ("C:\\Users\\barodriguez\\Fitness\\src\\main\\java\\session\\UserQueriesBean");
        List users = instance.validateUser("barbara");
        assertTrue(users.isEmpty());


    }
}
