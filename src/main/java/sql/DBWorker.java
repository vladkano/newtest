package sql;

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


      //Новый БОЙ

        try {
            String strSshUser = "poisondrop"; // SSH loging username
            String strSshHost = "77.223.106.149";
            String strRemoteHost = "localhost"; // hostname or ip of your database server
            int nRemotePort = 3306; // remote port number of your database
            String strDbUser = "pd_prod_user"; // database loging username
            String strDbPassword = "cGXIH452yfgYTus@%yt"; // database login password

            final JSch jsch = new JSch();
            jsch.addIdentity("/Users/viktoriarundkvist/Downloads/id_rsa");
            session = jsch.getSession(strSshUser, strSshHost, 10022);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);

            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + forwardedPort;
            con = DriverManager.getConnection(url + "/new_poisondrop", strDbUser,
                    strDbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Новый Тест

//        try {
//            String strSshUser = "user_qa"; // SSH loging username
//            String strSshHost = "77.223.106.150";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 33366; // remote port number of your database
//            String strDbUser = "db_user"; // database loging username
//            String strDbPassword = "db_user_pwd123"; // database login password
//
//            final JSch jsch = new JSch();
//            jsch.addIdentity("D:\\.ssh\\id_rsa");
//            session = jsch.getSession(strSshUser, strSshHost, 10022);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        //Накст

//        try {
//            String strSshUser = "poisondrop"; // SSH loging username
//            String strSshHost = "77.223.123.138";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 3306; // remote port number of your database
//            String strDbUser = "pd_prod_user"; // database loging username
//            String strDbPassword = "cGXIH452yfgYTus@%yt"; // database login password
//
//            final JSch jsch = new JSch();
//
//            session = jsch.getSession(strSshUser, strSshHost, 10022);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            config.put("PreferredAuthentications", "password");
//            session.setPassword("rHCkTLBLRqz7oQICFE");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/new_poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //1С(Сталинград)

//        try {
//            String strSshUser = "poisondrop"; // SSH loging username
//            String strSshHost = "77.223.123.139";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 3306; // remote port number of your database
//            String strDbUser = "poisondrop"; // database loging username
//            String strDbPassword = "KVOqtATBqnHqCHb0q0"; // database login password
//
//            final JSch jsch = new JSch();
//            session = jsch.getSession(strSshUser, strSshHost, 22);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            config.put("PreferredAuthentications", "password");
//            session.setPassword("Pr7Lx2XKHTgHCSJSHG");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }






        //БОЙ

//        try {
//            String strSshUser = "rundkvist"; // SSH loging username
//            String strSshHost = "176.53.181.34";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 3306; // remote port number of your database
//            String strDbUser = "pd_prod_user"; // database loging username
//            String strDbPassword = "cGXIH452yfgYTus@%yt"; // database login password
//
//            final JSch jsch = new JSch();
//            jsch.addIdentity("C:\\Users\\Квист\\.ssh\\id_rsa");
//            session = jsch.getSession(strSshUser, strSshHost, 10022);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/new_poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //Тест

//        try {
//            String strSshUser = "rundkvist"; // SSH loging username
//            String strSshPassword = "ldvOVHvhj648cYk"; // SSH login password
////            String strSshPassword = "bhoVH3209Udhovdfr66Jdf99"; // SSH login password
//            String strSshHost = "176.53.182.129"; // hostname or ip or SSH server
////            String strSshHost = "176.53.181.34";
////            int nSshPort = 22; // remote SSH host port number
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
////            int nLocalPort = 33066; // local port number use to bind SSH tunnel
//            int nRemotePort = 33066; // remote port number of your database
//            String strDbUser = "db_user"; // database loging username
//            String strDbPassword = "db_user_pwd123"; // database login password
//
//            final JSch jsch = new JSch();
//            session = jsch.getSession(strSshUser, strSshHost, 22);
//            session.setPassword(strSshPassword);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }



}
