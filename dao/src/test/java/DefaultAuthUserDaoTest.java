import com.github.alex.dealer.dao.AuthUserDao;

import com.github.alex.dealer.dao.impl.DefaultAuthUserDao;
import com.github.alex.dealer.data.AuthUser;
import com.github.alex.dealer.data.Role;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;


public class DefaultAuthUserDaoTest {
    final AuthUserDao defaultAuthUserDao = DefaultAuthUserDao.getInstance();

    AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    @Test
    public void saveAuthUser() {

    }

    @Test
    public void getByLogin() {

    }

    @Test
    public void deleteAuthUser() {

    }

    @Test
    @DisplayName("checkExistUser method Test")
    public void checkExistUser() {
        String login = "testUser";
        String pass = "testPass";
        authUserDao.saveAuthUser(new AuthUser(null, login, pass, Role.BLOCKED, null));
        Assertions.assertEquals(authUserDao.checkExistUser(login), 1);
    }

}
