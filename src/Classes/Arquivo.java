package Classes;

import java.io.*;

public class Arquivo {
   RandomAccessFile fileReader;
    

    public Arquivo(String arquivo)throws Exception{
        fileReader = new RandomAccessFile("jogadores.db", "rw");
        if(fileReader.length() == 0){
            fileReader.writeInt(0);
        }
    }

    public Arquivo(){}

    
    public Jogador read(int id) throws Exception{
        fileReader = new RandomAccessFile("jogadores.db", "rw");

        fileReader.seek(4);
        boolean lapide;
        int tamanho;
        Jogador jogador = new Jogador();
        byte[] ba;
        int idJogador;

        while(fileReader.getFilePointer() < fileReader.length()){
            lapide = fileReader.readBoolean();
            idJogador = fileReader.readInt();
            tamanho = fileReader.readInt();
            if(!lapide && idJogador == id){            
                ba = new byte[tamanho];
                fileReader.read(ba);
                jogador.fromByteArray(ba);
                jogador.toString();
                return jogador;
            
            } else{
                fileReader.skipBytes(tamanho);
            }

        }

        return null;
    }

    /*   
     * Método create
     * Acessa o arquivo na posição do cabeçalho e armazena o ultimo id cadastrado
     * Seta o id no objeto jogador (Ultimo ID + 1)
     * Salva o objeto jogador em um array de bytes
     * Escreve a lápide no arquivo, o id do jogador e o tamanho do jogador
     * Por fim escreve toda a informação do byte array e retorna o ultimo id utilizado
    */

    public int create (Jogador jogador) throws Exception{
        fileReader = new RandomAccessFile("jogadores.db", "rw");
        fileReader.seek(0);
        int ultimoId = fileReader.readInt();
    
        int proximoId = ultimoId + 1;
        fileReader.seek(0);
        fileReader.writeInt(proximoId);

        fileReader.seek(fileReader.length());

        jogador.setId(proximoId);
        byte[] ba = jogador.toByteArray();
        fileReader.writeBoolean(false);
        fileReader.writeInt(proximoId);
        fileReader.writeInt(ba.length);
        fileReader.write(ba);


        return proximoId;
    }


    public Jogador update(Jogador novoJogador) throws Exception{
        fileReader.seek(4);
        boolean lapide;
        long posicaoLapide = 0;
        int tamanho;
        Jogador jogador = new Jogador();
        byte[] ba;
        byte[] baNovo;
        int idJogador;

        while(fileReader.getFilePointer() < fileReader.length()){
            posicaoLapide = fileReader.getFilePointer();
            lapide = fileReader.readBoolean();
            idJogador = fileReader.readInt();
            tamanho = fileReader.readInt();
            if(!lapide && idJogador == novoJogador.getId()){
              
                ba = new byte[tamanho];
                fileReader.read(ba);
                jogador.fromByteArray(ba);

                baNovo = novoJogador.toByteArray();

                if(baNovo.length <= tamanho){
                    fileReader.seek(posicaoLapide + 9);
                    fileReader.write(baNovo);
                }else{
                    fileReader.seek(posicaoLapide);
                    fileReader.writeBoolean(true);
                    fileReader.seek(fileReader.length());
                    fileReader.writeBoolean(false);
                    fileReader.writeInt(idJogador);
                    fileReader.writeInt(baNovo.length);
                    fileReader.write(baNovo);
                }
                return jogador;
                
            } else{
                fileReader.skipBytes(tamanho);
            }
        }

        return null;
    }

    public Jogador delete(int id) throws Exception{
        fileReader = new RandomAccessFile("jogadores.db", "rw");

        fileReader.seek(4);
        boolean lapide;
        long posicaoLapide;
        int tamanho;
        Jogador jogador = new Jogador();
        byte[] ba;
        int idJogador;

        while(fileReader.getFilePointer() < fileReader.length()){
            posicaoLapide = fileReader.getFilePointer();
            lapide = fileReader.readBoolean();
            idJogador = fileReader.readInt();
            tamanho = fileReader.readInt();
            if(!lapide && idJogador == id){
                
                ba = new byte[tamanho];
                fileReader.read(ba);
                jogador.fromByteArray(ba);

                fileReader.seek(posicaoLapide);
                fileReader.writeBoolean(true);
                return jogador;
                
            } else{
                fileReader.skipBytes(tamanho);
            }

        }

        return null;
    }
    
}
