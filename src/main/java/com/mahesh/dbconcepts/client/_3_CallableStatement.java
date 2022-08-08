package com.mahesh.dbconcepts.client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _3_CallableStatement {

	public static void main(String[] args) {
		// getResultsbyEmployeeID(5);
		getEmployeeCount();
	}

	private static void getEmployeeCount() {

		String SQL = "CALL getEmployeeCount(?)";
		try (Connection connection = DBUtils.getConnection();
				CallableStatement callableStatement = connection.prepareCall(SQL);) {

			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.executeUpdate();
			System.out.println(callableStatement.getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getResultsbyEmployeeID(int id) {

		String SQL = "CALL getEmployeeByID(?)";
		try (Connection connection = DBUtils.getConnection();
				CallableStatement callableStatement = connection.prepareCall(SQL);) {

			callableStatement.setInt(1, id);
			ResultSet selectRS = callableStatement.executeQuery();
			while (selectRS.next()) {
				System.out.println(selectRS.getInt(1) + ">>>" + // employee_id
						selectRS.getString(2) + ">>>" + // employee_name
						selectRS.getString(3) + ">>>" + // email
						selectRS.getDouble(4) + ">>>" + // salary
						selectRS.getDate(5) + ">>>" + // date_of_joining
						selectRS.getBigDecimal(6));// bonus

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
