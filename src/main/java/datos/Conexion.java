package datos;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=False&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private  static final String USER_PASSWORD = "Resumiendo69%";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, USER_PASSWORD);
    }


    public static void Close(ResultSet rs) throws SQLException{
        rs.close();
    }

    public static void Close(PreparedStatement statement) throws SQLException{
        statement.close();
    }

    public static void Close(Connection conn) throws  SQLException{
        conn.close();
    }
}
