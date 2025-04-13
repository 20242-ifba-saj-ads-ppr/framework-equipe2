package command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import builder.TabletopProduct;
import context.Peca;
import observer.TabletopSubject;
import strategy.LeaoMovimentoStrategy;

public class CommandLogManager {

    // Carrega os comandos do arquivo de log e retorna uma lista com os comandos.
    // Para esse exemplo, recria comandos MoverPecaCommand usando dados simplificados do log.
    public static ArrayList<Command> loadCommands(TabletopProduct board, TabletopSubject subject) {
        ArrayList<Command> comandos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("command.log"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Supondo que a linha esteja no formato:
                // MoverPecaCommand;nomeDaPeca;origemX;origemY;destinoX;destinoY
                String[] parts = linha.split(";");
                if (parts.length == 6 && parts[0].equals("MoverPecaCommand")) {
                    String nomePeca = parts[1];
                    int ox = Integer.parseInt(parts[2]);
                    int oy = Integer.parseInt(parts[3]);
                    int dx = Integer.parseInt(parts[4]);
                    int dy = Integer.parseInt(parts[5]);
                    
                    // Para recriar o comando, precisamos instanciar a peça com a estratégia apropriada.
                    // Aqui assumimos que, para esse exemplo, estamos usando a estratégia do Leão.
                    Peca peca = new Peca(nomePeca, new LeaoMovimentoStrategy());
                    Command cmd = new MoverPecaCommand(peca, board, ox, oy, dx, dy, subject);
                    comandos.add(cmd);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar comandos do log: " + e.getMessage());
        }
        return comandos;
    }
}
