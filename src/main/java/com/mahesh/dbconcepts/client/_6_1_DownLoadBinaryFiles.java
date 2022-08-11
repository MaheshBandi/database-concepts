package com.mahesh.dbconcepts.client;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _6_1_DownLoadBinaryFiles {

	public static void main(String[] args) {
		String SQL = "SELECT * FROM storebinaryfile_table";
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
				Blob blob = rs.getBlob("file_content");
				InputStream asciiStream = blob.getBinaryStream();
				Files.copy(asciiStream, Paths.get("src/main/resources/DownloadedFiles/"+fileName), StandardCopyOption.REPLACE_EXISTING);
			}	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
