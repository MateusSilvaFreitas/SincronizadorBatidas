package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static String USERNAME = "root";
    public static String PASSWORD = "Projeto!#2021";
    public static String URL_CONEXAO = "jdbc:mysql://projeto-integrador.ci3ylwgfbzp4.us-east-1.rds.amazonaws.com:3306/projeto_integrador?characterEncoding=UTF-8";
//    host: 'projeto-integrador.ci3ylwgfbzp4.us-east-1.rds.amazonaws.com',
//    user: 'root',
//    password: 'Projeto!#2021',
//    database: 'projeto_integrador'

    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL_CONEXAO, USERNAME, PASSWORD);
        return connection;
    }
}
