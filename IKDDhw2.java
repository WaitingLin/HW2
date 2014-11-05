import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.lang.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
	public class IKDDhw2 extends JFrame{
		
		private Connection con;
		private Statement st;
		private String [][] tmpTableData;
		private String [] BookField;
		private DefaultTableModel tmodel;
		private JTable book;
		private ArrayList< ArrayList<String>> myList = new ArrayList< ArrayList<String>>(); 
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			IKDDhw2 hw = new IKDDhw2("jdbc:postgresql://iServDB.cloudopenlab.org.tw:5432/mooc0102_db_2852","mooc0102_user_2852","qQxvSjAB");
			Scanner scanner = new Scanner(System.in);
			String yourInput =  scanner.nextLine();
			hw.execute("select * from \"twitter\" where q = '"+yourInput+"'");
			
		}
		public IKDDhw2(String url,String username,String password)
	{
		try{
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url,username,password);
		st = con.createStatement();
		}catch (ClassNotFoundException ee)
		    {
		      ee.printStackTrace();
		      System.exit(1);
		    }
		    catch (SQLException ee)
		    {
		      ee.printStackTrace();
		      System.exit(2);
		    }
	}

	public void execute(String SQL)
	{
		try{
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next())
				{
				ArrayList<String> temp = new ArrayList<String>();
				 temp.add(rs.getString(2));	
				 temp.add(rs.getString(3));	
				 temp.add(rs.getString(1));	
				 myList.add(temp);
				}
			rs.close();
			printResult(myList);
			}catch(Exception ee){
				System.out.println(ee.getMessage()+"gg");
				System.out.println("fk");
				}
		
	}
	public void printResult(ArrayList<ArrayList<String>> ss)
	{
		   tmpTableData = new String[ss.size()][ss.get(0).size()];
		  for(int i = 0; i < ss.size(); i++)for(int j = 0; j < ss.get(i).size(); j++)tmpTableData[i][j] = ss.get(i).get(j);
		  BookField = new String[]{"text","user_name","user_id"};
		  tmodel = new DefaultTableModel(tmpTableData,BookField);
		  book = new JTable(tmodel);
		  Container c = getContentPane();
		  c.setLayout(new BorderLayout());
		  book.getColumnModel().getColumn(2).setPreferredWidth(180);
		  book.getTableHeader().setReorderingAllowed(false);
		  book.setShowHorizontalLines(false);
		  c.add(new JScrollPane(book),BorderLayout.CENTER);
		  setSize(500,400);
		  setLocation((int)(Math.random() * 50) + 5,250);
		  setVisible(true);
	}
							}