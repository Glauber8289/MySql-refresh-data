package aplication;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Connection conn= null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name,Email,BirthDate,BaseSalary,DepartmentId) "
					+ "VALUES "		
					+ "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Joao dos Santos");
			st.setString(2, "JoaodosSantos@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("17/03/1992").getTime()));
			st.setDouble(4, 3000);
			st.setInt(5, 4);
			
		int rowsAffected=st.executeUpdate();
		if (rowsAffected > 0) {
			ResultSet rs= st.getGeneratedKeys();
			while (rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Feito! ID = " + id);
				
			}
		}else {
			System.out.println("Nenhuma linha foi alterada!");
		}
		System.out.println("Feito! Linhas afetadas " + rowsAffected);
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
