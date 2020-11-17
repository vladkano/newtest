import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBWorker {

    private Connection con;
    private Session session;

    public Session getSession() {
        return session;
    }

    public Connection getCon() {
        return con;
    }


    public DBWorker() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://176.53.181.34/new_poisondrop";
            String username = "pd_prod_user";
            String password = "cGXIH452yfgYTus@%yt";
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
