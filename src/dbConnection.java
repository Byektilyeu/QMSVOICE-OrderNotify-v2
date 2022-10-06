import java.sql.*;

public class dbConnection {
    static final String userDirectory = System.getProperty("user.dir");
    public void createNewDb() {
        System.out.println("userDirectory :" + userDirectory);
        String url = "jdbc:sqlite:" + userDirectory + "\\voice.db";
        System.out.println("url :" + url);
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        String url = "jdbc:sqlite:" + userDirectory + "\\voice.db";
        String sql1 = "CREATE TABLE IF NOT EXISTS orders (\n"
                + "	visit integer PRIMARY KEY,\n"
                + "	qmsNumber integer NOT NULL,\n"
                + "	kdsState text ,\n"
                + "	voiceState boolean,\n"
                + "	guid text\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}