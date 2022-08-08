package com.mahesh.dbconcepts.client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _2_1_BasicDMLOperationsUsingPrependStatement {

	public static void main(String[] args) {
		// createEmployeeRecord();
		// updateEmployeeRecord();
		// deleteEmployeeRecord();
		fetchData(); 

	}

	public static void createEmployeeRecord() {
		
		String SQL = "INSERT INTO EMPLOYEE(" + "employee_name," + "email," + "salary," + "date_of_joining,bonus)"
				+ "VALUES(?,?,?,?,?)";
		try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			
			preparedStatement.setString(1, "TestName1");
			preparedStatement.setString(2, "test123@emial.com");
			preparedStatement.setDouble(3, 10000.00);
			preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
			preparedStatement.setBigDecimal(5, new BigDecimal(100000.00));
			
			int executeUpdate = preparedStatement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Successfully inserted");
			} else {
				System.out.println("No Record Inserted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	public static void updateEmployeeRecord() {
		String SQL = "UPDATE EMPLOYEE SET email=? WHERE employee_id=?";
		try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			
			preparedStatement.setString(1, "test123@email.com");
			preparedStatement.setInt(2, 6);
			int executeUpdate = preparedStatement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Successfully Updated");
			} else {
				System.out.println("No Record Updated");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	public static void deleteEmployeeRecord() {
		String SQL = "DELETE FROM EMPLOYEE WHERE employee_id=?";
		try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			
			preparedStatement.setInt(1, 6);
			int executeUpdate = preparedStatement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Successfully Deleted");
			} else {
				System.out.println("No Record Deleted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	public static void fetchData() {
		String SQL = "SELECT * FROM EMPLOYEE WHERE employee_id = ?" ;
		try (Connection connection = DBUtils.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				) {

			preparedStatement.setInt(1, 4);
			ResultSet selectRS = preparedStatement.executeQuery();
			while (selectRS.next()) {
				System.out.println(selectRS.getInt(1) +">>>"+ // employee_id
						selectRS.getString(2) +">>>"+ // employee_name
						selectRS.getString(3) +">>>"+ // email
						selectRS.getDouble(4) +">>>"+ // salary
						selectRS.getDate(5) +">>>"+ // date_of_joining
						selectRS.getBigDecimal(6));// bonus

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

}
