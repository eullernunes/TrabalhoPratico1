package Classes;

import java.io.*;
public class Ordenacao {
    private File arq;
    private RandomAccessFile fileReader;
    private long posicao;
    

    File arq1 = new File("arquivo1.db");
    RandomAccessFile arquivo1 = new RandomAccessFile("arquivo1.db", "rw");

    File arq2 = new File("arquivo2.db");
    RandomAccessFile arquivo2 = new RandomAccessFile("arquivo2.db", "rw");

    File arq3 = new File("arquivo3.db");
    RandomAccessFile arquivo3 = new RandomAccessFile("arquivo3.db", "rw");

    File arq4 = new File("arquivo4.db");
    RandomAccessFile arquivo4 = new RandomAccessFile("arquivo4.db", "rw");

    int total = 0;


    Ordenacao(String arquivo) throws Exception{
        this.arq = new File(arquivo);
        fileReader = new RandomAccessFile("jogadores.db","rw");
    }

    public void intercalacaoBalanceadaComum() throws Exception {
        
    }
}