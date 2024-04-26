import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales", "root", "admin");
            System.out.println("OK!");

            Statement myStmt = con.createStatement();
            ResultSet myResults = myStmt.executeQuery("SELECT * FROM sales.item");
            while(myResults.next()) {
                String descriptions = myResults.getString("descriptions");
                //only works if there really is a column called firstname in the Employee table!
                System.out.println(descriptions);
            }

            //MAP obj
            // List<User> users=new ArrayList<User>();

            // while(rs.next()) {
            //     User user = new User();      
            //     user.setUserId(rs.getString("UserId"));
            //     user.setFName(rs.getString("FirstName"));

            //     users.add(user);
            // } 
            
        } catch (SQLException e) {
            
            System.out.println("SQL Error --->");
            System.out.println(e.getMessage()); // prints out the java error
            System.out.println(e.getSQLState()); //this will print out the estate
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class error -- " + e.getMessage());
            System.out.println("Class Trace -- " + e.getStackTrace());
        }
    }
}
