import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;


    public static void main(String[] args) {

//        String query = "select * from user where login=+79126459328";

        String query = "select code from user_authentication_code where phone=+79126459328 and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

//                int id = resultSet.getInt("id");
//                String phone = resultSet.getString("phone");
                String code = resultSet.getString("code");
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setUser_id(resultSet.getInt("user_id"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setIs_verified(resultSet.getInt("is_verified"));
//                user.setCreated_at(resultSet.getDate("created_at"));
//                user.setUpdated_at(resultSet.getDate("updated_at"));

//                System.out.println(id);
                System.out.println(code);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        worker.getSession().disconnect();
    }


    public void delete() {

        String query = "delete from user where login=+79126459328";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
//                user.setUser_id(resultSet.getInt("user_id"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setIs_verified(resultSet.getInt("is_verified"));
//                user.setCreated_at(resultSet.getDate("created_at"));
//                user.setUpdated_at(resultSet.getDate("updated_at"));

                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getPassword() {

        String code = null;
        String query = "select code from user_authentication_code where phone=+79126459328 and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                code = resultSet.getString("code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return code;
    }


}

