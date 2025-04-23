package controller;

import abstractfactory.SelvaPieceFactoryImpl;
import factorymethod.SelvaCellCreator;
import flyweight.TabletopFlyweightConcreteCreator;
import facade.GameFacade;
import singleton.TurnManager;
import state.GameState;
import state.NotStartedState;
import context.PlayerSide;
import enums.CellType;
import context.Peca;

import java.util.Scanner;

public class GameController {
    private final GameFacade facade;
    private final TurnManager turnManager;
    private GameState state;
    private boolean gameOver;

    public GameController() {
        this.facade = new GameFacade();
        this.turnManager = TurnManager.getInstance();
        this.state = new NotStartedState();
        this.gameOver = false;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite 'start' para iniciar, 'move ox oy dx dy' para jogar, 'undo', 'replay', 'end' para sair.");
        while (!gameOver) {
            System.out.print("> ");
            String line = in.nextLine().trim();
            dispatch(line);
        }
        in.close();
    }

    private void dispatch(String commandLine) {
        if (commandLine.equalsIgnoreCase("start")) {
            state.start(this);
        } else if (commandLine.startsWith("move")) {
            String[] parts = commandLine.split("\\s+");
            if (parts.length == 5) {
                try {
                    int ox = Integer.parseInt(parts[1]);
                    int oy = Integer.parseInt(parts[2]);
                    int dx = Integer.parseInt(parts[3]);
                    int dy = Integer.parseInt(parts[4]);
                    String pieceName = promptForPieceName(ox, oy);
                    PlayerSide current = turnManager.getCurrentSide();
                    if (!turnManager.isCurrentPlayer(current)) {
                        System.out.println("Não é seu turno!");
                    } else {
                        boolean moved = facade.executeMove(pieceName, current, ox, oy, dx, dy);
                        if (moved) {
                            checkVictory(dx, dy);
                            if (!gameOver) turnManager.switchTurn();
                        } else {
                            System.out.println("Jogada inválida.");
                        }
                        printBoard();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Uso: move ox oy dx dy (valores inteiros)");
                }
            } else {
                System.out.println("Comando 'move' inválido. Use: move ox oy dx dy");
            }
        } else if (commandLine.equalsIgnoreCase("undo")) {
            facade.undo();
            printBoard();
        } else if (commandLine.equalsIgnoreCase("replay")) {
            facade.replay();
            printBoard();
        } else if (commandLine.equalsIgnoreCase("end")) {
            state.end(this);
        } else {
            System.out.println("Comando desconhecido: " + commandLine);
        }
    }

    public void doStart() {
        facade.setupSelva(
            7, 9,
            new SelvaCellCreator(),
            new TabletopFlyweightConcreteCreator(),
            new SelvaPieceFactoryImpl()
        );
        facade.divideBoard();
        System.out.println("=== Jogo Selva iniciado! ===");
        printBoard();
    }

    public void doPlayLoop() {
        // nada aqui, o playLoop é gerido por dispatch em run()
    }

    public void doEnd() {
        System.out.println("=== Fim de Jogo ===");
        gameOver = true;
    }

    public void setState(GameState s) {
        this.state = s;
    }

    private void checkVictory(int dx, int dy) {
        CellType dest = facade.getBoard().getCellType(dx, dy);
        PlayerSide current = turnManager.getCurrentSide();
        if ((dest == CellType.DEN_BLACK && current == PlayerSide.WHITE) ||
            (dest == CellType.DEN_WHITE && current == PlayerSide.BLACK)) {
            System.out.println("*** " + current + " venceu ao capturar a toca! ***");
            state.end(this);
        }
    }

    private void printBoard() {
        var board = facade.getBoard();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Peca p = board.getPieceAt(x, y);
                if (p != null) System.out.print(p.getNome().charAt(0) + " ");
                else switch (board.getCellType(x, y)) {
                    case WATER:    System.out.print("~ "); break;
                    case TRAP_WHITE: case TRAP_BLACK: System.out.print("^ "); break;
                    case DEN_WHITE:  System.out.print("W "); break;
                    case DEN_BLACK:  System.out.print("B "); break;
                    default:        System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private String promptForPieceName(int ox, int oy) {
        Peca p = facade.getBoard().getPieceAt(ox, oy);
        return p != null ? p.getNome() : "";
    }

    public static void main(String[] args) {
        new GameController().run();
    }
}