package gestionnaire_de_taches;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager {
	
	static Scanner eingabe=new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		String menu="""
				    -----Menu-----
				1. Ajouter une tache
				2. Voir toutes les taches
				3. Supprimer une tache
				4. Marquer une tache comme faite
				5. Filter les taches
				6. Quitter l'application
				""";
		int numMenu;
		do
		{
			System.out.println(menu);
			numMenu=eingabe.nextInt();
			eingabe.nextLine();
			
			switch(numMenu)
			{
			case 1 :
				System.out.println("Entrez la description et eventuellement une date limite");
				String desc=eingabe.nextLine();
				String date_limite=eingabe.nextLine();
				if(date_limite=="")
					new Task(desc);
				else 
					new Task(desc, date_limite);
				break;
			case 2 :
				System.out.println(AccesOnDataBase.show_all_tasks());
				break;
			case 3 :
			case 4 : 
				System.out.println("id ? :");
				int id=eingabe.nextInt();
				eingabe.nextLine();
				System.out.println("Que voulez vous modifier ? :");
				String attribut=eingabe.nextLine();
				System.out.println("Par quoi ?");
				String valeur=eingabe.nextLine();
				AccesOnDataBase.modify_task(id, attribut, valeur);
				break;
			case 5 :
			case 6 :
			default :
				continue;
			}
		}
		while(numMenu!=6);

	}

}
