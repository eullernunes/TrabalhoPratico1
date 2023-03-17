package Classes;

import java.io.*;

public class Csv {

    static String path = "csv/Fifa 23 Players Data.csv";

    public void lendoArquivo() throws Exception{

        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        String linha = "";

        br.readLine(); // Ignora a primeira linha do csv
        linha = br.readLine();

        while(linha != null){
            Jogador novoJogador = new Jogador();
            String[] array = linha.split(",");


            String knowAs = array[0];
            novoJogador.setKnownAs(knowAs);

            String fullName = array[1];
            novoJogador.setFullName(fullName);

            Byte overall = Byte.parseByte(array[2]);
            novoJogador.setOverall(overall);

            int value = Integer.parseInt(array[3]);
            novoJogador.setValue(value);

            String bestPosition = array[4];
            novoJogador.setBestPosition(bestPosition);

            String nacionality = array[5];
            novoJogador.setNacionality(nacionality);

            Byte age = Byte.parseByte(array[6]);
            novoJogador.setAge(age);

            String clubName = array[7];
            novoJogador.setClubName(clubName);
            
            String dateString = array[8];   
            novoJogador.setJoinedOn(dateString);
           
           /*System.out.println("KnowAs: "   + novoJogador.getKnownAs()
            +"\nFullName: "     + novoJogador.getFullName()
            +"\nOverall: "      + novoJogador.getOverall()
            +"\nValue: "        + novoJogador.getValue()
            +"\nBestPosition: " + novoJogador.getBestPosition()
            +"\nNacionality: "  + novoJogador.getNacionality()
            +"\nAge: "          + novoJogador.getAge()
            +"\nClubName: "     + novoJogador.getClubName()
            +"\nJoinedOn: "     + novoJogador.getJoinedOn()
            );
            */
        
            Arquivo arq = new Arquivo();
            arq.create(novoJogador);
           
            linha = br.readLine();
        }

        br.close();
        fileReader.close();
    }

}
