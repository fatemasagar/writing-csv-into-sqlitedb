import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;

public class Main {
	
	static String csvDatabase = "Sample.db";
	static String csvFile = "C:\\Users\\Dell\\eclipse-workspace\\CSVwithDatabase\\MySample.csv";
	static String split_char = ",";
	private static CSVReader reader;
	public static void main(String[] args) throws IOException, SQLException {
		sqliteConnection.createNewDatabase("Sample.db");
		sqliteConnection.createNewTable("Sample.db");
		
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] nextline;
			while((nextline = reader.readNext()) != null )
			{				
				if(nextline != null) {
					System.out.println(Arrays.toString(nextline));
				}
				String ar = Arrays.toString(nextline);
				Object[] row = ar.split(split_char);
				for(int i=0; i<row.length; i++)
				{
					if(i==0) {
						String temp = String.valueOf(row[i]).substring(1);
						row[0] = temp;
					}
					if(i==3) {
						String temp = String.valueOf(row[3]).replace(']', ' ');
						row[3] = temp;
					}
					System.out.println(row[i]);
				}
				
				String[] data = (String[]) row; 
				int ab = Integer.parseInt(data[0]);
				sqliteConnection.insertData(csvDatabase,ab, data[1], data[2], data[3]);
			}
		} finally {
			reader.close();
		}		
	}	
}