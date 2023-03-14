package Classes;

import java.util.Scanner;

public class Menu{

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
        

        switch(opcao) { // trata as opcoes
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

    public Jogador lerDados() throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("\nDigite o nome do jogador");
        String knownAs = sc.nextLine();

        System.out.println("\nDigite o nome compledo do jogador");
        String fullName = sc.nextLine();

        System.out.println("\nDigite o Overall do jogador");
        byte overall = sc.nextByte();

        System.out.println("\nDigite o valor do jogador:");
        long value = sc.nextLong();

        clearBuffer(sc);

        System.out.println("\nDigite a posição do jogador");
        String bestPosition = sc.nextLine();

        System.out.println("\nDigite a nacionalidade do jogador");
        String nacionality = sc.nextLine();

        System.out.println("\nDigite a idade do jogador");
        byte age = sc.nextByte();

        clearBuffer(sc);

        System.out.println("\nDigite o clube do jogador");
        String clubeName = sc.nextLine();

        Jogador jogador;
        jogador = new Jogador(knownAs, fullName, overall, value, bestPosition, nacionality, age, clubeName);

        sc.close();
        return jogador;
    }
        
}
