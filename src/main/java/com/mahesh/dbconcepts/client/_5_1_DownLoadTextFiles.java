package com.mahesh.dbconcepts.client;

import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _5_1_DownLoadTextFiles {

	public static void main(String[] args) {
		String SQL = "SELECT * FROM storetextfile_table";
		try(Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL);
				ResultSet rs = ps.executeQuery(SQL);) {
			
			while(rs.next()) {
				int fileId = rs.getInt("file_id");
				System.out.println("fileId :"+ fileId);
				String fileName = rs.getString("file_name");
				long fileSizeInKb = rs.getLong("file_size_in_kb");
				System.out.println("fileSizeInKb :"+fileSizeInKb);
				String fileExtension = rs.getString("file_extension");
				System.out.println("fileExtension :"+fileExtension);
				Clob clob = rs.getClob("file_content");
				InputStream asciiStream = clob.getAsciiStream();
				Files.copy(asciiStream, Paths.get("src/main/resources/DownloadedFiles/"+fileName), StandardCopyOption.REPLACE_EXISTING);
			}	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
