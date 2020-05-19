package map;

import java.util.List;
import java.util.Scanner;

public class SanakirjaOhjelma {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UseanKaannoksenSanakirja sanakirja = new UseanKaannoksenSanakirja();

        while (true) {
            System.out.print("Anna seuraava komento: ");
            String[] komento = input.nextLine().toLowerCase().split(" ");

            switch (komento[0]) {
            case "lisää":
                sanakirja.lisaa(komento[1], komento[2]);
                break;
            case "käännä":
                List<String> kaannokset = sanakirja.kaanna(komento[1]);
                System.out.println(kaannokset);
                break;
            case "poista":
                sanakirja.poista(komento[1]);
                break;
            case "lopeta":
                input.close();
                return;
            default:
                System.out.println("Tuntematon komento");
                break;
            }
            System.out.println();
        }
    }
}
