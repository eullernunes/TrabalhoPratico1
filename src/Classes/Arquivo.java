package Classes;

import java.io.*;
import java.util.Scanner;

public class Arquivo {
    private File arquivo;
    private static RandomAccessFile fileReader;
    private static long posicao;
    public static Scanner sc = new Scanner(System.in);
    public static int i = 0;
    

    public Arquivo(String arquivo)throws FileNotFoundException{
        this.arquivo = new File(arquivo);
        fileReader = new RandomAccessFile(arquivo, "rw");
    }

    public Arquivo(){}

    public static void escreverJogador(Jogador jogador)throws IOException{
        byte[] ba = jogador.toByteArray();
        fileReader.seek(0);
        fileReader.writeInt(jogador.getId());

        fileReader.seek(fileReader.length());
        fileReader.writeInt(ba.length);
        fileReader.write(ba);

    }

    public static Jogador leJogador(int tamanhoArquivo, int id, boolean lapide) throws IOException{
        Jogador jogador = new Jogador();

        jogador.setLapide(lapide);
        jogador.setId(id);
        jogador.setKnownAs(fileReader.readUTF());
        jogador.setOverall(fileReader.readByte());
        jogador.setValue(fileReader.readDouble());
        jogador.setBestPosition(fileReader.readUTF());
        jogador.setNacionality(fileReader.readUTF());
        jogador.setAge(fileReader.readByte());
        jogador.setClubName(fileReader.readUTF());

        return jogador;
    }

    public void pesquisa(int id)throws IOException{
        fileReader.seek(0);
        fileReader.readInt();
        int tamanhoJogador;
        boolean lapide;
        int idJogador;

        try{
            while(fileReader.getFilePointer() < fileReader.length()){
                tamanhoJogador = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if(lapide){
                    fileReader.readInt();
                    idJogador = fileReader.readInt();
                    if(idJogador == id){
                        System.out.println(leJogador(tamanhoJogador, id, lapide));
                        break;
                    }else{
                        fileReader.skipBytes(tamanhoJogador - 11);
                    }
                }else{
                    fileReader.skipBytes(tamanhoJogador - 1);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Jogador pesquisa(int id, Jogador jogador) throws IOException{
        fileReader.seek(0);
        fileReader.readInt();
        int tamanhoJogador;
        boolean lapide;
        int idJogador;

        try{
            while(fileReader.getFilePointer() < fileReader.length()){
                posicao = fileReader.getFilePointer();
                tamanhoJogador = fileReader.readInt();
                lapide = fileReader.readBoolean();
                
                if(lapide){
                    fileReader.readInt();
                    idJogador = fileReader.readInt();
                    if(idJogador == id){
                        jogador = leJogador(tamanhoJogador, id, lapide);
                        break;
                    }else {
                        fileReader.skipBytes(tamanhoJogador - 11);
                    }
                } else{
                    fileReader.skipBytes(tamanhoJogador - 1);
                }
            }

        } catch(Exception e){
            System.err.println("Id nao encontrado");
        }

        return jogador;
    }
    

    public void create() throws IOException{ 
        Scanner sc = new Scanner(System.in);
        Jogador jogador = new Jogador();
        
        fileReader.seek(0);
        int ultimoId = Integer.parseInt(fileReader.readUTF());
        ultimoId++;
        jogador.setId(ultimoId);

        System.out.println("Digite o nome do jogador:");
        jogador.setKnownAs(sc.nextLine());

        System.out.println("Digite o Overall do jogador:");
        jogador.setOverall(sc.nextByte());

        System.out.println("Digite o valor do jogador:");
        jogador.setValue(sc.nextDouble());

        System.out.println("Digite a posição do jogador:");
        jogador.setBestPosition(sc.nextLine());

        System.out.println("Digite a nacionalidade do jogador");
        jogador.setNacionality(sc.nextLine());

        System.out.println("Digite a Idade do Jogador");
        jogador.setAge(sc.nextByte());

        System.out.println("Digite o Clube do Jogador");
        jogador.setClubName(sc.nextLine());

        escreverJogador(jogador);

        sc.close();


    }

    	

    public void update(int id, byte opcao) throws IOException{ //metodo para atualizar jogador
        Jogador jogador = new Jogador();
        Scanner sc = new Scanner(System.in);

        jogador = pesquisa(id,jogador);

        if(jogador != null){
            System.out.println("Jogador selecionado:");
            System.out.println(jogador);        
        }

        switch(opcao){
            case 1:
                System.out.println("Digite o novo nome: ");
                jogador.setKnownAs(sc.nextLine());
                break;
            case 2:
                System.out.println("Digite o novo Overall: ");
                jogador.setOverall(sc.nextByte());
                break;
            case 3:
                System.out.println("Digite o novo valor: ");
                jogador.setValue(sc.nextDouble());
                break;
            case 4:
                System.out.println("Digite a nova posição: ");
                jogador.setBestPosition(sc.nextLine());
                break;
            case 5:
                System.out.println("Digite a nova nacionalidade: ");
                jogador.setNacionality(sc.nextLine());
                break;
            case 6:
                System.out.println("Digite a nova idade: ");
                jogador.setAge(sc.nextByte());
                break;
            case 7:
                System.out.println("Digite o novo clube: ");
                jogador.setClubName(sc.nextLine());
                break;
            default:
                System.out.println("A opção escolhida não é válida");
        }

        sc.close();

        byte[] ba = jogador.toByteArray();
        fileReader.seek(posicao);

        int tamanhoJogador = fileReader.readInt();
        if(ba.length <= tamanhoJogador){
            fileReader.write(ba);
        }else{
            fileReader.writeBoolean(false);
            escreverJogador(jogador);
        }

    }

    public void delete(int id) throws IOException{ //metodo para deletar conta
        fileReader.seek(0);
        fileReader.readInt();
        int tamanhoJogador;
        boolean lapide;
        int idJogador;
        long posicaoLapide;

        try{
            while(fileReader.getFilePointer() < fileReader.length()){
                tamanhoJogador = fileReader.readInt();
                posicaoLapide = fileReader.getFilePointer();
                lapide = fileReader.readBoolean();

                if(lapide){
                    fileReader.readInt();
                    idJogador = fileReader.readInt();
                    if(idJogador == id){
                        fileReader.seek(posicaoLapide);
                        fileReader.writeBoolean(false);
                        fileReader.skipBytes(10);
                        System.out.println("Jogador deletado: \n" + leJogador(tamanhoJogador,id,false));
                        break;
                    }else{
                        fileReader.skipBytes(tamanhoJogador - 11);
                    }
                } else{
                    fileReader.skipBytes(tamanhoJogador - 1);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        
    }

}