import java.sql.*;

public class sqliteConnection {	
	
	public static void createNewDatabase(String filename)
	{
		Connection con = null;
		String url = "jdbc:sqlite:C:\\Users\\Dell\\eclipse-workspace\\CSVwithDatabase\\" + filename;
		try {
				System.out.println("A new databse has been created...!!!");				
				con = DriverManager.getConnection(url);
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} finally {
			try {
				if(con != null)
				{
					con.close();
				}
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}
	public static void createNewTable( String Filename) {
		String url = "jdbc:sqlite:C:\\Users\\Dell\\eclipse-workspace\\CSVwithDatabase\\" + Filename;
		String sql = "CREATE TABLE IF NOT EXISTS People (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	contact_no text NOT NULL,\n"
                + " address text NOT NULL \n"
                + ");";
        System.out.println("Table created...!!!");
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	@SuppressWarnings("null")
	public static void insertData(String Filename, int id, String name, String contact_no, String address) {
		String url = "jdbc:sqlite:C:\\Users\\Dell\\eclipse-workspace\\CSVwithDatabase\\" + Filename;
        String sql = "INSERT INTO People(id, name, contact_no, address) VALUES(?,?,?,?)";
       
        try (Connection conn = DriverManager.getConnection(url)) {
        	Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //String sql1 = "INSERT INTO People (id,name,contact_no,address) " + "VALUES (" +id +",'" +name+ "', '" + contact_no + "','" + address + "')";
        	//stmt.executeUpdate(sql1);
            conn.setAutoCommit(false);
        	pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, contact_no);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	
        }
        System.out.println("Record Inserted...!!!");
    }
}
