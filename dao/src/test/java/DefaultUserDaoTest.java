import com.github.alex.dealer.dao.UserDao;
import com.github.alex.dealer.dao.impl.DefaultUserDao;
import com.github.alex.dealer.data.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class DefaultUserDaoTest {
    User user1 = new User(null, "Евгений", "Шугальский", "+375297666725");
    User user2 = new User(null, "Саша", "Кривошей", "+375293181024");
    UserDao userDao = DefaultUserDao.getInstance();

    @Test
    @DisplayName("saveUser test")
    public void saveUser() {
        UserDao userDao = new DefaultUserDao();
        userDao.save(user1);
        Assert.assertEquals("Евгений", user1.getFirstName());
        //todo Запилить тест


    }

    @Test
    @DisplayName("idByFirstNameAndlastName")
    public void idByFirstNameAndlastName() {
        //todo        ;
    }
}


