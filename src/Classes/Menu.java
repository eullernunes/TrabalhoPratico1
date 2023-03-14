package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JApplet;

public class Menu{

    /*
     * Exibe menu com opções para o usuário escolher
    */

    public void exibeMenu() throws Exception{
        Scanner sc = new Scanner(System.in);
        Arquivo arquivo = new Arquivo();
        int opcao = 0;
        int id  = 0;

        System.out.println("******** Menu Jogadores ********\n");
        System.out.println("Escolha uma opção:");
        System.out.println("1) Criar Jogador");
        System.out.println("2) Ler Jogador");
        System.out.println("3) Alterar Jogador");
        System.out.println("4) Deletar Jogador");
        System.out.println("5) Sair");

        opcao = sc.nextInt();
        

        /*
         * Trata as opções
        */
        switch(opcao) {        
            case 1:
                Jogador createJogador = lerDados(); 
                arquivo.create(createJogador);
                break;
            case 2:
                System.out.println("Digite o id que deseja pesquisar: ");
                id = sc.nextInt();
                System.out.println(id);

                try{
                    Jogador jogadorProcurado = arquivo.read(id);
                    if(jogadorProcurado == null){
                        System.out.println("Jogador não encontrado!");
                    }else{
                        System.out.println(jogadorProcurado.toString());
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Digite o id que deseja alterar: ");
                id = sc.nextInt();
                System.out.println("\nInsira os atributos do jogador: ");
                Jogador jogador = lerDados();

                try{
                    Jogador novoJogador = arquivo.update(jogador);
                    System.out.println("\n" +novoJogador.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case 4:
                System.out.println("\nDigite o id do jogador que deseja deletar: ");
                id = sc.nextInt();

                try{
                    Jogador jogadorDeletado = arquivo.delete(id);
                    if(jogadorDeletado == null){
                        System.out.println("Jogador não encontrado!");
                    }else{
                        System.out.println(jogadorDeletado.toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case 5:
                System.out.println("Saindo...");
                sc.close();
                break;

            default:
                System.out.println("Digite uma opção válida!!");
        }
    }

    public static void clearBuffer(Scanner scanner){
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }
    }

    /*
     * Cria o objeto jogador de acordo com a entrada do usuário
    */

    public Jogador lerDados() throws Exception{
        Scanner sc = new Scanner(System.in);
        Jogador jogador = new Jogador();

        System.out.println("\nDigite o nome do jogador");
        jogador.setKnownAs(sc.nextLine());

        System.out.println("\nDigite o nome compledo do jogador");
        jogador.setFullName(sc.nextLine());

        System.out.println("\nDigite o Overall do jogador");
        jogador.setOverall(sc.nextByte());

        System.out.println("\nDigite o valor do jogador:");
        jogador.setValue(sc.nextDouble());

        clearBuffer(sc);

        System.out.println("\nDigite a posição do jogador");
        jogador.setBestPosition(sc.nextLine());

        System.out.println("\nDigite a nacionalidade do jogador");
        jogador.setNacionality(sc.nextLine());

        System.out.println("\nDigite a idade do jogador");
        jogador.setAge(sc.nextByte());

        clearBuffer(sc);

        System.out.println("\nDigite o clube do jogador");
        jogador.setClubName(sc.nextLine());

        System.out.println("\nEntre com a data de ingresso do jogador");
        jogador.setJoinedOn(sc.nextLine());

        sc.close();
        return jogador;
    }
        
}
