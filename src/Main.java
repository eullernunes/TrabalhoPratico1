import Classes.*;

public class Main {
    public static void main(String[] args) {
        
        Arquivo arquivo;
        Csv arquivoCsv = new Csv();

        try{
            arquivo = new Arquivo("Jogadores");

            arquivoCsv.lendoArquivo();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


