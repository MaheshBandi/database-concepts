package com.mahesh.dbconcepts.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _2_BasicDMLOperationsUsingStatement {

	public static void main(String[] args) {
		// createEmployeeRecord();
		// updateEmployeeRecord();
		// deleteEmployeeRecord();
		fetchData(); 

	}

	public static void createEmployeeRecord() {
		try (Connection connection = DBUtils.getConnection(); Statement statement = connection.createStatement()) {
			String SQL = "INSERT INTO EMPLOYEE(" + "employee_name," + "email," + "salary," + "date_of_joining,bonus)"
					+ "VALUES('Mahesh','test@email.com',1000000.00,'2022/08/08',100000.00)";
			int executeUpdate = statement.executeUpdate(SQL);

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
		try (Connection connection = DBUtils.getConnection(); Statement statement = connection.createStatement()) {
			String SQL = "UPDATE EMPLOYEE SET email='test1@email.com' WHERE employee_id=3";
			int executeUpdate = statement.executeUpdate(SQL);

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
		try (Connection connection = DBUtils.getConnection(); Statement statement = connection.createStatement()) {
			String SQL = "DELETE FROM EMPLOYEE WHERE employee_id=3";
			int executeUpdate = statement.executeUpdate(SQL);

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
		try (Connection connection = DBUtils.getConnection(); Statement statement = connection.createStatement()) {
			String SQL = "SELECT * FROM EMPLOYEE";
			ResultSet selectRS = statement.executeQuery(SQL);

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
