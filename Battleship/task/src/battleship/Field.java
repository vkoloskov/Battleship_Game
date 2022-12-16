package battleship;

import java.util.Scanner;

public class Field {
    private int fieldSize = 11;
    private String[][] fieldArea;
    Field() {
        this.fieldArea = new String[this.fieldSize][this.fieldSize];
        setDefaultField();
    }

    void setDefaultField() {
        int firstRow = 1;
        char firstCol = 'A';
        for (int i = 0; i < this.fieldSize; i++) {
            for (int j = 0; j < this.fieldSize; j++) {
                if( i == 0 & j == 0 ) {
                    fieldArea[i][j] = " ";
                } else if ( i == 0 ) {
                    fieldArea[i][j] = String.valueOf(firstRow++);
                } else if ( j == 0 ) {
                    fieldArea[i][j] = String.valueOf(firstCol++);
                } else {
                    fieldArea[i][j] = "~";
                }
            }
        }
    }

    void printField(boolean isFogy) {
        for (int i = 0; i < this.fieldSize; i++) {
            for (int j = 0; j < this.fieldSize; j++) {
                if (isFogy && "O".equals(fieldArea[i][j])) {
                    System.out.print("~ ");
                } else {
                    System.out.print(fieldArea[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    private String checkEdges (int cord1, int cord2, int cordXY, boolean isVertical) {
        if (cord1 > cord2) {
            int tmp = cord1;
            cord1 = cord2;
            cord2 = tmp;
        }
        for (int i = cord1; i <= cord2; i++) {
            if ( isVertical ) {
                if (i + 1 > fieldSize - 1) {
                    if (fieldArea[cordXY][i - 1].equals("O") ||
                            fieldArea[cordXY - 1][i].equals("O") ||
                            fieldArea[cordXY - 1][i - 1].equals("O")
                    ) {
                        return "Error! You placed it too close to another one. Try again:";
                    }
                } else if (cordXY + 1 > fieldSize - 1) {
                    if (fieldArea[cordXY][i - 1].equals("O") ||
                            fieldArea[cordXY - 1][i].equals("O") ||
                            fieldArea[cordXY - 1][i - 1].equals("O")
                    ) {
                        return "Error! You placed it too close to another one. Try again:";
                    }
                } else if (fieldArea[cordXY][i - 1].equals("O") ||
                        fieldArea[cordXY][i + 1].equals("O") ||
                        fieldArea[cordXY - 1][i].equals("O") ||
                        fieldArea[cordXY + 1][i].equals("O") ||
                        fieldArea[cordXY - 1][i - 1].equals("O") ||
                        fieldArea[cordXY + 1][i - 1].equals("O") ||
                        fieldArea[cordXY - 1][i + 1].equals("O") ||
                        fieldArea[cordXY + 1][i + 1].equals("O")
                ) {
                    return "Error! You placed it too close to another one. Try again:";
                }
            } else {
                if (i + 1 > fieldSize - 1) {
                    if (fieldArea[i - 1][cordXY].equals("O") ||
                            fieldArea[i][cordXY - 1].equals("O") ||
                            fieldArea[i - 1][cordXY - 1].equals("O")
                    ) {
                        return "Error! You placed it too close to another one. Try again:";
                    }
                } else if (cordXY + 1 > fieldSize - 1) {
                    if (fieldArea[i - 1][cordXY].equals("O") ||
                            fieldArea[i][cordXY - 1].equals("O") ||
                            fieldArea[i - 1][cordXY - 1].equals("O")
                    ) {
                        return "Error! You placed it too close to another one. Try again:";
                    }
                } else if (fieldArea[i - 1][cordXY].equals("O") ||
                        fieldArea[i + 1][cordXY].equals("O") ||
                        fieldArea[i][cordXY - 1].equals("O") ||
                        fieldArea[i][cordXY + 1].equals("O") ||
                        fieldArea[i - 1][cordXY - 1].equals("O") ||
                        fieldArea[i - 1][cordXY + 1].equals("O") ||
                        fieldArea[i + 1][cordXY - 1].equals("O") ||
                        fieldArea[i + 1][cordXY + 1].equals("O")
                ) {
                    return "Error! You placed it too close to another one. Try again:";
                }
            }
        }
        return "";
    }
    private void addShip(int cord1, int cord2, int cordXY, boolean isVertical) {
        if (cord1 > cord2) {
            int tmp = cord1;
            cord1 = cord2;
            cord2 = tmp;
        }
            for (int i = cord1; i <= cord2; i++) {
                if (isVertical) {
                    fieldArea[cordXY][i] = "O";
                } else {
                    fieldArea[i][cordXY] = "O";
                }
            }
    }
    private String getErrors(int vertical1, int horizontal1, int vertical2, int horizontal2, int size) {
        if (size != Math.abs(horizontal1 - horizontal2) + 1 && size != Math.abs(vertical1 - vertical2) + 1) {

            return "Error! Wrong length of the Submarine! Try again:";

        } else if (vertical1 == vertical2) {

            return checkEdges(horizontal1,horizontal2,vertical1, true);

        } else if (horizontal1 == horizontal2) {

            return checkEdges(vertical1,vertical2,horizontal1, false);

        } else {

            return "Error! Wrong ship location! Try again:";
        }
    }

    private int[] parseCoordinate(String coordinate) {
        int firstCoord = coordinate.charAt(0) - 64;
        int secondCoord = Integer.parseInt(coordinate.substring(1));
        return new int[] {firstCoord < 11 ? firstCoord : 0, secondCoord < 11 ? secondCoord : 0};
    }
    boolean addBattleship(String firstCoordinate, String secondCoordinate, int size) {
        int vertical1 = firstCoordinate.charAt(0) - 64; //65 'A'
        int horizontal1 = Integer.parseInt(firstCoordinate.substring(1));
        int vertical2 = secondCoordinate.charAt(0) - 64;
        int horizontal2 = Integer.parseInt(secondCoordinate.substring(1));
        //int[] coordinate1 = parseCoordinate(firstCoordinate);
        //int[] coordinate2 = parseCoordinate(secondCoordinate);

        String error = getErrors(vertical1, horizontal1, vertical2, horizontal2, size);

        if ("".equals(error)) {
            if (vertical1 == vertical2) {

                addShip(horizontal1,horizontal2,vertical1, true);
                return true;

            } else if (horizontal1 == horizontal2) {

                addShip(vertical1,vertical2,horizontal1, false);
                return true;

            }
        }
        System.out.println(error);
        return false;
    }

    void takeShot() {
        Scanner scanner = new Scanner(System.in);
        int[] coordinate = parseCoordinate(scanner.next());
        if ("~".equals(fieldArea[coordinate[0]][coordinate[1]])) {
            fieldArea[coordinate[0]][coordinate[1]] = "M";
            printField(true);
            System.out.println("You missed!");
            printField(false);
        } else if ("O".equals(fieldArea[coordinate[0]][coordinate[1]])) {
            fieldArea[coordinate[0]][coordinate[1]] = "X";
            printField(true);
            System.out.println("You hit a ship!");
            printField(false);
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            takeShot();
        }
    }
}
