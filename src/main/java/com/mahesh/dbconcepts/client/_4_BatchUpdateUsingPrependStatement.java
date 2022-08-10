package com.mahesh.dbconcepts.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _4_BatchUpdateUsingPrependStatement {

	public static void main(String[] args) {

		updateEmployeeBatch();
	}
	
	public static void updateEmployeeBatch() {
		
		String UPDATESQL = "UPDATE employee set salary=? where employee_id=?";
		try (Connection connection = DBUtils.getConnection() ; PreparedStatement ps = connection.prepareStatement(UPDATESQL)) {
			
			ps.setDouble(1, 700000);
			ps.setDouble(2, 1);
			ps.addBatch();

			ps.setDouble(1, 700000);
			ps.setDouble(2, 2);
			ps.addBatch();
			
			ps.setDouble(1, 700000);
			ps.setDouble(2, 3);
			ps.addBatch();
			
			ps.setDouble(1, 700000);
			ps.setDouble(2, 4);
			ps.addBatch();
			
			ps.setDouble(1, 700000);
			ps.setDouble(2, 5);
			ps.addBatch();
			
			ps.setDouble(1, 700000);
			ps.setDouble(2, 6);
			ps.addBatch();
			
			int[] executeBatch = ps.executeBatch();
			
			for (int i = 0; i < executeBatch.length; i++) {
				
				System.out.println(executeBatch[i]);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
