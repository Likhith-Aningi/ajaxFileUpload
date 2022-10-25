
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.http.Part;
/**
 *
 * @author Likhith
 */
public class Dao {

    @SuppressWarnings("CallToPrintStackTrace")
    void uplink(Part image, String uploadPath) throws ClassNotFoundException {
        try ( FileOutputStream fos = new FileOutputStream(uploadPath)) {
            InputStream is = image.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
        } catch (IOException e) {
           e.printStackTrace();
        }
        System.out.println("upload done to " + uploadPath);
        
        
        
        
        //entering to database
        String prep = "INSERT INTO `Images` (`ipath`) VALUES (?);";
        String url = "jdbc:mysql://localhost:3306/employees";
        String uname = "root";
        String pass = "mysql@Way2";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection con = DriverManager.getConnection(url, uname, pass)) {
            PreparedStatement ps = con.prepareStatement(prep);
            ps.setString(1, uploadPath);
            int result = ps.executeUpdate();
            //System.out.println(result);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
