package Classes;

import java.io.*;
import java.util.Scanner;


public class LerCsv {

    public static int i;

    public static void lerArquivo(Arquivo arquivo){
        File file = new File ("Jogadores.csv");
        Scanner sc = null;

        try{
            sc = new Scanner(file);
            while(sc.hasNextLine()){
                Jogador jogador = new Jogador();
                jogador.read(sc.nextLine())
            }
        }
    }
}
