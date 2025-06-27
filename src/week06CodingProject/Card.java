package week06CodingProject;

public class Card {
		private int value;
	    private String name;

	    // Creates a card.
	    public Card(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    	//Generated getters and setters to set and return the card's value and name.
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		 // Describes the card.
	    public void describe() {
	        System.out.println(name);
	    }

	    // Compares this card with another based on value.
	    public int compareRank(Card otherCard) {
	        return Integer.compare(this.value, otherCard.value);
	    }
}


