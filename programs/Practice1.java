package com.bridgeit.programs;

import java.sql.*;
import java.util.Scanner;		
public class Practice1 {
	public static void main(String[]args){
		Connection con=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root", "root");
			con.setAutoCommit(false);
			while(true){
				System.out.println("Choose your option");
				System.out.println("1) Insert \n2) Delete \n3) View \n4) Edit \n0) Exit");
				Scanner scan= new Scanner(System.in);
				int choice= scan.nextInt();
				switch(choice){
				case 0:
					con.close();
					System.exit(0);
					break;
				case 1:
					PreparedStatement stmt= con.prepareStatement("insert into emp values(?,?,?)");
					System.out.println("Enter employee id");
					stmt.setString(1, scan.next());
					System.out.println("Enter employee name");
					stmt.setString(2, scan.next());
					System.out.println("Enter employee age");
					stmt.setInt(3, scan.nextInt());
					
					stmt.executeUpdate();
					con.commit();
					//con.close();
					break;
				case 2:
					System.out.println("Delete by\n1) employee name\n2) employee id");
					int choice2= scan.nextInt();
					if(choice2==1){
						PreparedStatement stmt2= con.prepareStatement("delete from emp where name=?");
						System.out.println("Enter employee name");
						stmt2.setString(1, scan.next());
						stmt2.executeUpdate();
						//con.close();	
					}else if(choice2==2){
						PreparedStatement stmt2= con.prepareStatement("delete from emp where id=?");
						System.out.println("Enter Employee id");
						stmt2.setString(1, scan.next());
						stmt2.executeUpdate();
						con.commit();
						//con.close();
					}else{ System.out.println("Invalid choice");}
					break;
				case 3:
					PreparedStatement stmt3= con.prepareStatement("select * from emp");
					ResultSet rs= stmt3.executeQuery();
					while(rs.next()){
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
					}
					break;
				case 4:
					System.out.println("1)id \n2)name \n3)age)");
					int choice3=scan.nextInt();
					switch(choice3){
					case 1:
						PreparedStatement stmt4= con.prepareStatement("update emp set id=? where name=?");
						System.out.println("Enter name of employee");
						stmt4.setString(2, scan.next());
						System.out.println("Enter id of employee");
						stmt4.setString(1, scan.next());
						stmt4.executeUpdate();
						con.commit();
						break;
					case 2:
						PreparedStatement stmt5 = con.prepareStatement("update emp set name=? where id=?");
						System.out.println("Enter id of employee");
						stmt5.setString(2, scan.next());
						System.out.println("Enter name of employee");
						stmt5.setString(1, scan.next());
						stmt5.executeUpdate();
						con.commit();
						break;
					case 3:
						PreparedStatement stmt6= con.prepareStatement("update emp set age=? where name=?");
						System.out.println("Enter employee name");
						stmt6.setString(2, scan.next());
						System.out.println("Enter employee age");
						stmt6.setInt(1, scan.nextInt());
						stmt6.executeUpdate();
						con.commit();
						break;
						default:
							System.out.println("Invalid option");
					}break;
				default: System.out.println("Invalid choice");
				}
			}
			
		}catch(Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
