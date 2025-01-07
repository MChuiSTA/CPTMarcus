import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTMarcus{
	public static void main(String[] args){
		Console con = new Console(1280,720);
		BufferedImage imgBlackjackTitle = con.loadImage("blackjack.jpg");
		
		// Create the variables
		char chrChoice;
		String strOption = "";
		String strName;
		int intMoney = 1000;
		int intBet;
		
		// Create the arrays
		int intDeck[][];
		intDeck = new int[52][3];
		int intPlayer[][];
		intPlayer = new int[5][2];
		int intDealer[][];
		intDealer = new int[5][2];
		
		// Create even more variables
		int intPlayerValue = 0;
		int intDealerValue = 0;
		int intDeckCount = 0;
		int intPlayerCount = 2;
		int intDealerCount = 1;
		int intCount;
		int intAceCount = 0;
		boolean blnStood = false;
		boolean blnBroken = false;
		
		// Main Menu
		con.drawImage(imgBlackjackTitle,0,0);
		con.println("Welcome to Blackjack!");
		con.println("Would you like to play (p),");
		con.println("View High Scores (s),");
		con.println("Help (h),");
		con.println("or Quit (q)");
		con.print("What would you want to do: ");
		chrChoice = con.readChar();
		con.clear();
		
		// Ask for the player name and bet
		if(chrChoice == 'p'){
			con.print("What is your name?: ");
			strName = con.readLine();
			con.println("You have $1000");
			con.print("How much do you want to bet?: ");
			intBet = con.readInt();
		}
		
		// Checks if the choice is to quit and if it is, don't do anything
		while(chrChoice != 'q'){
			// Checks if the choice is to play
			if(chrChoice == 'p'){
				if(!strOption.equalsIgnoreCase("hit") && !strOption.equalsIgnoreCase("h") && !strOption.equalsIgnoreCase("stand") && !strOption.equalsIgnoreCase("s")){
					con.clear();
					intDeck = marcustoolsCPT.shuffleDeck();
				}
				
				while(blnStood == false && blnBroken == false){
					if(intDeck[0][0] != -1){
						// The cards for the player
						con.println("PLAYER CARDS");
						intPlayer[0][0] = intDeck[0][0];
						intPlayer[0][1] = intDeck[0][1];
						con.println(intPlayer[0][0] + " | " + intPlayer[0][1]);
						intDeck[0][0] = -1;
						intPlayer[1][0] = intDeck[1][0];
						intPlayer[1][1] = intDeck[1][1];
						con.println(intPlayer[1][0] + " | " + intPlayer[1][1]);
						intDeck[1][0] = -1;
						con.println("");
						con.println("");
						con.println("");
						// Finds the value of the cards
						for(intCount = 0; intCount < intPlayerCount; intCount++){
							// Face Card Logic
							if(intPlayer[intCount][0] == 11 || intPlayer[intCount][0] == 12 || intPlayer[intCount][0] == 13){
								intPlayerValue = intPlayerValue + 10;
							// Ace Card Logic
							}else if(intPlayer[intCount][0] == 1){
								intPlayerValue = intPlayerValue + 11;
								intAceCount = intAceCount + 1;
							// Other Cards Logic
							}else{
								intPlayerValue = intPlayerValue + intPlayer[intCount][0];
							}
						}
						while(intPlayerValue > 21 && intAceCount > 0){
							intPlayerValue = intPlayerValue - 10;
							intAceCount = intAceCount - 1;
						}
					}
					
					if(intDeck[2][0] != -1){
						// The cards for the dealer
						con.println("DEALER CARDS");
						intDealer[0][0] = intDeck[2][0];
						intDealer[0][1] = intDeck[2][1];
						con.println(intDealer[0][0] + " | " + intDealer[0][1]);
						intDeck[2][0] = -1;
					}
					if(intPlayerValue != 21){
						con.print("Do you want to \"hit\" or \"stand\"?: ");
						strOption = con.readLine();
						if(strOption.equalsIgnoreCase("hit") || strOption.equalsIgnoreCase("h")){
							// Find the next available card
							while(intDeck[intDeckCount][0] == -1){
								intDeckCount = intDeckCount + 1;
								// con.println("TEST: " + intDeck[intDeckCount][0] + " | " + intDeck[intDeckCount][1]);
							}
							// Add the new card to the player's hand
							intPlayer[intPlayerCount][0] = intDeck[intDeckCount][0];
							intPlayer[intPlayerCount][1] = intDeck[intDeckCount][1];
							con.println("TEST PLAYER: " + intPlayer[intPlayerCount][0] + " | " + intPlayer[intPlayerCount][1]);
							intDeck[intDeckCount][0] = -1;
							intPlayerCount = intPlayerCount + 1;
							
							con.clear();
							intPlayerValue = 0;
							con.println("PLAYER CARDS");
							
							// Print all the player cards and the finds the value of the cards
							for(intCount = 0; intCount < intPlayerCount; intCount++){
								con.println(intPlayer[intCount][0] + " | " + intPlayer[intCount][1]);
								// Face Card Logic
								if(intPlayer[intCount][0] == 11 || intPlayer[intCount][0] == 12 || intPlayer[intCount][0] == 13){
									intPlayerValue = intPlayerValue + 10;
								// Ace Card Logic
								}else if(intPlayer[intCount][0] == 1){
									intPlayerValue = intPlayerValue + 11;
									intAceCount = intAceCount + 1;
								// Other Cards Logic
								}else{
									intPlayerValue = intPlayerValue + intPlayer[intCount][0];
								}
							}
							while(intPlayerValue > 21 && intAceCount > 0){
								intPlayerValue = intPlayerValue - 10;
								intAceCount = intAceCount - 1;
							}
							
							if(intPlayerValue >= 21){
								con.println("Final Player Value: " + intPlayerValue);
							}
							
							con.println("");
							con.println("");
							con.println("");
							
							// Continuously print the dealer's card
							con.println("DEALER CARDS");
							for(intCount = 0; intCount < intDealerCount; intCount++){
								con.println(intDealer[intCount][0] + " | " + intDealer[intCount][1]);
							}
							
							if(intPlayerValue == 21){
								con.println("Nice 21!");
								blnBroken = true;
								strOption = "stand";
								break;
							}else if(intPlayerValue > 21){
								con.println("YOU BUSTED!");
								blnBroken = true;
								strOption = "stand";
								break;
							}else{
								continue;
							}
						}else if(strOption.equalsIgnoreCase("stand") || strOption.equalsIgnoreCase("s")){
							while(intDealerValue < 17){
								// Find the next available card
								while(intDeck[intDeckCount][0] == -1){
									intDeckCount = intDeckCount + 1;
									// con.println("TEST: " + intDeck[intDeckCount][0] + " | " + intDeck[intDeckCount][1]);
								}
								
								// Add the new card to the dealer's hand
								intDealer[intDealerCount][0] = intDeck[intDeckCount][0];
								intDealer[intDealerCount][1] = intDeck[intDeckCount][1];
								// con.println("TEST DEALER: " + intDealer[intDealerCount][0] + " | " + intDealer[intDealerCount][1]);
								intDeck[intDeckCount][0] = -1;
								intDealerCount = intDealerCount + 1;
								
								con.clear();
								intDealerValue = 0;
								
								con.println("PLAYER CARDS");
								for(intCount = 0; intCount < intPlayerCount; intCount++){
									con.println(intPlayer[intCount][0] + " | " + intPlayer[intCount][1]);
								}
								
								con.println("-------");
								
								for(intCount = 0; intCount < intDealerCount; intCount++){
									con.println(intDealer[intCount][0] + " | " + intDealer[intCount][1]);
									// Face Card Logic
									if(intDealer[intCount][0] == 11 || intDealer[intCount][0] == 12 || intDealer[intCount][0] == 13){
										intDealerValue = intDealerValue + 10;
									// Ace Card Logic
									}else if(intDealer[intCount][0] == 1 && (intDealerValue + 11) > 21){
										intDealerValue = intDealerValue + 1;
									}else if(intDealer[intCount][0] == 1 && (intDealerValue + 11) <= 21){
										intDealerValue = intDealerValue + 11;
									// Other Cards Logic
									}else{
										intDealerValue = intDealerValue + intDealer[intCount][0];
									}
								}
								con.println("-------");
								con.println("Final Player Value: " + intPlayerValue);
								if(intDealerValue > 17){
									con.println("Final Dealer Value: " + intDealerValue);
								}else{
									con.println("Final Dealer Value: " + intDealerValue);
								}
								blnStood = true;
							}
						}else{
							con.println("That's not an option!");
							con.print("Do you want to \"hit\" or \"stand\"?: ");
							strOption = con.readLine();
						}
					}else{
						blnStood = true;
					}
				}
				
			}else if(chrChoice == 's'){
				TextInputFile scores = new TextInputFile("winners.txt");
				con.clear();
				while(scores.eof() == false){
					con.println(scores.readLine());
				}
				break;
			}
		}
	}
}
