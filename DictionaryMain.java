import java.util.Iterator;
import java.util.Scanner;

public class DictionaryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary catalog = new Dictionary();
		Scanner scnr = new Scanner(System.in);
		
		catalog.add("The Weeknd", "R&B");
		catalog.add("My Chemical Romance", "Rock");
		catalog.add("Bring Me the Horizon", "Metal");
		catalog.add("Taylor Swift", "Pop");
		catalog.add("Twice", "Kpop");
		catalog.add("blink-182", "Punk");
		
		System.out.println("Welcome to the Record store! Please select an option to viewe inventory:");
		System.out.println("1. View artists\n"
						  +"2. View genres\n"
						  + "3. View catalog size\n"
						  + "4. Make a purchase\n"
						  + "5. Purchase full catalog");
		
		int store = 0;
		store = scnr.nextInt();
		switch(store) {
			case 1: 
				System.out.println("Artist: ");
				Iterator keyIterator = catalog.getKeyIterator();
				while(keyIterator.hasNext()) {
					System.out.println(keyIterator.next());
				}
				break;
			case 2:
				System.out.println("Genres: ");
				Iterator valueIterator = catalog.getValueIterator();
				while(valueIterator.hasNext()) {
					System.out.println(valueIterator.next());
				}
				break;
			case 3: 
				System.out.println("There are "+ catalog.getSize() + " artist available to purchase");
				break;
			case 4: 
				System.out.println("Please select an artist to purchase");
				scnr.nextLine();
				String purchase = scnr.nextLine();
				catalog.remove(purchase);
				System.out.println("Purchase successful!");
				System.out.println("There are " +catalog.getSize()+ "left to purchase");
				break;
			case 5: 
				catalog.clear();
				System.out.println("Thank you for purchasing all albums!");
				System.out.println("Checking if inventory is empty: " + catalog.isEmpty());
				break;
			default: 
					System.out.println("Not a valid choice");
					break;
		}
	}

}
