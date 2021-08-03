/*
* Creating when new game stars
* */

package Interface;

import Board.Board;
import Figures.Figure;
import Players.Player;
import Exceptions.IllegalMove;

import java.util.Map;
import java.util.Scanner;

public class Interface {
    private final Player white = new Player(false);
    private final Player black = new Player(true);
    private boolean isGame; //for checking if game is over
    //to compare coordinate with matrix
    private final Map<Character, Integer> linesCoordinate = Map.of('1', 7,
            '2', 6,
            '3', 5,
            '4', 4,
            '5', 3,
            '6', 2,
            '7', 1,
            '8', 0);
    private final Map<Character, Integer> columnsCoordinate = Map.of('h', 7,
            'g', 6,
            'f', 5,
            'e', 4,
            'd', 3,
            'c', 2,
            'b', 1,
            'a', 0);

    public Interface() {
        isGame = true;
        Board.startNewGame();
        this.turn(white);
    }
    /**
     * Analyze input player
     * Let him choose figure and move it
     * If opposite player king is dead current player win
     * If not give turn to another player
     * If input "stop" instead of coordinates game will finished immediately
     * **/
    public void turn(Player player) {
        while (isGame) {
            Scanner scan = new Scanner(System.in);
            Board.showBoard();
            System.out.println(playerColor(player) + " player turn");
            System.out.println("Choose figure to move (enter coordinates): ");
            System.out.println("*you should enter coordinate like this: a1");
            String coordinate = scan.nextLine();
            if (coordinate.equals("stop")) {
                isGame = false;
                continue;
            }
            char[] coordinates = coordinate.toCharArray();
            try {
                checkCoordinates(coordinates);
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }
            Figure chosenFigure;
            try {
                chosenFigure = Board.chooseFigure(linesCoordinate.get(coordinates[1]),
                        columnsCoordinate.get(coordinates[0]), player);
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Where should it move? (enter coordinates)");
            coordinate = scan.nextLine();
            if (coordinate.equals("stop")) {
                isGame = false;
                continue;
            }
            coordinates = coordinate.toCharArray();
            try {
                checkCoordinates(coordinates);
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }
            try {
                if(Board.moveFigure(chosenFigure, linesCoordinate.get(coordinates[1]),
                        columnsCoordinate.get(coordinates[0]))) {
                    isGame = false;
                    System.out.println(playerColor(player) + " win!");
                    Board.showBoard();
                    System.out.println("Congratulations!");
                }
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }
            if (player.getColor()) turn(white);
            else turn(black);
        }
    }

    /**
     * throw exception if input is wrong
     * **/
    private void checkCoordinates(char[] coordinates) {
        if (coordinates.length != 2) throw new IllegalMove();
        if (!columnsCoordinate.containsKey(coordinates[0])) throw new IllegalMove();
        if (!linesCoordinate.containsKey(coordinates[1])) throw new IllegalMove();
    }

    /**
     * return string meaning of player color
     * **/
    private String playerColor(Player player){
        if (player.getColor()) return "Black";
        else return "White";
    }

    /**
     * used im main menu to give players a tip about game
     * **/
    public static void description(){
        System.out.println("""
                This version of chess is only for two players(or you can play with yourself;))
                Black is on top, white is down
                There is no interception in this version
                Condition to win - beat opponent king
                There is no check of ... check, so look for it by yourself, or you will lose
                If you want to stop game immediately write "stop" instead of coordinates
                Good luck!""");
        System.out.println();
    }
}
