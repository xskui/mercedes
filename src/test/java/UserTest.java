import com.redemption.shawshank.web.service.inter.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author : xingshukui .
 * Date : 2017/7/8.
 * Desc :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;


    @Test
    public void query(){
        long id = 1l;
        userService.findOne(id);
    }
}
