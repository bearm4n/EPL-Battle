import java.util.*;
import java.sql.*; 

public class MyGame 
{
	
	public static void main(String args[])
	{
		System.out.println("Welcome to Premier League Battle!");
		System.out.println("It's a TOP TRUMPS rip off");
		ArrayList<Integer> newOrder = random();
		
		ArrayList<Integer> usersIDs = new ArrayList(newOrder.subList(0, 5));
		System.out.println(usersIDs.toString());
		ArrayList<String> userPlayersArray = new ArrayList();
		ArrayList<String> cpuPlayersArray = new ArrayList();

		String userPlayers = ("("+usersIDs.get(0).toString()+", "+usersIDs.get(1).toString()+", "+usersIDs.get(2).toString()+", "+usersIDs.get(3).toString()+", "+usersIDs.get(4).toString()+")");
		
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/PremierLeague","root","England1%3");  
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
