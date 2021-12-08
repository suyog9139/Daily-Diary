import java.sql.*;

class MysqlConnectionJDBC {
    static String date = "";
    static String time = "";
    static String note = "";
    static String study = "";
    static String hour = "";
    static String log = "";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "routine";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "2110";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL_FULL = DB_URL + DB_NAME;
    private static Connection connection;

    public static void CreateTable() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS diary" +
                    "(Date VARCHAR(45) NOT NULL, " +
                    "Time VARCHAR(45) NOT NULL, " +
                    "Note VARCHAR(45) NOT NULL, " +
                    "Topic_of_Study VARCHAR(45) NOT NULL, " +
                    "How_Many_Hours VARCHAR(45) NOT NULL, " +
                    "Remarks VARCHAR(45) NOT NULL);";
            statement.executeUpdate(sql);
            System.out.println("Created table in given database...");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddElement(String date, String time, String note, String study, String hour, String log) {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO diary (Date , Time , Note , Topic_of_Study , How_Many_Hours , Remarks) VALUES ('"
                            + date + "','" + time + "','" + note + "','" + study + "','" + hour + "','" + log + "');");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ViewAll() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM diary");
            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void Delete(String date, String time) {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM diary WHERE Date = '" + date + "' AND Time = '" + time + "'");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet Search(String date, String time, String note, String study, String hour, String log) {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM diary WHERE Date = '" + date
                    + "' OR Time= '" + time + "' OR Note= '" + note + "' OR Topic_of_Study= '" + study
                    + "' OR How_Many_Hours= '" + hour + "' OR Remarks= '" + log + "' ";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void Update(String date, String time, String note, String study, String hour, String log) {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_FULL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE diary SET Date = '" + date + "', Time = '" + time + "', Note = '" + note
                    + "', Topic_of_Study = '" + study + "', How_Many_Hours = '" + hour + "', Remarks = '" + log
                    + "' WHERE Date = '" + date + "'" + "AND Time = '" + time + "'");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

