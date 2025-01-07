import arc.*;

public class marcustoolsCPT{
	public static int[][] shuffleDeck() {
        // Creates the deck and shuffles the cards
		// Create the int array
		int intDeck[][];
			
		// Create the parameters for the int array
		intDeck = new int[52][3];
		
		// Create the variables for the deck creation
		int intValue;
		int intCount;
		int intSuit = 1;
		int intRandom;
		
		// Loop every single card so it has a value from 1 - 13, a suit, and a random number
		for(intCount = 0; intCount <= 3; intCount++){
			for(intValue = 1; intValue <= 13; intValue++){
				intDeck[intValue - 1 + intCount*13][0] = intValue;
				intDeck[intValue - 1+ intCount*13][1] = intSuit;
				intRandom = (int)(Math.random() * 100 + 1);
				intDeck[intValue - 1 + intCount*13][2] = intRandom;
			}
			intSuit += 1;
		}
		
		// Create the variables for the sorting
		int intTempValue;
		int intTempSuit;
		int intTempRandom;
		int intRow;
		int intRow2;
		
		// Loop every single card so it uses bubble sorting and sorts the random numbers from lowest to highest
		for(intRow2 = 0; intRow2 < 52-1; intRow2++){
			for(intRow = 0; intRow < 52-1-intRow2; intRow++){
				if(intDeck[intRow][2] > intDeck[intRow + 1][2]){
					// Take the left item
					intTempValue = intDeck[intRow][0];
					intTempSuit = intDeck[intRow][1];
					intTempRandom = intDeck[intRow][2];
					// Right item moves to the left
					intDeck[intRow][0] = intDeck[intRow+1][0];
					intDeck[intRow][1] = intDeck[intRow+1][1];
					intDeck[intRow][2] = intDeck[intRow+1][2];
					// Put temporary value on the right
					intDeck[intRow+1][0] = intTempValue;
					intDeck[intRow+1][1] = intTempSuit;
					intDeck[intRow+1][2] = intTempRandom;
				}
			}
		}
		
		return intDeck;
	}
}
