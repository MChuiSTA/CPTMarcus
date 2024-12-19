import arc.*;

public class CPTMarcus{
	public static void main(String[] args){
		Console con = new Console();
		
		// Main Menu
		
		con.println("Welcome to Blackjack!");
		con.println("Would you like to play (p),");
		con.println("View High Scores (s), ");
		con.println("Help me");
		
		
		
		// Creates the deck and shuffles the cards
		marcustoolsCPT.deck(con);
		
		
		
	}
}
