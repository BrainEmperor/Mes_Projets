package gestionnaire_de_taches;

import java.sql.*;

public class AccesOnDataBase {
	private static String url="jdbc:postgresql://localhost:5432/gestionnaire_de_taches";
	private static String user="postgres";
	private static String password="Lxgiwyl0000%";
	
	static int get_max_id_from_database()
	{
		ResultSet rs = null;
		int id=0;
		try 
		{
			String query="""
					        SELECT max(id)
					        FROM tasks				
					     """;
			Connection conn= DriverManager.getConnection(url, user, password);
			Statement stm=conn.createStatement();
			rs=stm.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			if(rs.next()) {
			    id=rs.getInt("id");
			    System.out.println(id);
			}
		} 
		catch (SQLException e) 
		{
//			return 0;
		}
		return id;
	}
	
	static boolean addTask(String description, String status, String date_limite, String priority)
	{
		String query=String.format("""
				INSERT INTO  tasks (id, description, date_limite, status, priorite)
				VALUES (%d, '%s','%s', '%s', '%s');
				""", get_max_id_from_database()+1, description, date_limite, status, priority);
		System.out.println(query);
		try
		{
			Connection conn=DriverManager.getConnection(url, user, password);
			PreparedStatement stm=conn.prepareStatement(query);
			stm.executeUpdate();	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	static String modify_task(int id, String attribut, String valeur)
	{
		try {
		String query = String.format("""
				UPDATE tasks
				SET %s=%s
				WHERE id=%d;
				""",attribut, valeur, id);
		Connection conn= DriverManager.getConnection(url, user, password);
		PreparedStatement stm=conn.prepareStatement(query);
		stm.executeUpdate();
		return "Modification effectuée";
		}
		catch(SQLException e)
		{
			return "Modification non effectuée";
		}
	}
	
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		AccesOnDataBase.url = url;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		AccesOnDataBase.user = user;
	}

	public static void setPassword(String oldPassword, String password) {
		if (oldPassword!=AccesOnDataBase.password)
		    throw new IllegalArgumentException("Echec ! Veuillez entrer correctement l'ancien mot de passe");
		AccesOnDataBase.password = password;
	}
	
	static String show_all_tasks() throws SQLException
	{
		String query="SELECT * FROM tasks";
		Connection conn=DriverManager.getConnection(url, user, password);
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(query);
		String result="id\t description\t\t date_limite \t status \t priorite\n";
		while(rs.next())
		{
			result+=rs.getInt("id") + "\t" + rs.getString("description") + "\t\t" + rs.getString("date_limite") + "\t" + rs.getString("status") + "\t" + rs.getString("priorite") + "\n"  ;		
		}
		return result;
	}

}
