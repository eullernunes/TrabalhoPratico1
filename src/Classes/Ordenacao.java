package Classes;

import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Ordenacao {

    private RandomAccessFile fileReader;
	static Scanner sc = new Scanner(System.in);


    Ordenacao(String nomeArquivo) throws Exception{
        this.fileReader= new RandomAccessFile(nomeArquivo,"rw"); // abre o arquivo para leitura e escrita
    }

	


	public void intercalacaoBalanceada(int numSegmentos, int numCaminhos) throws Exception{

		boolean lapide;
		int idJogador;
		int tamanhoRegistro;
		long posicaoLapide;
		byte[] ba; 
		int i;
		ArrayList<Jogador> segmento = new ArrayList<Jogador>(numSegmentos);
		ArrayList<RandomAccessFile> fileReaderTemp = new ArrayList<RandomAccessFile>();
		Jogador jogador = new Jogador();

		fileReader.seek(4); // pula a posição do cabeçalho


		while(fileReader.getFilePointer() < fileReader.length()){
			i = 0;
			while(i < numSegmentos){
				posicaoLapide = fileReader.getFilePointer();
				lapide = fileReader.readBoolean();
				idJogador = fileReader.readInt();
				tamanhoRegistro = fileReader.readInt();
				if(!lapide){
					ba = new byte[tamanhoRegistro];
					fileReader.read(ba);
					jogador.setId(idJogador);
					jogador.fromByteArray(ba);
					segmento.add(jogador);
					i++;
				} else{
					fileReader.seek(tamanhoRegistro);
				}
			}

			segmento.sort(Comparator.comparing(Jogador::getId));
			iniciaTemporarios(fileReaderTemp, numCaminhos);
			escreveTemporario(segmento, fileReaderTemp, numCaminhos);	
		}
	}

	private void escreveTemporario(ArrayList<Jogador> segmento, ArrayList<RandomAccessFile> fileReaderTemp, int numCaminhos) throws IOException {
		byte ba[];
		for (int i = 0; i < segmento.size(); i++) {
			ba = segmento.get(i).toByteArray();

			fileReaderTemp.get(i % numCaminhos).seek(0);
			fileReaderTemp.get(i % numCaminhos).writeInt(segmento.get(i).getId());
			fileReaderTemp.get(i % numCaminhos).seek(fileReaderTemp.get(i % numCaminhos).length());

			fileReaderTemp.get(i % numCaminhos).writeBoolean(false);
			fileReaderTemp.get(i % numCaminhos).write
		}
		
	}


	public void iniciaTemporarios(ArrayList<RandomAccessFile> fileReaderTemp, int numCaminhos) throws IOException{
		
		for(int i = 0; i < 2*numCaminhos; i++){
			fileReaderTemp.add(new RandomAccessFile("jogadoresTemp" + i + ".db", "rw"));
			fileReaderTemp.get(i).seek(0);
			fileReaderTemp.get(i).writeInt(-1);
		}
	}

	public void quicksort(Jogador[] array){
		quicksort(array, 0, array.length - 1);
	}

	private void quicksort(Jogador[] array, int inicio, int fim){
		if(fim > inicio){
			int pivo = dividir(array, inicio, fim);
			quicksort(array, inicio, pivo - 1);
			quicksort(array, pivo + 1, fim);
		}
	}

	private int dividir(Jogador[] array, int inicio, int fim){
		int pivo;
		int esq, dir = fim;
		esq = inicio + 1;
		pivo = array[inicio].getId();

		while(esq <= dir){
			while(esq <= dir && array[esq].getId() <= pivo){
				esq++;
			}

			while(dir >= esq && array[dir].getId() >= pivo){
				dir--;
			}

			if(esq < dir){
				swap(array, dir , esq);
				esq++;
				dir--;
			}
		}

		swap(array, inicio, dir);

		return dir;
	}

	private void swap(Jogador[] array, int i , int j){
		Jogador temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}


}