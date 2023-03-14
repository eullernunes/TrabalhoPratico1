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

    int total = 100;


    Ordenacao(String arquivo) throws FileNotFoundException{
        this.arq = new File(arquivo);
        fileReader = new RandomAccessFile("jogadores.db","rw");
    }

    public void intercalacaoBalanceada() throws Exception{
        int count = 0;
        fileReader.seek(0);
        int ultimoId = fileReader.readInt();

        total = ultimoId;

        Jogador[] array = new Jogador[total/2];

        int elementos = 0;

        while(elementos < total){
            int x = 0;
            while(x < total/2){
                array[x] = new Jogador();
                int tamanhoJogador = fileReader.readInt();
                posicao = fileReader.getFilePointer();
                
            }
        }
        

    }

}
