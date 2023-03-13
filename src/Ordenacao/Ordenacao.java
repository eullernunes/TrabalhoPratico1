package Ordenacao;

import java.io.*;
public class Ordenacao {
    private File arq;
    private RandomAccessFile fileReader;
    

    File arq1 = new File("arquivo1.db");
    RandomAccessFile arquivo1 = new RandomAccessFile("arquivo1.db", "rw");

    File arq2 = new File("arquivo2.db");
    RandomAccessFile arquivo2 = new RandomAccessFile("arquivo2.db", "rw");

    File arq3 = new File("arquivo3.db");
    RandomAccessFile arquivo3 = new RandomAccessFile("arquivo3.db", "rw");

    File arq4 = new File("arquivo4.db");
    RandomAccessFile arquivo4 = new RandomAccessFile("arquivo4.db", "rw");


    Ordenacao(String arquivo) throws FileNotFoundException{
        this.arq = new File(arquivo);
        fileReader = new RandomAccessFile(arquivo,"rw");
    }

    public void intercalacaoBalanceada() throws Exception{
        
    }

}
