import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertApp {

    static final String userDirectory = System.getProperty("user.dir");

    public Connection connect() {
        String url = "jdbc:sqlite:" + userDirectory + "\\voice.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(Integer visit, Integer qmsNumber, String kdsState, Boolean voiceState1, String guid) {
        String sql = "INSERT or REPLACE INTO orders(visit, qmsNumber, kdsState, voiceState, guid) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = connect().prepareStatement(sql)) {
            pstmt.setInt(1, visit);
            pstmt.setInt(2, qmsNumber);
            pstmt.setString(3, kdsState);
            pstmt.setBoolean(4, voiceState1);
            pstmt.setString(5, guid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}