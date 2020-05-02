package tietokannat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBCShoppingListItemDao toteuttaa rajapinnan, ja sisältää konkreettisen
 * SQL-logiikan
 * TODO: Teemulle muistutus (Ei sinulle opettaja :)!): Tee JUnit testit tälle hässäkälle!
 */
public class JDBCShoppingListItemDao implements ShoppingListItemDao {

    // Tietokannan sijainti voidaan asettaa myös ympäristömuuttujaan, katso:
    // https://git.io/JfkVk
    private static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");
    // private static final String JDBC_URL = "jdbc:sqlite:db/shoppingList.sqlite";
    
    @Override
    public List<ShoppingListItem> getAllItems() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        List<ShoppingListItem> items = new ArrayList<>();

        try {
            // muodostetaan yhteys tietokantaan
            connection = DriverManager.getConnection(JDBC_URL);

            // muodostetaan kysely "SELECT * FROM ShoppingListItem"
            statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");

            // suoritetaan kysely
            results = statement.executeQuery();

            // tulostetaan kaikki tuloksena saadut rivit
            while (results.next()) {
                long id = results.getLong("id");
                String title = results.getString("title");
                ShoppingListItem newItem = new ShoppingListItem(id, title);

                items.add(newItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(connection, statement, results);
        }
        return items;
    }

    @Override
    public ShoppingListItem getItem(long id) {
    	
    	List<ShoppingListItem> items = getAllItems();
    	
    	for (ShoppingListItem item : items) {
			if (item.getId() == id) {
				return new ShoppingListItem(item.getId(), item.getTitle());
			}
		}
    	
        return null;
    }

    @Override
    public boolean addItem(ShoppingListItem newItem) {
        // Suoritetaan insert-komento, jossa title-parametriksi asetetaan
        // `newItem.getTitle()`
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
    	try {
    		// Katsotaan onko tuote jo olemassa tietokannassa.
    		List<ShoppingListItem> items = getAllItems();
    		for (ShoppingListItem listItem : items) {
				if (listItem.getTitle().equalsIgnoreCase(newItem.getTitle())) {
					return false;
				}
			}
    		connection = DriverManager.getConnection(JDBC_URL);
    		
    		statement = connection.prepareStatement("INSERT INTO ShoppingListItem (title) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
    		
    		// Muista käyttää PreparedStatement-luokan setString-metodia!
    		statement.setString(1, newItem.getTitle());
    		
    		int count = statement.executeUpdate();
    		
    		// voit myös asettaa generoidun id:n newItem-oliolle setId-metodilla, katso:
    		// https://git.io/JfkVJ
    		result = statement.getGeneratedKeys();
    		result.next();
    		
    		int generatedId = result.getInt(1);
    		newItem.setId(generatedId);

    		// Tarkistaa onko uusi rivi lisätty ja palauttaa true jos onnistui.
    		// Ei ollut mitään hajua miten tämä javassa toimii niin otin täältä referenssiä ->
    		// https://stackoverflow.com/questions/24378270/how-do-you-determine-if-an-insert-or-update-was-successful-using-java-and-mysql
    		if (count > 0) {
    			return true;
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, result);
		}
    	
    	return false;

    }

    @Override
    public boolean removeItem(ShoppingListItem item) {
    	Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet results = null;
    	
    	List<ShoppingListItem> items = getAllItems();
    	
    	try {
    		
			connection = DriverManager.getConnection(JDBC_URL);
			statement = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE id = ?");
    		
        	for (ShoppingListItem listItem : items) {
    			if (item.getTitle().equalsIgnoreCase(listItem.getTitle())) {
    				
    				statement.setLong(1, listItem.getId());
    				
    				if (statement.executeUpdate() > 0) return true;
    				
    			}
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, results);
		}
    	
    	
        return false;
    }

    private void close(Connection connection, PreparedStatement statement, ResultSet results) {
        if (results != null) {
            try {
                results.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}