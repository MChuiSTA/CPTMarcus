import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTMarcus{
	public static void main(String[] args){
		Console con = new Console("Blackjack",1280,720);
		BufferedImage imgBlackjackTitle = con.loadImage("blackjack.jpg");
		
		// Create the variables
		char chrChoice = '$';
		String strOption = "Y";
		String strName;
		int intMoney = 1000;
		int intBet;
		String strOutcome = "";
		
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
		boolean blnStandPrint = true;
		boolean blnMainMenu = true;
		
		while(blnMainMenu == true){
			// Main Menu
			con.drawImage(imgBlackjackTitle,0,0);
			con.println("Welcome to Blackjack!");
			con.println("Would you like to play (p),");
			con.println("View High Scores (v),");
			con.println("Help (h),");
			con.println("or Quit (q)");
			con.print("What would you want to do: ");
			chrChoice = con.readChar();
		
			if(chrChoice == 'p'){
				con.print("What is your name?: ");
				strName = con.readLine();
			}
			
			// Checks if the choice is to quit and if it is, don't do anything
			while(chrChoice != 'q'){
				// Checks if the choice is to play
				if(chrChoice == 'p'){
					blnMainMenu = false;
					con.clear();
					// Only happens if the user wants to play again or if chrChoice is P
					if(!strOption.equalsIgnoreCase("hit") && !strOption.equalsIgnoreCase("h") && !strOption.equalsIgnoreCase("stand") && !strOption.equalsIgnoreCase("s") && strOption.equalsIgnoreCase("y")){
						// Shuffles the deck
						intDeck = marcustoolsCPT.shuffleDeck();
						con.println("You have $" + intMoney);
						con.print("How much do you want to bet?: ");
						intBet = con.readInt();
						for(intCount = 0; intCount < 5; intCount++){
							// Resets all the variables for replay
							intPlayer[intCount][0] = 0;
							intPlayer[intCount][1] = 0;
							intDealer[intCount][0] = 0;
							intDealer[intCount][1] = 0;
							intPlayerValue = 0;
							intDealerValue = 0;
							intPlayerCount = 2;
							intDealerCount = 1;
							intDeckCount = 0;
							intAceCount = 0;
							blnStood = false;
							blnStandPrint = true;
						}
						con.clear();
					}
					
					while(blnStood == false){
						if(intDeck[0][0] != -1){
							// The cards for the player
							con.println("PLAYER CARDS");
							intPlayer[0][0] = intDeck[0][0];
							intPlayer[0][1] = intDeck[0][1];
							
							// Prints the face cards and A instead of 1, 11, 12, & 13
							if(intPlayer[0][0] == 1){
								con.println("A | " + intPlayer[0][1]);
							}else if(intPlayer[0][0] < 11){
								con.println(intPlayer[0][0] + " | " + intPlayer[0][1]);
							}else if(intPlayer[0][0] == 11){
								con.println("J | " + intPlayer[0][1]);
							}else if(intPlayer[0][0] == 12){
								con.println("Q | " + intPlayer[0][1]);
							}else{
								con.println("K | " + intPlayer[0][1]);
							}
							intDeck[0][0] = -1;
							
							intPlayer[1][0] = intDeck[1][0];
							intPlayer[1][1] = intDeck[1][1];
							if(intPlayer[1][0] == 1){
								con.println("A | " + intPlayer[1][1]);
							}else if(intPlayer[1][0] < 11){
								con.println(intPlayer[1][0] + " | " + intPlayer[1][1]);
							}else if(intPlayer[1][0] == 11){
								con.println("J | " + intPlayer[1][1]);
							}else if(intPlayer[1][0] == 12){
								con.println("Q | " + intPlayer[1][1]);
							}else{
								con.println("K | " + intPlayer[1][1]);
							}
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
							// Dynamic Ace Values
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
							if(intDealer[0][0] == 1){
								con.println("A | " + intDealer[0][1]);
							}else if(intDealer[0][0] < 11){
								con.println(intDealer[0][0] + " | " + intDealer[0][1]);
							}else if(intDealer[0][0] == 11){
								con.println("J | " + intDealer[0][1]);
							}else if(intDealer[0][0] == 12){
								con.println("Q | " + intDealer[0][1]);
							}else{
								con.println("K | " + intDealer[0][1]);
							}
							intDeck[2][0] = -1;
						}
						if(intPlayerValue < 21 || strOption.equalsIgnoreCase("stand") || strOption.equalsIgnoreCase("s")){
							
							// If user doesn't stand
							if(!strOption.equalsIgnoreCase("stand") && !strOption.equalsIgnoreCase("s")){
								con.print("Do you want to \"hit\" or \"stand\"?: ");
								strOption = con.readLine();
							}
							
							// If user hits
							if(strOption.equalsIgnoreCase("hit") || strOption.equalsIgnoreCase("h")){
								
								System.out.println("User Hit");
								
								// Find the next available card
								while(intDeck[intDeckCount][0] == -1){
									intDeckCount = intDeckCount + 1;
									// con.println("TEST: " + intDeck[intDeckCount][0] + " | " + intDeck[intDeckCount][1]);
								}
								// Add the new card to the player's hand
								intPlayer[intPlayerCount][0] = intDeck[intDeckCount][0];
								intPlayer[intPlayerCount][1] = intDeck[intDeckCount][1];
								// con.println("TEST PLAYER: " + intPlayer[intPlayerCount][0] + " | " + intPlayer[intPlayerCount][1]);
								intDeck[intDeckCount][0] = -1;
								intPlayerCount = intPlayerCount + 1;
								
								con.clear();
								intPlayerValue = 0;
								con.println("PLAYER CARDS");
								
								// Print all the player cards and the finds the value of the cards
								for(intCount = 0; intCount < intPlayerCount; intCount++){
									if(intPlayer[intCount][0] == 1){
										con.println("A | " + intPlayer[intCount][1]);
									}else if(intPlayer[intCount][0] < 11){
										con.println(intPlayer[intCount][0] + " | " + intPlayer[intCount][1]);
									}else if(intPlayer[intCount][0] == 11){
										con.println("J | " + intPlayer[intCount][1]);
									}else if(intPlayer[intCount][0] == 12){
										con.println("Q | " + intPlayer[intCount][1]);
									}else{
										con.println("K | " + intPlayer[intCount][1]);
									}
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
									if(intDealer[intCount][0] == 1){
										con.println("A | " + intDealer[intCount][1]);
									}else if(intDealer[intCount][0] < 11){
										con.println(intDealer[intCount][0] + " | " + intDealer[intCount][1]);
									}else if(intDealer[intCount][0] == 11){
										con.println("J | " + intDealer[intCount][1]);
									}else if(intDealer[intCount][0] == 12){
										con.println("Q | " + intDealer[intCount][1]);
									}else{
										con.println("K | " + intDealer[intCount][1]);
									}
								}
								
								if(intPlayerValue > 21){
									strOption = "stand";
									System.out.println("User Busted");
									blnStandPrint = false;
								}else if(intPlayerValue == 21){
									strOption = "stand";
									System.out.println("User Got 21");
									blnStandPrint = false;
								}
							}else if(strOption.equalsIgnoreCase("stand") || strOption.equalsIgnoreCase("s") || intPlayerValue >= 21){
								while(intDealerValue < 17){
									// Find the next available card
									while(intDeck[intDeckCount][0] == -1){
										intDeckCount = intDeckCount + 1;
										// con.println("TEST: " + intDeck[intDeckCount][0] + " | " + intDeck[intDeckCount][1]);
										con.sleep(500);
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
										if(intPlayer[intCount][0] == 1){
											con.println("A | " + intPlayer[intCount][1]);
										}else if(intPlayer[intCount][0] < 11){
											con.println(intPlayer[intCount][0] + " | " + intPlayer[intCount][1]);
										}else if(intPlayer[intCount][0] == 11){
											con.println("J | " + intPlayer[intCount][1]);
										}else if(intPlayer[intCount][0] == 12){
											con.println("Q | " + intPlayer[intCount][1]);
										}else{
											con.println("K | " + intPlayer[intCount][1]);
										}
									}
									
									con.println("-------");
									con.println("DEALER CARDS");
									intAceCount = 0;
									
									for(intCount = 0; intCount < intDealerCount; intCount++){
										if(intDealer[intCount][0] == 1){
											con.println("A | " + intDealer[intCount][1]);
										}else if(intDealer[intCount][0] < 11){
											con.println(intDealer[intCount][0] + " | " + intDealer[intCount][1]);
										}else if(intDealer[intCount][0] == 11){
											con.println("J | " + intDealer[intCount][1]);
										}else if(intDealer[intCount][0] == 12){
											con.println("Q | " + intDealer[intCount][1]);
										}else{
											con.println("K | " + intDealer[intCount][1]);
										}
										// Face Card Logic
										if(intDealer[intCount][0] == 11 || intDealer[intCount][0] == 12 || intDealer[intCount][0] == 13){
											intDealerValue = intDealerValue + 10;
										// Ace Card Logic
										}else if(intDealer[intCount][0] == 1){
											intDealerValue = intDealerValue + 11;
											intAceCount = intAceCount + 1;
										// Other Cards Logic
										}else{
											intDealerValue = intDealerValue + intDealer[intCount][0];
										}
									}
									
									while(intDealerValue > 21 && intAceCount > 0){
										intDealerValue = intDealerValue - 10;
										intAceCount = intAceCount - 1;
									}
									
									con.println("-------");
									con.println("Final Player Value: " + intPlayerValue);
									if(intDealerValue > 17){
										con.println("Final Dealer Value: " + intDealerValue);
										if(blnStandPrint == true){
											System.out.println("User stood");
										}
									}else{
										con.println("Final Dealer Value: " + intDealerValue);
										if(blnStandPrint == true){
											System.out.println("User stood");
										}
									}
									blnStood = true;
									
								}
							}else{
								con.println("That's not an option!");
							}
						}else{
							blnStood = true;
						}
						
						if(blnStood == true){
							con.sleep(3000);
							con.clear();
							if(intPlayerValue > 21 && intDealerValue > 21){
								con.println("It is a tie!");
								con.println("You both busted!");
								strOutcome = "tie";
							}else if(intPlayerValue < 21 && intDealerValue > 21){
								con.println("You won against the dealer!");
								con.println("The dealer busted!");
								con.println("Your total was: " + intPlayerValue);
								con.println("The dealer's total was: " + intDealerValue);
								strOutcome = "win";
							}else if(intPlayerValue > 21 && intDealerValue < 21){
								con.println("You lost against the dealer");
								con.println("You busted!");
								con.println("Your total was: " + intPlayerValue);
								con.println("The dealer's total was: " + intDealerValue);
								strOutcome = "loss";
							}else if(intPlayerValue < 21 && intDealerValue < 21){
								if(intPlayerValue > intDealerValue && intPlayerValue != intDealerValue){
									con.println("You won against the dealer!");
									con.println("Your total was: " + intPlayerValue);
									con.println("The dealer's total was: " + intDealerValue);
									strOutcome = "win";
								}else if(intDealerValue > intPlayerValue && intPlayerValue != intDealerValue){
									con.println("You lost against the dealer");
									con.println("Your total was: " + intPlayerValue);
									con.println("The dealer's total was: " + intDealerValue);
									strOutcome = "loss";
								}else{
									con.println("It is a tie!");
									con.println("You both got the same total!");
									con.println("Your totals were: " + intPlayerValue);
									strOutcome = "tie";
								}
							}
							con.print("Do you want to play again? (Y/N): ");
							strOption = con.readLine();
							
							if(strOption.equalsIgnoreCase("Y")){
								System.out.println("User played again");
							}else if(strOption.equalsIgnoreCase("N")){
								con.closeConsole();
								
							}
							
						}
					}
					
				}else if(chrChoice == 'v'){
					blnMainMenu = false;
					con.clear();
					TextInputFile scores = new TextInputFile("winners.txt");
					con.clear();
					while(scores.eof() == false){
						con.println(scores.readLine());
					}
					con.println("");
					con.println("When you want to return back to the main menu");
					con.print("Just press the \"y\" key");
					
					if(con.getChar() == 'y'){
						blnMainMenu = true;
						con.clear();
						break;
					}
					scores.close();
				}else if(chrChoice == 'h'){
					blnMainMenu = false;
					con.clear();
					con.println("RULES OF BLACKJACK");
					con.println("You try to create card totals higher than the dealer");
					con.println("However, you cannot go higher than 21 or you bust");
					con.println("Number Cards count as their number");
					con.println("The Jack, Queen, and King count as 10");
					con.println("Aces count as 11 unless that would cause them to bust, then they count as 1");
					con.println("You can hit: Get another card");
					con.println("You can stand: Take no more cards");
					con.println("You can double down: Double the bet");
					con.println("but only take one more card");
					con.println("The dealer cannot hit");
					con.println("if their total is 17 or more");
					con.println("");
					con.println("");
					con.println("When you want to return");
					con.println("back to the main menu");
					con.print("Just press the \"y\" key");
					
					
					while(con.getChar() != 'y'){
						System.out.println("Help Test");
					}
					blnMainMenu = true;
					con.clear();
					break;
					
				}
			}
			if(chrChoice == 'q'){
				con.closeConsole();
			}
		}
	}
}
