package am.aca.phonebook.repository.interfaces;
import am.aca.phonebook.common.bean.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 9/16/2016.
 */
public interface UserInterface {
    User addUser(User user) throws SQLException;
    //for sign in check
    User getUser(User user) throws SQLException;
    void deleteUser(Integer id) throws SQLException;
    User editUser(User user1,User user2) throws SQLException;
    User addFriend(User user,String friendName) throws SQLException;
    User getUser(String name) throws SQLException;
    List<String> getFriendNames(Integer id) throws SQLException;
    void deleteFriend(String friendName) throws SQLException;
}
