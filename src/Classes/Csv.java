package Classes;

import java.io.*;

public class Csv {

    static String arquivo = "../csv/Fifa 23 Players Data.csv";

    public void lendoArquivo() throws Exception{

        FileReader fileReader = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fileReader);
        String linha = "";
        int id = 0;

        br.readLine(); // Ignora a primeira linha do csv
        linha = br.readLine();

        while(linha != null){
            id++;
            String[] array = linha.split(",");

            String knowAs = array[0];
            String fullName = array[1];
            Byte overall = Byte.parseByte(array[2]);
            double value = Double.parseDouble(array[3]);
            String bestPosition = array[4];
            String nacionality = array[5];
            Byte age = Byte.parseByte(array[6]);
            String clubName = array[7];     
            
            Jogador novoJogador = new Jogador(id, knowAs, fullName, overall, value, bestPosition, nacionality, age, clubName);

            linha = br.readLine();
        }

        br.close();
        fileReader.close();
    }

}
