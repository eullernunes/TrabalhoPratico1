package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Ordenacao {
    private File arq;
    private RandomAccessFile fileReader;
    private long posicao;


    Ordenacao(String arquivo) throws Exception{
        this.arq = new File(arquivo);
        fileReader = new RandomAccessFile("jogadores.db","rw");
    }

    public void intercalacaoBalanceadaComum() throws Exception {
        try {
            int m = 10 , n = 2;
            int ultimoId;
            int idAtual = 0;
            int tamRegAtual;
            long pos0, pos1, posBucketNovo;
            Jogador jogadorTemp;
            int arquivoFinal = 0;

            ArrayList<RandomAccessFile> arqTemp = new ArrayList<RandomAccessFile>();
            List<Jogador> memoria = new ArrayList<Jogador>(m);
            int [] ultimoId2 = new int[2*n];
            int [] ultimoSalvo = new int [2*n];

            arq.seek(comeco);
            ultimoId = arq.readInt();
            
            for(int i=0; i<2*n; i++) { // inicia os RandomAccesFiles dos arquivos temporarios
				arqTemp.add(new RandomAccessFile("arqTemp" + i + ".db", "rw"));
				arqTemp.get(i).writeInt(-1); // escreve -1 como sendo a ultima id
				ultimoId2[i] = -1;
			}

            arq.seek(comeco);
			ultimoId = arq.readInt();
			idAtual = 0;

            for(int i=0; idAtual != ultimoId; i++){ // faz a distribuicao
				// carrega a memoria com os dados
				while(memoria.size()<m && idAtual != ultimoId) { // carrega os m registros na memoria
					tamRegAtual = arq.readInt();
					pos1 = arq.getFilePointer();
					if(arq.readByte()!=1) {
						arq.seek(pos1);
						jogadortemp = leRegistro(arq, comeco, pos1);
						memoria.add(jogadortemp);
						idAtual = jogadortemp.getId();
					} else {
						arq.seek(pos1);
						arq.skipBytes(tamRegAtual);
					}
				}
            
            memoria.sort(Comparator.comparing(Jogador::getId)); // ordena a memoria

            if(arqTemp.get(i%n).length() == 0) { // se o arquivo for vazio, escreve -1 como a ultima id
                arqTemp.get(i%n).seek(comeco);
                arqTemp.get(i%n).writeInt(-1);
                posBucketNovo = arqTemp.get(i%n).getFilePointer(); // salva a posicao atual
                //System.out.println("arquivo vazio"); // teste
            } else {
                posBucketNovo = arqTemp.get(i%n).getFilePointer(); // salva a posicao atual
                arqTemp.get(i%n).seek(comeco); // navega ate o comeco do arquivo temporario para gravar qual foi a ultima id
                arqTemp.get(i%n).writeInt(memoria.get(memoria.size()-1).getId()); // salva qual eh a ultima id na memoria
                arqTemp.get(i%n).seek(posBucketNovo); // navega ate a posicao do comeco do bloco atual
            }
            for(Jogador jogadorTemp : memoria) { // grava os registros da memoria no arquivo temporario
                //System.out.println("escreveu registro " + jogadorTemp.getId()); // teste
                arqTemp.get(i%n).seek(comeco);
                arqTemp.get(i%n).writeInt(jogadorTemp.getId()); // escreve a ultima id
                ultimoId2[i%n] = jogadorTemp.getId();
                arqTemp.get(i%n).seek(posBucketNovo);
                escreveRegistro(arqTemp.get(i%n), posBucketNovo, jogadorTemp);
                posBucketNovo = arqTemp.get(i%n).getFilePointer();
            }
            
            
            memoria.clear(); // limpa a memoria

            int[] posBlocoAtual = new int [n]; // tamanho do bloco
			int tamBlocoOrdenado = m;
			int saiuDaFita = 0;
			boolean[] blocoAcabou = new boolean[n];
			Jogador[] memoria2 = new Jogador[n];
			int fitaAtual;
			int ordem = 0;
			long[] pos = new long[n*2];
			int menor;
			int indiceMenor;
			int fitaDeSaida = n;
			boolean todosAcabaram = false;
			boolean todosRegistrosLidos;
			int naoOrdem;
			boolean temMaisDeUmComDados = true;
			int arqComDados = n;

            // zera o ultimoSalvo, posBlocoAtual e pos e volta o ponteiro para o comeco dos arquivos temporarios
			for(int i=0; i<n*2; i++) {
				ultimoSalvo[i] = -1;
				arqTemp.get(i).seek(comeco);
				ultimoId2[i] = arqTemp.get(i).readInt(); // salva a ultima id 
				pos[i] = arqTemp.get(i).getFilePointer();
			}
			for(int i=0; i<n; i++) {
				posBlocoAtual[i] = 0;
			}

            do {
				
				// reseta os ponteiros para o comeco do arquivo
				for(int i=0; i<n*2; i++) {
					arqTemp.get(i).seek(comeco);
					arqTemp.get(i).readInt();
					pos[i] = arqTemp.get(i).getFilePointer();
				}
				
				// do {
				do {
					
					// enquanto bloco nao acabou
					while(!todosAcabaram) {
						
						// para cada arquivo temporario do lado atual
						for(int i=0; i<n; i++) {
							
							// se for a primeira passada ou for o que saiuDaFita
							fitaAtual = (ordem*n) + i;
							if(posBlocoAtual[fitaAtual%n] == 0 || fitaAtual == saiuDaFita) {
								
								// se a posicao do bloco atual for < tamanho do bloco ordenado
								if(posBlocoAtual[fitaAtual%n] < tamBlocoOrdenado) {
									
									// se nao tiver acabado o arquivo
									if(ultimoSalvo[fitaAtual] != ultimaId2[fitaAtual]) {
										
										// le o registro atual
										//System.out.print("tentou ler registro na fita " + fitaAtual + " na posição " + pos[fitaAtual] + "\nfita " + fitaAtual + ": "); // teste
										//imprimeArquivo(arqTemp.get(fitaAtual), comeco); // teste
										//System.out.println("ultimoSalvo[" + fitaAtual + "] = " + ultimoSalvo[fitaAtual] + " ultimaId2[" + fitaAtual + "] = " + ultimaId2[fitaAtual] + " posBlocoAtual = " + posBlocoAtual[fitaAtual%n]); // teste
										arqTemp.get(fitaAtual).seek(pos[fitaAtual]);
										tamRegAtual = arqTemp.get(fitaAtual).readInt();
										memoria2[i] = leRegistro(arqTemp.get(fitaAtual), comeco, pos[fitaAtual]);
										pos[fitaAtual] = arqTemp.get(fitaAtual).getFilePointer();
										// ultimoSalvo[fitaAtual] = memoria2[i].getId();
										//System.out.println("leu registro " + memoria2[i].getId() + " posBlocoAtual[" + fitaAtual%n + "] calculado = " + (posBlocoAtual[fitaAtual%n] + 1)); // teste
										
										// aumenta a posBlocoAtual
										posBlocoAtual[fitaAtual%n]++;
										//System.out.println("depois da leitura posBlocoAtual[" + fitaAtual%n + "] = " + posBlocoAtual[fitaAtual%n]); // teste
									}
									// se tiver acabado o arquivo
									else {
										
										// salva o blocoAcabou
										blocoAcabou[fitaAtual%n] = true;
									}
								}
								// se a posicao do bloco atual for >= tamanho do bloco ordenado
								else {
									
									// salva o blocoAcabou
									blocoAcabou[fitaAtual%n] = true;
								}
							}
						}
                        menor = 2147483647;
						indiceMenor = 2147483647;
						for(int i=0; i<n; i++) {
							if(memoria2[i] != null) {
								if(memoria2[i].getId() < menor) {
									menor = memoria2[i].getId();
									indiceMenor = i;
								}
							}
						}
                        if(indiceMenor != 2147483647) { 
							
							// salva o saiuDaFita
							saiuDaFita = indiceMenor + (ordem*n);
							int fitaQueFoiLida = (ordem*n) + indiceMenor; 
							
							//System.out.println("Tentou escrever registro " + menor + " de indice " + indiceMenor + " no arquivo " + fitaDeSaida); // teste
							// salva o registro no arquivo de saida
							arqTemp.get(fitaDeSaida).seek(comeco);
							arqTemp.get(fitaDeSaida).writeInt(menor);
							escreveRegistro(arqTemp.get(fitaDeSaida), pos[fitaDeSaida], memoria2[indiceMenor]);
							pos[fitaDeSaida] = arqTemp.get(fitaDeSaida).getFilePointer();
							//pos[saiuDaFita] = arqTemp.get(saiuDaFita).getFilePointer();
							
							// salva o ultimoSalvo
							ultimaId2[fitaDeSaida] = memoria2[indiceMenor].getId();
							ultimoSalvo[fitaQueFoiLida] = memoria2[indiceMenor].getId();
							//System.out.println("escreveu registro " + menor + " de indice " + indiceMenor + " no arquivo " + fitaDeSaida + ", ultimoSalvo[" + fitaQueFoiLida + "] = " + ultimoSalvo[fitaQueFoiLida]); // teste
							
							// remove o registro da memoria
							memoria2[indiceMenor] = null;
						} else {
							//System.out.println("não escreveu nenhum registro"); // teste
						}
						
						// checa se todos os blocos acabaram
						todosAcabaram = true;
						for(int i=0; i<n; i++) {
							if(!blocoAcabou[i]) {
								todosAcabaram = false;
							}
						}
						
						//System.out.println("fita de saida " + fitaDeSaida + ":"); // teste
						//imprimeArquivo(arqTemp.get(fitaDeSaida), comeco); // teste
						
					}
					
					// volta as posBlocoAtual para 0
					for(int i=0; i<n; i++) {
						posBlocoAtual[i] = 0;
						blocoAcabou[i] = false;
						//System.out.println("zerou posBloco["+i+"]"); // teste
					}
					
					// reseta os blocos
					todosAcabaram = false;
					
					// troca o arquivo de saida
					//System.out.print("fita de saida antes: " + fitaDeSaida); // teste
					if (ordem == 0) {
						naoOrdem = 1;
					} else {
						naoOrdem = 0;
					}
					fitaDeSaida = (naoOrdem*n) + ((fitaDeSaida+1)%n);
					//System.out.println(" fita de saida depois: " + fitaDeSaida); // teste
					
					// checa se todos os registros foram lidos
					todosRegistrosLidos = true;
					for(int i=0; i<n; i++) {
						if(ultimoSalvo[(ordem*n) + i] != ultimaId2[(ordem*n) + i]) {
							todosRegistrosLidos = false;
						}
						//System.out.println("final do while todosRegistrosLidos ultimoSalvo[" + ((ordem*n) + i) + "] = " + ultimoSalvo[(ordem*n) + i] + " ultimaId2[" + ((ordem*n) + i) + "] = " + ultimaId2[(ordem*n) + i]); // teste
						//sc.nextLine();
					}
					
				// } repete enquanto todos ultimoSalvo forem diferentes do ultimaId2 para os n arquivos atuais
				} while (!todosRegistrosLidos);
				
				// dobra o tamBlocoOrdenado
				tamBlocoOrdenado *= 2;
				
				// troca os arquivos atuais
				if(ordem == 0) {
					ordem = 1;
					naoOrdem = 0;
				} else {
					ordem = 0;
					naoOrdem = 1;
				}
				
				// troca a fitaDeSaida
				//System.out.print("troca grupo de fitas. fita de saida antes: " + fitaDeSaida); // teste
				fitaDeSaida = naoOrdem*n;
				//System.out.println(" fita de saida depois: " + fitaDeSaida); // teste
				
				// limpa os novos arquivos de saida
				for(int i=0; i<n; i++) {
					int arquivoAtual = (naoOrdem*n) + i; 
					arqTemp.get(arquivoAtual).setLength(0);
					arqTemp.get(arquivoAtual).writeInt(-1);
					pos[arquivoAtual] = arqTemp.get(arquivoAtual).getFilePointer();
					//System.out.println("limpou o ultimoSalvo do arquivo " + ((ordem*n) + i)); // teste
					ultimoSalvo[(ordem*n) + i] = -1; // ultimo salvo do arquivo de entrada
					ultimaId2[arquivoAtual] = -1;
				}
				
				// checa se tem mais de um com dados
				temMaisDeUmComDados = false;
				int numComDados = 0;
				for(int i=0; i<n; i++) {
					int arquivoAtual = (ordem*n) + i;
					
					if(arqTemp.get(arquivoAtual).length() > 4) {
						numComDados++;
						arqComDados = arquivoAtual;
						arquivoFinal = arquivoAtual;
					}
					// sc.nextLine(); // teste
					
					// reseta os ponteiros para o comeco do arquivo
					arqTemp.get(arquivoAtual).seek(comeco);
					arqTemp.get(arquivoAtual).readInt();
					pos[arquivoAtual] = arqTemp.get(arquivoAtual).getFilePointer();
				}
				if(numComDados > 1) {
					temMaisDeUmComDados = true;
				} 
				
			// } repete enquanto tiver mais de um arquivo de saida com dados
			} while (temMaisDeUmComDados);
			//System.out.println("arquivo final com dados: "); // teste
			//imprimeArquivo(arqTemp.get(arqComDados), comeco); // teste
			
			// escreve sobre o arquivo de dados
			copiaArquivo(arqTemp.get(arquivoFinal), comeco, arq);
			System.out.println("\nArquivo após a ordenação:");
			imprimeArquivo(arq, comeco);
			System.out.println("\nAperte enter para continuar.");
			sc.nextLine();


        } catch (Exception e) {
            
        }
        
    }
}