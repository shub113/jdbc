package com.bridgeit.programs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;
/**
 * @author bridgeit
 *	 picking image from download and saving to home
 */
public class Practice2 {
	public static void main(String[]args) throws Exception{
		Scanner scan= new Scanner(System.in);
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/image","root","root");
			con.setAutoCommit(false);
			PreparedStatement stmt= con.prepareStatement("insert into images(name,pic) values(?,?) ");
			System.out.println("Enter name of pic");
			stmt.setString(1, scan.next());
			FileInputStream fin= new FileInputStream("/home/bridgeit/Downloads/pic1.jpg");
			stmt.setBinaryStream(2, fin,fin.available());
			stmt.executeUpdate();
			
			
			PreparedStatement stmt2=con.prepareStatement("select * from images");
			ResultSet rs= stmt2.executeQuery();
			FileOutputStream fout= new FileOutputStream("/home/bridgeit/pic11.jpg");
			byte[] b1=null;
			while(rs.next()){
				Blob b=rs.getBlob(2);
				b1=b.getBytes(1,(int) b.length());
				fout.write(b1);
			}
			fout.close();
			con.commit();
			con.close();
			
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
		}
	}
}
