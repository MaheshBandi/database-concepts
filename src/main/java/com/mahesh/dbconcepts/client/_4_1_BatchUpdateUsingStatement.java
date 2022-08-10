package com.mahesh.dbconcepts.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _4_1_BatchUpdateUsingStatement {

	public static void main(String[] args) {

		updateEmployeeBatch();
	}
	
	public static void updateEmployeeBatch() {
		
		
		try (Connection connection = DBUtils.getConnection() ; Statement ps = connection.createStatement()) {
			
			String UPDATESQL = "UPDATE employee set salary=80000 where employee_id=4";
			ps.addBatch(UPDATESQL);
			
			UPDATESQL = "UPDATE employee set salary=90000 where employee_id=5";
			ps.addBatch(UPDATESQL);
			
			UPDATESQL = "UPDATE employee set salary=100000 where employee_id=1";
			ps.addBatch(UPDATESQL);
			
			String SQL = "INSERT INTO EMPLOYEE(" + "employee_name," + "email," + "salary," + "date_of_joining,bonus)"
					+ "VALUES('Mahesh','test@email.com',1000000.00,'2022/08/08',100000.00)";
			ps.addBatch(SQL);
			
			ps.addBatch(SQL);
			
			ps.addBatch(SQL);
			
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
