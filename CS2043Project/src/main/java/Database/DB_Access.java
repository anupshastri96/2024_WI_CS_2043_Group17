package Database;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DB_Access {

    public static Connection Connect(){
        Connection dbConnection = null;
        Properties dbConnectionProps = new Properties();

        try{
            FileInputStream inputStream = new FileInputStream("CS2043Project/src/main/resources/db.properties");
            dbConnectionProps.load(inputStream);

            inputStream.close();

            String url=dbConnectionProps.getProperty("url");
            String username = dbConnectionProps.getProperty("username");
            String password = dbConnectionProps.getProperty("password");

            dbConnection= DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            getSQLException(e);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return dbConnection;
    }

    public static void Closing(CallableStatement dbStatement, Connection dbConnection){
        if(dbStatement != null){
            try{
                dbStatement.close();
            } catch (SQLException e) {
                getSQLException(e);
            }

        }

        if(dbConnection != null){
            try{
                dbConnection.close();
            }
            catch(SQLException e) {
                getSQLException(e);
            }
        }
    }

    public static void ClosingResultSet(ResultSet dbResultSet){
        if(dbResultSet != null) {
            try {
                dbResultSet.close();
            } catch (SQLException ex) {
                getSQLException(ex);
            }
        }
    }

    public static void getSQLException(SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
}
