import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class voiceRead {
    InsertApp app = new InsertApp();

    public String[] getGreaterThanZero() {
        String sql = "SELECT visit, qmsNumber, voiceState "
                + "FROM orders WHERE voiceState = 0 AND kdsState = 'ready' Limit 1";
        String qmsNum = null;
        Boolean voiceState = false;
        Integer visit = 0;

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                visit = rs.getInt("visit") ;
                qmsNum = rs.getString("qmsNumber");
                voiceState = rs.getBoolean("voiceState");
//                System.out.println("+/+/+/+/+/+/+/+??????????????????????????????????????????????????????????????????????" + qmsNum);
            }
        } catch (SQLException e) {
            System.out.println("Greater than zero error" + e.getMessage());
        }
        String vis =Integer.toString(visit);
        String[] arr = new String [2];
        arr[0] =qmsNum;
        arr[1] = vis;
        return new String[] {qmsNum,vis};
    }

    public void updateVoiceState() throws IOException {
//        String updQmsNumber = getGreaterThanZero();
        String[] arr = getGreaterThanZero();
        String updQmsNumber = arr[0];
        String vis = arr[1];
        int visit = Integer.parseInt(vis);

//        System.out.println("+/+/+/+/+/+/+/+" + updQmsNumber);
        String sql = "UPDATE orders SET voiceState = '1'  "
                + "WHERE  qmsNumber = " + updQmsNumber + " "
                + "AND visit = " + visit + " ";
        System.out.println("updateVoiceState sql query: " + sql);
        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateVoiceState error" + e.getMessage());
        }
    }
}
