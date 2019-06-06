public class Player {
	
	//Data members for name, current score, id and card
	private String name;
	private int score;
	private int id;
	private int card;

	//Constructors to initialize Player's data members.
	public Player() {
		id =0;
		name = "Unknown Player";
		score = 0;
		card = 0;
	}

	public Player(String name, int playerID, int id) {
		this.id = id;
		this.name = name;
		score = 0;
		card = 0;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setCard(int card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public int getID() {
		return id;
	}
	
	public int getCard() {return card;}

	public static void showInfo(Player p)
	{
		System.out.println("ID: "+ p.getID() + " Name: " + p.getName() + " Score: " + p.getScore());
	}
}
