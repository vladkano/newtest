import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;
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
            String strSshUser = "rundkvist"; // SSH loging username
            String strSshPassword = "ldvOVHvhj648cYk"; // SSH login password
            String strSshHost = "176.53.182.129"; // hostname or ip or SSH server
//            int nSshPort = 22; // remote SSH host port number
            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nLocalPort = 33066; // local port number use to bind SSH tunnel
            int nRemotePort = 33066; // remote port number of your database
            String strDbUser = "db_user"; // database loging username
            String strDbPassword = "db_user_pwd123"; // database login password

            final JSch jsch = new JSch();
            session = jsch.getSession(strSshUser, strSshHost, 22);
            session.setPassword(strSshPassword);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);

            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + forwardedPort;
            con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
                    strDbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
