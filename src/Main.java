import Classes.*;

public class Main {
    public static void main(String[] args) {
        
        Arquivo arquivo;
        Csv arquivoCsv = new Csv();
        int opcao = 1;

        try{
            arquivo = new Arquivo("Jogadores");

            arquivoCsv.lendoArquivo();

            Menu menu = new Menu();

            menu.exibeMenu();
            

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


