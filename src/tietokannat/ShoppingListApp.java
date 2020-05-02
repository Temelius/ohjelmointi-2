package tietokannat;

import java.util.List;
import java.util.Scanner;

public class ShoppingListApp {
    private static ShoppingListItemDao dao = new JDBCShoppingListItemDao();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the shopping list app!\n" + "Available commands:\n" + " list\n"
                + " add [product name]\n" + " remove [product name]\n" + " get [product ID]\n" + " quit");

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = input.next();
            String parameter = input.nextLine().trim();

            switch (command) {
            case "list":
                listAllItems();
                break;
            case "add":
                addItem(parameter);
                break;
            case "remove":
                removeItem(parameter);
                break;
            case "quit":
                running = false;
                break;
            case "get":
            	long id = Long.parseLong(parameter);
            	getItem(id);
            	break;
            default:
            	System.out.println("Command not found!");
            	break;
            }

            System.out.println();
        }

        System.out.println("Bye!");
        input.close();
    }

    private static void listAllItems() {
        System.out.println("Shopping list contents:");

        List<ShoppingListItem> items = dao.getAllItems();
        for (ShoppingListItem item : items) {
            System.out.println(item.getId() + " " + item.getTitle());
        }
    }

    private static void addItem(String title) {
        ShoppingListItem newItem = new ShoppingListItem(title);
        // todo: tulosta teksti onnistuiko
        if (dao.addItem(newItem)) {
			System.out.println("Successfully added \"" + title + "\".");
		} else {
			System.out.println("An error occurred or product might already exist.");
		}
    }

    private static void removeItem(String title) {
    	if (dao.removeItem(new ShoppingListItem(title))) {
    		System.out.println("Product \"" + title + "\" deleted successfully");
    	} else {
    		System.out.println("There was an error while deleting the product or product not found.");
    	}
    }
    
    private static void getItem (long id) {
    	ShoppingListItem result = dao.getItem(id);
    	if (result != null) {
    		System.out.println(result.getId() + " " + result.getTitle());
    	} else {
    		System.out.println("Product not found!");
    	}
    }
    
}
