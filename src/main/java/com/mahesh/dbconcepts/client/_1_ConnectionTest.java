package com.mahesh.dbconcepts.client;

import java.sql.Connection;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _1_ConnectionTest {

	public static void main(String[] args) {
		Connection connection = DBUtils.getConnection();
		
		if(connection!=null) {
			System.out.println("DB Connected");
		}else {
			System.out.println("DB Not Connected");
		}
		

	}

}
