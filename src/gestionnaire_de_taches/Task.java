package gestionnaire_de_taches;

public class Task {
	
	public Task(String description)
	{
		this(description, "non accomplie", "aucune", "moyenne");
	}
	
	public Task(String description, String date_limite)
	{
		this(description, "non accomplie", date_limite, "moyenne");
	}
	
	public Task(String description, String status, String date_limite, String priority)
	{
		this.setDescription(description, false);
		this.setStatus(status, false);
		this.setDate_limite(date_limite,false);
		this.setId(AccesOnDataBase.get_max_id_from_database()+1, false);
		this.setPriority(priority,false);
		boolean newTask=AccesOnDataBase.addTask(description, status, date_limite, priority);
		if (newTask)
			System.out.println("Tache ajoutée avec succès");
		else
			System.out.println("La tache n'a pas été ajoutée");
	}

	private int id;
	private String description;
	private String status;
	private String date_limite;
	private String priority;

	public int getId() {
		return id;
	}

	public void setId(int id, boolean set_on_db) {
		this.id = id;
//		if (set_on_db)
//			AccesOnDataBase.modify_task(this.getId(), "id", )
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description, boolean set_on_db) {
		if (description=="")
			throw new IllegalArgumentException("Description invalide !");
		this.description = description;
		if (set_on_db)
			AccesOnDataBase.modify_task(this.getId(), "description", description); 
	}

	public void setStatus(String status, boolean set_on_db) {
		if(status!="accomplie" && status!="non accomplie")
			throw new IllegalArgumentException("Statut non valide");
		this.status = status;
		if (set_on_db)
			AccesOnDataBase.modify_task(this.getId(), "status", status);
	}

	public String getDate_limite() {
		return date_limite;
	}

	// à améliorer
	public void setDate_limite(String date_limite, boolean set_on_db) {
		if(date_limite.matches("\\d{2}/\\d{2}/\\d{4}"))
		    this.date_limite = date_limite;
		if (set_on_db)
			AccesOnDataBase.modify_task(this.getId(), "date_limite", date_limite);
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority, boolean set_on_db) {
		this.priority = priority;
		if (set_on_db)
			AccesOnDataBase.modify_task(this.getId(), "priorite", priority);
	}
		
}
