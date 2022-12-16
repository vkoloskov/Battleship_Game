package battleship;

import java.util.Scanner;

enum ShipE {

    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine",3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);
    int size;
    String name;
    ShipE(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class Main {

    public static void main(String[] args) {
        final int amountOfShips = 5;
        final int[] shipsSizes = {5,4,3,3,2};
        final String[] shipsNames = {"Aircraft Carrier","Battleship","Submarine","Cruiser","Destroyer"};
        Field battlefield = new Field();
        /*Ship[] battleships = {new Ship(5,"Aircraft Carrier"),
                new Ship(4, "Battleship"),
                new Ship(3, "Submarine"),
                new Ship(3, "Cruiser"),
                new Ship(3, "Destroyer") };*/
        Scanner scanner = new Scanner(System.in);
        int shipsIterator = 0;



        battlefield.printField(false);
        System.out.println("Enter the coordinates of the "+shipsNames[shipsIterator]+ " (" + shipsSizes[shipsIterator] + " cells):");

        while (shipsIterator != amountOfShips) {
            if (battlefield.addBattleship(scanner.next(),scanner.next(),shipsSizes[shipsIterator])) {

                battlefield.printField(false);
                shipsIterator++;
                if (shipsIterator != amountOfShips)
                System.out.println("Enter the coordinates of the "+shipsNames[shipsIterator]+ " (" + shipsSizes[shipsIterator] + " cells):");
            }
        }
        System.out.println("The game starts!");
        battlefield.printField(true);
        System.out.println("Take a shot!");
        battlefield.takeShot();

    }
}
