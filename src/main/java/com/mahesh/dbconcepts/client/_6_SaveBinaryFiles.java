package com.mahesh.dbconcepts.client;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mahesh.dbconcepts.utils.DBUtils;

public class _6_SaveBinaryFiles {

	public static void main(String[] args) {
		String SQl = "INSERT INTO storebinaryfile_table"
				+ "(file_name,file_size_in_kb,file_extension,file_content)"
				+ "VALUES"
				+ "(?,?,?,?)";
		Path dir = Paths.get("src/main/resources/InputBinaryFiles");
		try(Stream<Path> list = Files.list(dir);
				Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQl);
				) {
			List<Path> pathList = list.collect(Collectors.toList());
			
			for(Path path : pathList) {
				System.out.println(path.getFileName());
				File file = path.toFile();
				String fileName = file.getName();
				long fileLength = file.length();
				long fileLengthInKb = fileLength/1024;
				
				ps.setString(1, fileName);
				ps.setLong(2, fileLengthInKb);
				ps.setString(3, fileName.substring(fileName.lastIndexOf(".")+1));
				ps.setBinaryStream(4, new FileInputStream(file),fileLength);
				ps.addBatch();
			}
			int[] executeBatch = ps.executeBatch();
			
			for(int i : executeBatch) {
				System.out.println(i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
