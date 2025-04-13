package command;

import context.Peca;
import builder.TabletopProduct;
import observer.TabletopSubject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MoverPecaCommand implements Command {

    private Peca peca;
    private TabletopProduct board;
    private int origemX, origemY;
    private int destinoX, destinoY;
    private TabletopSubject subject;
    
    public MoverPecaCommand(Peca peca, TabletopProduct board,
                            int origemX, int origemY,
                            int destinoX, int destinoY,
                            TabletopSubject subject) {
        this.peca = peca;
        this.board = board;
        this.origemX = origemX;
        this.origemY = origemY;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.subject = subject;
    }
    
    @Override
    public boolean execute() {
        System.out.println("Executando comando para mover a peça " + peca.getNome());
        boolean resultado = peca.mover(board, origemX, origemY, destinoX, destinoY, subject);
        if(resultado) {
            store();
        }
        return resultado;
    }
    
    // Serializa o comando em uma string simples. Essa representação pode ser usada para recarregar o comando.
    @Override
    public String serialize() {
        // Para efeitos do exemplo, salvamos os dados básicos do comando.
        return "MoverPecaCommand;" + peca.getNome() + ";" + origemX + ";" + origemY + ";" + destinoX + ";" + destinoY;
    }
    
    // Armazena o comando em um arquivo de log (append mode).
    @Override
    public void store() {
        try (PrintWriter out = new PrintWriter(new FileWriter("command.log", true))) {
            out.println(serialize());
            System.out.println("Comando armazenado: " + serialize());
        } catch(IOException e) {
            System.err.println("Erro ao armazenar comando: " + e.getMessage());
        }
    }
}
