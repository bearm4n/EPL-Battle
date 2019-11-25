import java.util.*;
import java.sql.*; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class MyGame 
{

	public static void main(String args[])
	{
		JFrame frame = new JFrame("Premier League Battle");
        GridLayout experimentLayout = new GridLayout(0,1);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setLayout(experimentLayout);
        
        
        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.gray);

        JPanel cpuPanel = new JPanel(); 

        
        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Pictures/card.png"); 
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        
        JLabel label_player_name = new JLabel("Player Name:");
        JLabel label_cpu = new JLabel("Computer");
            
        JButton start = new JButton("Submit");
        start.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	
            }
        });
        userPanel.add(label_player_name); 
        userPanel.add(start);
        //userPanel.add(thumb);
        cpuPanel.add(label_cpu); 
        cpuPanel.add(thumb);

        frame.getContentPane().add(cpuPanel);	//put the controls at the bottom of the GUI
        frame.getContentPane().add(userPanel);	//put the controls at the bottom of the GUI

        frame.setVisible(true);	
		
		
		System.out.println("Welcome to Premier League Battle!");
		System.out.println("It's a TOP TRUMPS rip off");
		ArrayList<Integer> newOrder = random();
		ArrayList<Integer> usersIDs = new ArrayList<Integer>(newOrder.subList(0, 5));
		ArrayList<String> userPlayersArray = new ArrayList<String>();
		ArrayList<String> cpuPlayersArray = new ArrayList<String>();

		String userPlayers = ("("+usersIDs.get(0).toString()+", "+usersIDs.get(1).toString()+", "+usersIDs.get(2).toString()+", "+usersIDs.get(3).toString()+", "+usersIDs.get(4).toString()+")");
		
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/PremierLeague","root","");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from players where id in "+userPlayers);  
			while(rs.next())  
				userPlayersArray.add(String.valueOf(rs.getInt(1))
				+", "+rs.getString(2)
				+", "+String.valueOf(rs.getInt(3))
				+", "+String.valueOf(rs.getInt(4))
				+", "+String.valueOf(rs.getInt(5))
				+", "+String.valueOf(rs.getInt(6))
				+", "+String.valueOf(rs.getInt(7))
				+", "+String.valueOf(rs.getInt(8)));
			
			ResultSet rs2=stmt.executeQuery("select * from players where id not in "+userPlayers);  
			while(rs2.next())  
				cpuPlayersArray.add(String.valueOf(rs2.getInt(1))
				+", "+rs2.getString(2)
				+", "+String.valueOf(rs2.getInt(3))
				+", "+String.valueOf(rs2.getInt(4))
				+", "+String.valueOf(rs2.getInt(5))
				+", "+String.valueOf(rs2.getInt(6))
				+", "+String.valueOf(rs2.getInt(7))
				+", "+String.valueOf(rs2.getInt(8)));
			
			con.close();  
		}
		
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		
		Collections.shuffle(userPlayersArray, new Random()); 
		
		int round = 1;
		int userTopStat = 0;
		int cpuTopStat = 0;

		for(int i = 0; i<5; i++)
		{
			String array1[]= userPlayersArray.get(i).split(", ");
			for (String temp: array1)
			{
			      System.out.println(temp);
			}
		}
		
		//userTopStat = 
		//cpuTopStat = 
	}
	
	public static ArrayList<Integer> random()
	{
		int[] ids = {1,2,3,4,5,6,7,8,9,10};
		ArrayList<Integer> randomPick = new ArrayList<Integer>() ;
		for (int id: ids) 
		{
            randomPick.add(id);
        }
		Collections.shuffle(randomPick, new Random()); 
		return randomPick;
	}

}
