public class Card {

	/*Pass array of integers that represent a deck of 
	 * blank and un-shuffled cards
	into constructor*/
    public static void Deck(int deck[]) {
   	 
   	 //Assign each blank card a number

   	 for (int i = 0; i < deck.length; i++) {
   		 deck[i] = i;
   	 }
    
   	 //The deck of cards are shuffled. 
   	 //Taking a random card and placing it a sequential position
   	 //Taking the card that was overriden and placing it at the overriding card's former position.
   	  
   	 for(int i = 0; i < deck.length; i++) {
   		 //Generate an index randomly in the range of 0 to 52
   		 int index = (int)(Math.random() * deck.length);
   		 
   		 int temp = deck[i];
   		
   		 deck[i] = deck[index];
   		
   		 deck[index] = temp;
   	 }
   	 
   	
    }
}
