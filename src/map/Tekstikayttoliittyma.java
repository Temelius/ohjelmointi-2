package map;

import java.util.Scanner;

public class Tekstikayttoliittyma {

	public static void main(String[] args) {
		
		Sanakirja sanakirja = new Sanakirja();
		Scanner lukija = new Scanner(System.in);
		
		System.out.println(
					"Komennot:"
					+ "\n\tlisaa - lisää sanaparin sanakirjaan" 
					+ "\n\tkaanna - kysyy sanan ja tulostaa sen käännöksen" 
					+ "\n\tlopeta - poistuu käyttöliittymästä"
				);
		
		while (true) {
			System.out.print("\nKomento: ");
			String komento = lukija.nextLine();
			
			switch (komento) {
			case "lisaa":
				System.out.print("Suomeksi: ");
				String sana = lukija.nextLine();
				
				System.out.print("Anna käännös: ");
				String kaannos = lukija.nextLine();
				sanakirja.lisaa(sana, kaannos);
				break;
			case "kaanna":
				System.out.print("Anna sana: ");
				sana = lukija.nextLine();
				System.out.println("Käännös: " + sanakirja.kaanna(sana));
				break;
			case "lopeta":
				lukija.close();
				System.out.println("Hei hei!");
				System.exit(0);
				break;
			default:
				System.out.println("Tuntematon komento.");
				break;
			}
		}

	}

}
