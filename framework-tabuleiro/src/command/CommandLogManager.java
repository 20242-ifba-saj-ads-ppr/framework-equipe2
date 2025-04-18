package command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import abstractfactory.SelvaPieceFactory;
import abstractfactory.SelvaPieceFactoryImpl;
import builder.TabletopProduct;
import context.Peca;
import context.PlayerSide;
import observer.TabletopSubject;


public class CommandLogManager {

    /**
     * Carrega os comandos do log, reconstrói cada MoverPecaCommand com
     * a fábrica de peças (AbstractFactory) e retorna a lista.
     */
    public static ArrayList<Command> loadCommands(
            TabletopProduct board,
            TabletopSubject subject) throws IOException {

        ArrayList<Command> comandos = new ArrayList<>();
        SelvaPieceFactory pieceFactory = new SelvaPieceFactoryImpl();

        try (BufferedReader reader = new BufferedReader(new FileReader("command.log"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Formato agora:
                // MoverPecaCommand;nomePeca;side;origemX;origemY;destinoX;destinoY
                String[] parts = linha.split(";");
                if (parts.length == 7 && "MoverPecaCommand".equals(parts[0])) {
                    String nomePeca = parts[1];
                    PlayerSide side = PlayerSide.valueOf(parts[2]);
                    int ox = Integer.parseInt(parts[3]);
                    int oy = Integer.parseInt(parts[4]);
                    int dx = Integer.parseInt(parts[5]);
                    int dy = Integer.parseInt(parts[6]);

                    // usa a AbstractFactory para criar a peça
                    Peca peca = pieceFactory.create(nomePeca, side);

                    Command cmd = new MoverPecaCommand(peca, board, ox, oy, dx, dy, subject);
                    comandos.add(cmd);
                }
            }
        }
        return comandos;
    }
}
