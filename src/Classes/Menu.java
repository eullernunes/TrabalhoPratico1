package Classes;

import java.io.*;
import java.util.Scanner;

public class Menu{

    public void exibeMenu() throws Exception{
        Scanner sc = new Scanner(System.in);
        Arquivo arquivo = new Arquivo();
        int opcao, id = 0;;

        System.out.println("******** Menu Jogadores ********\n");
        System.out.println("Escolha uma opção:");
        System.out.println("1) Criar Jogador");
        System.out.println("2) Pesquisar Jogador");
        System.out.println("3) Alterar Jogador");
        System.out.println("4) Deletar Jogador");
        System.out.println("5) Sair");

        opcao = sc.nextInt();

        switch(opcao) { // trata as opcoes
            case 1:
                arquivo.create();
                break;
            case 2:
                System.out.println("Digite o id que deseja pesquisar: ");
                id = sc.nextInt();

                arquivo.read(id);
                break;
            case 3:
                System.out.println("Digite o id que deseja alterar: ");
                id = sc.nextInt();
                System.out.println("Selecione a opcao que deseja alterar: ");
                System.out.println("1) Nome \n 2) Overall \n 3) Valor \n 4) Posição \n 5) Nacionalidade \n 6) Idade \n 7) Clube");
                byte escolha = sc.nextByte();
                arquivo.update(id, escolha);
                break;

            case 4:
                arquivo.delete(id);
                break;

            case 5:
                System.out.println("Saindo...");
                sc.close();
                break;

            default:
                System.out.println("Digite uma opção válida!!");
        }
    }
        
}
