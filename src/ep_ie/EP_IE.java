/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep_ie;

import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.E;
import java.util.ArrayList;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author gabri
 */
abstract public class EP_IE {

    static final int QTD_AMOSTRAS = 100000;
    static final int QTD_PROVAS = 100000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        parteUm();
        parteDois();

    }

    //Calcula a nota do aluno para determinada prova
    public static double calculaNota(Aluno aluno, Prova prova) {
        double nota = 0;
        double probAcerto;
        for (int i = 0; i < prova.questoes.size(); i++) { //Pega uma amostra de nota para cada questão da prova
            probAcerto = calculaProb(prova.questoes.get(i), aluno.habilidade);
            if (random() > (1 - probAcerto)) {  //se a amostra obtida for maior que a probabilidade do aluno acertar a questão, incrementa um na nota (acertou)
                aluno.acertos[i] = 1;
                nota++;
            } else {
                aluno.acertos[i] = 0;
            }
        }
        return nota / prova.questoes.size();
    }
    //feito

    //Calcula a probabilidade do aluno ser melhor que o aluno2
    public static double probMelhor(Aluno aluno1, Aluno aluno2, int qtd) {
        double prob = 0; //media das probabilidades de todas as notas
        double nota1, nota2;
        switch (qtd) {
            case 10:
                for (int i = 0; i < aluno1.teste10.notas.size(); i++) {
                    nota1 = aluno1.teste10.notas.get(i);
                    nota2 = aluno2.teste10.notas.get(i);
                    if (nota1 > nota2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste10.notas.size();
            case 20:
                for (int i = 0; i < aluno1.teste20.notas.size(); i++) {
                    nota1 = aluno1.teste20.notas.get(i);
                    nota2 = aluno2.teste20.notas.get(i);
                    if (nota1 > nota2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste20.notas.size();
            case 50:
                for (int i = 0; i < aluno1.teste50.notas.size(); i++) {
                    nota1 = aluno1.teste50.notas.get(i);
                    nota2 = aluno2.teste50.notas.get(i);
                    if (nota1 > nota2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste50.notas.size();
            case 100:
                for (int i = 0; i < aluno1.teste100.notas.size(); i++) {
                    nota1 = aluno1.teste100.notas.get(i);
                    nota2 = aluno2.teste100.notas.get(i);
                    if (nota1 > nota2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste100.notas.size();
            default:
                break;
        }
        return 0;
    }

    public static double probMelhorHabilidade(Aluno aluno1, Aluno aluno2, int qtd) {
        double prob = 0; //media das probabilidades de todas as notas
        double hab1, hab2;
        switch (qtd) {
            case 10:
                for (int i = 0; i < aluno1.teste10.habilidade.size(); i++) {
                    hab1 = aluno1.teste10.habilidade.get(i);
                    hab2 = aluno2.teste10.habilidade.get(i);
                    if (hab1 > hab2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste10.habilidade.size();
            case 20:
                for (int i = 0; i < aluno1.teste20.habilidade.size(); i++) {
                    hab1 = aluno1.teste20.habilidade.get(i);
                    hab2 = aluno2.teste20.habilidade.get(i);
                    if (hab1 > hab2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste20.habilidade.size();
            case 50:
                for (int i = 0; i < aluno1.teste50.habilidade.size(); i++) {
                    hab1 = aluno1.teste50.habilidade.get(i);
                    hab2 = aluno2.teste50.habilidade.get(i);
                    if (hab1 > hab2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste50.habilidade.size();
            case 100:
                for (int i = 0; i < aluno1.teste100.habilidade.size(); i++) {
                    hab1 = aluno1.teste100.habilidade.get(i);
                    hab2 = aluno2.teste100.habilidade.get(i);
                    if (hab1 > hab2) {
                        prob++;
                    }
                }
                return prob / aluno1.teste100.habilidade.size();
            default:
                break;
        }
        return 0;
    }

    //calcula a nota para o aluno e armazena no arranjo de notas
    public static void aplicaTeste(Aluno aluno, Prova prova, int qtd) {
        //para cada prova, recolhe uma quantidade de amostras(notas) e guarda no aluno
        double nota = calculaNota(aluno, prova);
        //teste.filaNotas.add(nota);
        //teste.arrayNotas[i] = nota;
        //teste.notas = teste.filaNotas.stream().sorted().collect(Collectors.toList());
        //Arrays.sort(teste.arrayNotas);
        switch (qtd) {
            case 10:
                aluno.teste10.notas.add(nota);
                break;
            case 20:
                aluno.teste20.notas.add(nota);
                break;
            case 50:
                aluno.teste50.notas.add(nota);
                break;
            case 100:
                aluno.teste100.notas.add(nota);
                break;
            default:
                break;
        }
    }
    //feito

    //Sorteia uma prova com a quantidade de questões passadas por parâmetro
    public static Prova sorteiaProva(ArrayList<Questao> questoes, int qtdQuestoes) {
        Random ran = new Random();
        HashSet<Integer> sorteados = new HashSet<>();
        Prova prova = new Prova();
        int x = ran.nextInt(100);
        for (int i = 0; i < qtdQuestoes; i++) {
            while (sorteados.contains(x)) {
                x = ran.nextInt(100);
            }
            sorteados.add(x);
            prova.questoes.add(questoes.get(x));
        }
        return prova;
    }
    //feito

    //Descobre o intervalo de confiança do aluno para os testes com quantidade de questões determinado pelo parâmetro qtd
    private static Intervalo intervaloConfianca(Aluno aluno, int qtd) {
        Intervalo intervalo = new Intervalo();
        double a = 0;
        double b = 100;
        int indice1 = 0;
        int indice2 = (int) (QTD_AMOSTRAS * 0.9) - 1;
        double dif = b - a;
        double nota1, nota2;
        switch (qtd) {
            case 10:
                while (indice2 < aluno.teste10.notas.size()) {
                    nota1 = aluno.teste10.notas.get(indice1);
                    nota2 = aluno.teste10.notas.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 20:
                while (indice2 < aluno.teste20.notas.size()) {
                    nota1 = aluno.teste20.notas.get(indice1);
                    nota2 = aluno.teste20.notas.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 50:
                while (indice2 < aluno.teste50.notas.size()) {
                    nota1 = aluno.teste50.notas.get(indice1);
                    nota2 = aluno.teste50.notas.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 100:
                while (indice2 < aluno.teste100.notas.size()) {
                    nota1 = aluno.teste100.notas.get(indice1);
                    nota2 = aluno.teste100.notas.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
        }
        intervalo.a = a;
        intervalo.b = b;
        return intervalo;
    }

    private static Intervalo intervaloConfiancaHabilidade(Aluno aluno, int qtd) {
        Intervalo intervalo = new Intervalo();
        double a = 0;
        double b = 100;
        int indice1 = 0;
        int indice2 = (int) (QTD_AMOSTRAS * 0.9) - 1;
        double dif = b - a;
        double nota1, nota2;
        switch (qtd) {
            case 10:
                while (indice2 < aluno.teste10.habilidade.size()) {
                    nota1 = aluno.teste10.habilidade.get(indice1);
                    nota2 = aluno.teste10.habilidade.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 20:
                while (indice2 < aluno.teste20.habilidade.size()) {
                    nota1 = aluno.teste20.habilidade.get(indice1);
                    nota2 = aluno.teste20.habilidade.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 50:
                while (indice2 < aluno.teste50.habilidade.size()) {
                    nota1 = aluno.teste50.habilidade.get(indice1);
                    nota2 = aluno.teste50.habilidade.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
            case 100:
                while (indice2 < aluno.teste100.habilidade.size()) {
                    nota1 = aluno.teste100.habilidade.get(indice1);
                    nota2 = aluno.teste100.habilidade.get(indice2);
                    if ((nota2 - nota1) < dif) {
                        a = nota1;
                        b = nota2;
                        dif = nota2 - nota1;
                    }
                    indice1++;
                    indice2++;
                }
                break;
        }
        intervalo.a = a;
        intervalo.b = b;
        return intervalo;
    }

    //recebe um aluno e uma questão e devolve a probabilidade do aluno acertar a questão
    private static double calculaProb(Questao questao, double habilidade) {
        double aux = questao.a * (habilidade - questao.b);
        return (pow(E, aux)) / (1 + pow(E, aux));
    }
    //feito

    //seleciona as questoes que maximizam a probabilidade do aluno 5 ser melhor que o aluno 4
    private static Prova selecionaQuestoes(ArrayList<Questao> questoes, Aluno aluno5, Aluno aluno4, int qtd) {
        HashSet<Integer> selecionada = new HashSet<>();
        ArrayList<Questao> melhoresQuestoes = new ArrayList<>();
        double dif;
        int melhor;
        double prob5, prob4;
        for (int i = 0; i < qtd; i++) {
            dif = -1;
            melhor = -1;
            for (int j = 0; j < questoes.size(); j++) {
                if (!selecionada.contains(j)) {
                    Questao questao = questoes.get(j);
                    prob5 = calculaProb(questao, aluno5.habilidade);
                    prob4 = calculaProb(questao, aluno4.habilidade);
                    if ((prob5 - prob4) > dif) {
                        dif = prob5 - prob4;
                        melhor = j;
                    }
                }
            }
            melhoresQuestoes.add(questoes.get(melhor));
            selecionada.add(melhor);
        }
        return new Prova(melhoresQuestoes);
    }

    private static void parteUm() {

        Prova prova10;
        Prova prova20;
        Prova prova50;
        Prova prova100;    //Utilizado para armazenar as provas sorteadas

        //Utilizado para gerar os arquivos txt
        FileWriter arquivo;

        //arranjo com as questoes e seus parametros
        ArrayList<Questao> questoes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/gabri/Google Drive/USP/3 Sem/IE/dados/23/questoes.txt"));
            int i = 1;
            while (br.ready()) {
                String questaoStr = br.readLine();
                String[] parametros = questaoStr.split(" ");
                double disc = Double.parseDouble(parametros[0]);
                double dif = Double.parseDouble(parametros[1]);
                int indice = i;
                Questao questao = new Questao(disc, dif, indice);
                questoes.add(questao);
                i++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //arranjo com alunos e suas habilidades
        ArrayList<Aluno> alunos = new ArrayList<>();
        Aluno aluno1 = new Aluno(-1.0);
        Aluno aluno2 = new Aluno(-0.5);
        Aluno aluno3 = new Aluno(0.0);
        Aluno aluno4 = new Aluno(0.5);
        Aluno aluno5 = new Aluno(1.0);
        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        alunos.add(aluno5);

        prova100 = new Prova(questoes);
        //sorteia as provas e aplica os testes no aluno
        for (int i = 0; i < QTD_PROVAS; i++) {
            prova10 = sorteiaProva(questoes, 10);
            prova20 = sorteiaProva(questoes, 20);
            prova50 = sorteiaProva(questoes, 50);
            for (Aluno aluno : alunos) {
                aplicaTeste(aluno, prova10, 10);
                aplicaTeste(aluno, prova20, 20);
                aplicaTeste(aluno, prova50, 50);
                aplicaTeste(aluno, prova100, 100);
            }
        }

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 10 questoes
        double P_10_1 = probMelhor(alunos.get(4), alunos.get(0), 10);
        double P_10_2 = probMelhor(alunos.get(4), alunos.get(1), 10);
        double P_10_3 = probMelhor(alunos.get(4), alunos.get(2), 10);
        double P_10_4 = probMelhor(alunos.get(4), alunos.get(3), 10);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 20 questoes
        double P_20_1 = probMelhor(alunos.get(4), alunos.get(0), 20);
        double P_20_2 = probMelhor(alunos.get(4), alunos.get(1), 20);
        double P_20_3 = probMelhor(alunos.get(4), alunos.get(2), 20);
        double P_20_4 = probMelhor(alunos.get(4), alunos.get(3), 20);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 50 questoes
        double P_50_1 = probMelhor(alunos.get(4), alunos.get(0), 50);
        double P_50_2 = probMelhor(alunos.get(4), alunos.get(1), 50);
        double P_50_3 = probMelhor(alunos.get(4), alunos.get(2), 50);
        double P_50_4 = probMelhor(alunos.get(4), alunos.get(3), 50);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 100 questoes
        double P_100_1 = probMelhor(alunos.get(4), alunos.get(0), 100);
        double P_100_2 = probMelhor(alunos.get(4), alunos.get(1), 100);
        double P_100_3 = probMelhor(alunos.get(4), alunos.get(2), 100);
        double P_100_4 = probMelhor(alunos.get(4), alunos.get(3), 100);

        try {
            arquivo = new FileWriter(new File("I1.txt"));
            arquivo.write(Double.toString(P_10_1) + " " + Double.toString(P_10_2) + " " + Double.toString(P_10_3) + " " + Double.toString(P_10_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_20_1) + " " + Double.toString(P_20_2) + " " + Double.toString(P_20_3) + " " + Double.toString(P_20_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_50_1) + " " + Double.toString(P_50_2) + " " + Double.toString(P_50_3) + " " + Double.toString(P_50_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_100_1) + " " + Double.toString(P_100_2) + " " + Double.toString(P_100_3) + " " + Double.toString(P_100_4));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //seleciona as provas que maximizam a chance do aluno 5 ser melhor que o aluno 4
        prova10 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 10);
        prova20 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 20);
        prova50 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 50);

        //aplica o teste com as questoes escolhidas
        for (Aluno aluno : alunos) {
            aluno.teste10.notas.clear();
            aluno.teste20.notas.clear();
            aluno.teste50.notas.clear();
            for (int i = 0; i < QTD_AMOSTRAS; i++) {
                aplicaTeste(aluno, prova10, 10);
                aplicaTeste(aluno, prova20, 20);
                aplicaTeste(aluno, prova50, 50);
            }
        }

        //calcula as probabilidades do aluno 5 ser melhor que o aluno 1 para os testes com 10 questoes
        P_10_1 = probMelhor(alunos.get(4), alunos.get(0), 10);
        P_10_2 = probMelhor(alunos.get(4), alunos.get(1), 10);
        P_10_3 = probMelhor(alunos.get(4), alunos.get(2), 10);
        P_10_4 = probMelhor(alunos.get(4), alunos.get(3), 10);

        //calcula as probabilidades do aluno 5 ser melhor que o aluno 1 para os testes com 20 questoes
        P_20_1 = probMelhor(alunos.get(4), alunos.get(0), 20);
        P_20_2 = probMelhor(alunos.get(4), alunos.get(1), 20);
        P_20_3 = probMelhor(alunos.get(4), alunos.get(2), 20);
        P_20_4 = probMelhor(alunos.get(4), alunos.get(3), 20);

        //calcula as probabilidades do aluno 5 ser melhor que o aluno 1 para os testes com 20 questoes
        P_50_1 = probMelhor(alunos.get(4), alunos.get(0), 50);
        P_50_2 = probMelhor(alunos.get(4), alunos.get(1), 50);
        P_50_3 = probMelhor(alunos.get(4), alunos.get(2), 50);
        P_50_4 = probMelhor(alunos.get(4), alunos.get(3), 50);

        try {
            arquivo = new FileWriter(new File("I2.txt"));
            for (Questao questao : prova10.questoes) {
                arquivo.write(questao.indice + " ");
            }
            arquivo.write("\n");
            arquivo.write(Double.toString(P_10_1) + " " + Double.toString(P_10_2) + " " + Double.toString(P_10_3) + " " + Double.toString(P_10_4));
            arquivo.write("\n");
            for (Questao questao : prova20.questoes) {
                arquivo.write(questao.indice + " ");
            }
            arquivo.write("\n");
            arquivo.write(Double.toString(P_20_1) + " " + Double.toString(P_20_2) + " " + Double.toString(P_20_3) + " " + Double.toString(P_20_4));
            arquivo.write("\n");
            for (Questao questao : prova50.questoes) {
                arquivo.write(questao.indice + " ");
            }
            arquivo.write("\n");
            arquivo.write(Double.toString(P_50_1) + " " + Double.toString(P_50_2) + " " + Double.toString(P_50_3) + " " + Double.toString(P_50_4));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ordena as notas para encontrar o intervalo de confiança
        for (Aluno aluno : alunos) {
            Collections.sort(aluno.teste10.notas);
            Collections.sort(aluno.teste20.notas);
            Collections.sort(aluno.teste50.notas);
            Collections.sort(aluno.teste100.notas);
        }

        //calcula o intervalo de confianca do aluno 1
        Intervalo intervalo_10_1 = intervaloConfianca(alunos.get(0), 10);
        double I_10_1 = intervalo_10_1.a;
        double F_10_1 = intervalo_10_1.b;
        Intervalo intervalo_20_1 = intervaloConfianca(alunos.get(0), 20);
        double I_20_1 = intervalo_20_1.a;
        double F_20_1 = intervalo_20_1.b;
        Intervalo intervalo_50_1 = intervaloConfianca(alunos.get(0), 50);
        double I_50_1 = intervalo_50_1.a;
        double F_50_1 = intervalo_50_1.b;
        Intervalo intervalo_100_1 = intervaloConfianca(alunos.get(0), 100);
        double I_100_1 = intervalo_100_1.a;
        double F_100_1 = intervalo_100_1.b;

        //calcula o intervalo de confianca do aluno 2
        Intervalo intervalo_10_2 = intervaloConfianca(alunos.get(1), 10);
        double I_10_2 = intervalo_10_2.a;
        double F_10_2 = intervalo_10_2.b;
        Intervalo intervalo_20_2 = intervaloConfianca(alunos.get(1), 20);
        double I_20_2 = intervalo_20_2.a;
        double F_20_2 = intervalo_20_2.b;
        Intervalo intervalo_50_2 = intervaloConfianca(alunos.get(1), 50);
        double I_50_2 = intervalo_50_2.a;
        double F_50_2 = intervalo_50_2.b;
        Intervalo intervalo_100_2 = intervaloConfianca(alunos.get(1), 100);
        double I_100_2 = intervalo_100_2.a;
        double F_100_2 = intervalo_100_2.b;

        //calcula o intervalo de confianca do aluno 3
        Intervalo intervalo_10_3 = intervaloConfianca(alunos.get(2), 10);
        double I_10_3 = intervalo_10_3.a;
        double F_10_3 = intervalo_10_3.b;
        Intervalo intervalo_20_3 = intervaloConfianca(alunos.get(2), 20);
        double I_20_3 = intervalo_20_3.a;
        double F_20_3 = intervalo_20_3.b;
        Intervalo intervalo_50_3 = intervaloConfianca(alunos.get(2), 50);
        double I_50_3 = intervalo_50_3.a;
        double F_50_3 = intervalo_50_3.b;
        Intervalo intervalo_100_3 = intervaloConfianca(alunos.get(2), 100);
        double I_100_3 = intervalo_100_3.a;
        double F_100_3 = intervalo_100_3.b;

        //calcula o intervalo de confianca do aluno 4
        Intervalo intervalo_10_4 = intervaloConfianca(alunos.get(3), 10);
        double I_10_4 = intervalo_10_4.a;
        double F_10_4 = intervalo_10_4.b;
        Intervalo intervalo_20_4 = intervaloConfianca(alunos.get(3), 20);
        double I_20_4 = intervalo_20_4.a;
        double F_20_4 = intervalo_20_4.b;
        Intervalo intervalo_50_4 = intervaloConfianca(alunos.get(3), 50);
        double I_50_4 = intervalo_50_4.a;
        double F_50_4 = intervalo_50_4.b;
        Intervalo intervalo_100_4 = intervaloConfianca(alunos.get(3), 100);
        double I_100_4 = intervalo_100_4.a;
        double F_100_4 = intervalo_100_4.b;

        //calcula o intervalo de confianca do aluno 5
        Intervalo intervalo_10_5 = intervaloConfianca(alunos.get(4), 10);
        double I_10_5 = intervalo_10_5.a;
        double F_10_5 = intervalo_10_5.b;
        Intervalo intervalo_20_5 = intervaloConfianca(alunos.get(4), 20);
        double I_20_5 = intervalo_20_5.a;
        double F_20_5 = intervalo_20_5.b;
        Intervalo intervalo_50_5 = intervaloConfianca(alunos.get(4), 50);
        double I_50_5 = intervalo_50_5.a;
        double F_50_5 = intervalo_50_5.b;
        Intervalo intervalo_100_5 = intervaloConfianca(alunos.get(4), 100);
        double I_100_5 = intervalo_100_5.a;
        double F_100_5 = intervalo_100_5.b;

        try {
            arquivo = new FileWriter(new File("I3.txt"));
            arquivo.write(Double.toString(I_10_1) + " " + Double.toString(F_10_1) + " " + Double.toString(I_10_2) + " " + Double.toString(F_10_2) + " "
                    + Double.toString(I_10_3) + " " + Double.toString(F_10_3) + " " + Double.toString(I_10_4) + " " + Double.toString(F_10_4) + " "
                    + Double.toString(I_10_5) + " " + Double.toString(F_10_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_20_1) + " " + Double.toString(F_20_1) + " " + Double.toString(I_20_2) + " " + Double.toString(F_20_2) + " "
                    + Double.toString(I_20_3) + " " + Double.toString(F_20_3) + " " + Double.toString(I_20_4) + " " + Double.toString(F_20_4) + " "
                    + Double.toString(I_20_5) + " " + Double.toString(F_20_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_50_1) + " " + Double.toString(F_50_1) + " " + Double.toString(I_50_2) + " " + Double.toString(F_50_2) + " "
                    + Double.toString(I_50_3) + " " + Double.toString(F_50_3) + " " + Double.toString(I_50_4) + " " + Double.toString(F_50_4) + " "
                    + Double.toString(I_50_5) + " " + Double.toString(F_50_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_100_1) + " " + Double.toString(F_100_1) + " " + Double.toString(I_100_2) + " " + Double.toString(F_100_2) + " "
                    + Double.toString(I_100_3) + " " + Double.toString(F_100_3) + " " + Double.toString(I_100_4) + " " + Double.toString(F_100_4) + " "
                    + Double.toString(I_100_5) + " " + Double.toString(F_100_5));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void parteDois() {

        Prova prova10;
        Prova prova20;
        Prova prova50;
        Prova prova100;    //Utilizado para armazenar as provas sorteadas
        Double habilidade;

        //Utilizado para gerar os arquivos txt
        FileWriter arquivo;

        //arranjo com as questoes e seus parametros
        ArrayList<Questao> questoes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/gabri/Google Drive/USP/3 Sem/IE/dados/23/questoes.txt"));
            int i = 1;
            while (br.ready()) {
                String questaoStr = br.readLine();
                String[] parametros = questaoStr.split(" ");
                double disc = Double.parseDouble(parametros[0]);
                double dif = Double.parseDouble(parametros[1]);
                int indice = i;
                Questao questao = new Questao(disc, dif, indice);
                questoes.add(questao);
                i++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        prova100 = new Prova(questoes);

        //arranjo com os alunos e seus parametros
        ArrayList<Aluno> alunos2000 = new ArrayList<>(2000);

        for (int j = 0; j < 2000; j++) {
            Aluno aluno = new Aluno();
            alunos2000.add(aluno);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/gabri/Google Drive/USP/3 Sem/IE/dados/23/respostas.txt"));
            int i = 0;
            while (br.ready()) {
                String questaoStr = br.readLine();
                String[] parametros = questaoStr.split(" ");
                for (int j = 0; j < parametros.length; j++) {
                    int acerto = Integer.parseInt(parametros[j]);
                    alunos2000.get(j).acertos[i] = acerto;
                }
                i++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (Aluno aluno : alunos2000) {
            aluno.habilidade = calculaHabilidadeNewton(aluno, prova100);

        }

        try {
            arquivo = new FileWriter(new File("II1.txt"));
            for (Aluno aluno : alunos2000) {
                arquivo.write(Double.toString(aluno.habilidade));
                arquivo.write("\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //arranjo com alunos e suas habilidades
        ArrayList<Aluno> alunos = new ArrayList<>();
        Aluno aluno1 = new Aluno(-1.0);
        Aluno aluno2 = new Aluno(-0.5);
        Aluno aluno3 = new Aluno(0.0);
        Aluno aluno4 = new Aluno(0.5);
        Aluno aluno5 = new Aluno(1.0);
        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        alunos.add(aluno5);

        //seleciona as provas que maximizam a chance do aluno 5 ser melhor que o aluno 4
        prova10 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 10);
        prova20 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 20);
        prova50 = selecionaQuestoes(questoes, alunos.get(4), alunos.get(3), 50);

        //aplica o teste com as questoes escolhidas
        for (Aluno aluno : alunos) {
            for (int i = 0; i < QTD_AMOSTRAS; i++) {
                aplicaTeste(aluno, prova10, 10);
                while (Double.isNaN(calculaHabilidadeNewton(aluno, prova10))) {
                    aplicaTeste(aluno, prova10, 10);
                }
                aluno.teste10.habilidade.add(calculaHabilidadeNewton(aluno, prova10));

                aplicaTeste(aluno, prova20, 20);
                while (Double.isNaN(calculaHabilidadeNewton(aluno, prova20))) {
                aplicaTeste(aluno, prova20, 20);
                }
                aluno.teste20.habilidade.add(calculaHabilidadeNewton(aluno, prova20));
                
                aplicaTeste(aluno, prova50, 50);
                while (Double.isNaN(calculaHabilidadeNewton(aluno, prova50))) {
                aplicaTeste(aluno, prova50, 50);
                }
                aluno.teste50.habilidade.add(calculaHabilidadeNewton(aluno, prova50));
                
                aplicaTeste(aluno, prova100, 100);
                while (Double.isNaN(calculaHabilidadeNewton(aluno, prova100))) {
                    aplicaTeste(aluno, prova100, 100);
                }
                aluno.teste100.habilidade.add(calculaHabilidadeNewton(aluno, prova100));
            }
        }

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 10 questoes
        double P_10_1 = probMelhorHabilidade(alunos.get(4), alunos.get(0), 10);
        double P_10_2 = probMelhorHabilidade(alunos.get(4), alunos.get(1), 10);
        double P_10_3 = probMelhorHabilidade(alunos.get(4), alunos.get(2), 10);
        double P_10_4 = probMelhorHabilidade(alunos.get(4), alunos.get(3), 10);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 20 questoes
        double P_20_1 = probMelhorHabilidade(alunos.get(4), alunos.get(0), 20);
        double P_20_2 = probMelhorHabilidade(alunos.get(4), alunos.get(1), 20);
        double P_20_3 = probMelhorHabilidade(alunos.get(4), alunos.get(2), 20);
        double P_20_4 = probMelhorHabilidade(alunos.get(4), alunos.get(3), 20);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 50 questoes
        double P_50_1 = probMelhorHabilidade(alunos.get(4), alunos.get(0), 50);
        double P_50_2 = probMelhorHabilidade(alunos.get(4), alunos.get(1), 50);
        double P_50_3 = probMelhorHabilidade(alunos.get(4), alunos.get(2), 50);
        double P_50_4 = probMelhorHabilidade(alunos.get(4), alunos.get(3), 50);

        //calcula as probabilidades do aluno 5 ser melhor que os outros alunos para os testes com 100 questoes
        double P_100_1 = probMelhorHabilidade(alunos.get(4), alunos.get(0), 100);
        double P_100_2 = probMelhorHabilidade(alunos.get(4), alunos.get(1), 100);
        double P_100_3 = probMelhorHabilidade(alunos.get(4), alunos.get(2), 100);
        double P_100_4 = probMelhorHabilidade(alunos.get(4), alunos.get(3), 100);

        try {
            arquivo = new FileWriter(new File("II2.txt"));
            arquivo.write(Double.toString(P_10_1) + " " + Double.toString(P_10_2) + " " + Double.toString(P_10_3) + " " + Double.toString(P_10_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_20_1) + " " + Double.toString(P_20_2) + " " + Double.toString(P_20_3) + " " + Double.toString(P_20_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_50_1) + " " + Double.toString(P_50_2) + " " + Double.toString(P_50_3) + " " + Double.toString(P_50_4));
            arquivo.write("\n");
            arquivo.write(Double.toString(P_100_1) + " " + Double.toString(P_100_2) + " " + Double.toString(P_100_3) + " " + Double.toString(P_100_4));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ordena as notas para encontrar o intervalo de confiança
        for (Aluno aluno : alunos) {
            Collections.sort(aluno.teste10.habilidade);
            Collections.sort(aluno.teste20.habilidade);
            Collections.sort(aluno.teste50.habilidade);
            Collections.sort(aluno.teste100.habilidade);
        }

        //calcula o intervalo de confianca do aluno 1
        Intervalo intervalo_10_1 = intervaloConfiancaHabilidade(alunos.get(0), 10);
        double I_10_1 = intervalo_10_1.a;
        double F_10_1 = intervalo_10_1.b;
        Intervalo intervalo_20_1 = intervaloConfiancaHabilidade(alunos.get(0), 20);
        double I_20_1 = intervalo_20_1.a;
        double F_20_1 = intervalo_20_1.b;
        Intervalo intervalo_50_1 = intervaloConfiancaHabilidade(alunos.get(0), 50);
        double I_50_1 = intervalo_50_1.a;
        double F_50_1 = intervalo_50_1.b;
        Intervalo intervalo_100_1 = intervaloConfiancaHabilidade(alunos.get(0), 100);
        double I_100_1 = intervalo_100_1.a;
        double F_100_1 = intervalo_100_1.b;

        //calcula o intervalo de confianca do aluno 2
        Intervalo intervalo_10_2 = intervaloConfiancaHabilidade(alunos.get(1), 10);
        double I_10_2 = intervalo_10_2.a;
        double F_10_2 = intervalo_10_2.b;
        Intervalo intervalo_20_2 = intervaloConfiancaHabilidade(alunos.get(1), 20);
        double I_20_2 = intervalo_20_2.a;
        double F_20_2 = intervalo_20_2.b;
        Intervalo intervalo_50_2 = intervaloConfiancaHabilidade(alunos.get(1), 50);
        double I_50_2 = intervalo_50_2.a;
        double F_50_2 = intervalo_50_2.b;
        Intervalo intervalo_100_2 = intervaloConfiancaHabilidade(alunos.get(1), 100);
        double I_100_2 = intervalo_100_2.a;
        double F_100_2 = intervalo_100_2.b;

        //calcula o intervalo de confianca do aluno 3
        Intervalo intervalo_10_3 = intervaloConfiancaHabilidade(alunos.get(2), 10);
        double I_10_3 = intervalo_10_3.a;
        double F_10_3 = intervalo_10_3.b;
        Intervalo intervalo_20_3 = intervaloConfiancaHabilidade(alunos.get(2), 20);
        double I_20_3 = intervalo_20_3.a;
        double F_20_3 = intervalo_20_3.b;
        Intervalo intervalo_50_3 = intervaloConfiancaHabilidade(alunos.get(2), 50);
        double I_50_3 = intervalo_50_3.a;
        double F_50_3 = intervalo_50_3.b;
        Intervalo intervalo_100_3 = intervaloConfiancaHabilidade(alunos.get(2), 100);
        double I_100_3 = intervalo_100_3.a;
        double F_100_3 = intervalo_100_3.b;

        //calcula o intervalo de confianca do aluno 4
        Intervalo intervalo_10_4 = intervaloConfiancaHabilidade(alunos.get(3), 10);
        double I_10_4 = intervalo_10_4.a;
        double F_10_4 = intervalo_10_4.b;
        Intervalo intervalo_20_4 = intervaloConfiancaHabilidade(alunos.get(3), 20);
        double I_20_4 = intervalo_20_4.a;
        double F_20_4 = intervalo_20_4.b;
        Intervalo intervalo_50_4 = intervaloConfiancaHabilidade(alunos.get(3), 50);
        double I_50_4 = intervalo_50_4.a;
        double F_50_4 = intervalo_50_4.b;
        Intervalo intervalo_100_4 = intervaloConfiancaHabilidade(alunos.get(3), 100);
        double I_100_4 = intervalo_100_4.a;
        double F_100_4 = intervalo_100_4.b;

        //calcula o intervalo de confianca do aluno 5
        Intervalo intervalo_10_5 = intervaloConfiancaHabilidade(alunos.get(4), 10);
        double I_10_5 = intervalo_10_5.a;
        double F_10_5 = intervalo_10_5.b;
        Intervalo intervalo_20_5 = intervaloConfiancaHabilidade(alunos.get(4), 20);
        double I_20_5 = intervalo_20_5.a;
        double F_20_5 = intervalo_20_5.b;
        Intervalo intervalo_50_5 = intervaloConfiancaHabilidade(alunos.get(4), 50);
        double I_50_5 = intervalo_50_5.a;
        double F_50_5 = intervalo_50_5.b;
        Intervalo intervalo_100_5 = intervaloConfiancaHabilidade(alunos.get(4), 100);
        double I_100_5 = intervalo_100_5.a;
        double F_100_5 = intervalo_100_5.b;

        try {
            arquivo = new FileWriter(new File("II3.txt"));
            arquivo.write(Double.toString(I_10_1) + " " + Double.toString(F_10_1) + " " + Double.toString(I_10_2) + " " + Double.toString(F_10_2) + " "
                    + Double.toString(I_10_3) + " " + Double.toString(F_10_3) + " " + Double.toString(I_10_4) + " " + Double.toString(F_10_4) + " "
                    + Double.toString(I_10_5) + " " + Double.toString(F_10_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_20_1) + " " + Double.toString(F_20_1) + " " + Double.toString(I_20_2) + " " + Double.toString(F_20_2) + " "
                    + Double.toString(I_20_3) + " " + Double.toString(F_20_3) + " " + Double.toString(I_20_4) + " " + Double.toString(F_20_4) + " "
                    + Double.toString(I_20_5) + " " + Double.toString(F_20_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_50_1) + " " + Double.toString(F_50_1) + " " + Double.toString(I_50_2) + " " + Double.toString(F_50_2) + " "
                    + Double.toString(I_50_3) + " " + Double.toString(F_50_3) + " " + Double.toString(I_50_4) + " " + Double.toString(F_50_4) + " "
                    + Double.toString(I_50_5) + " " + Double.toString(F_50_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_100_1) + " " + Double.toString(F_100_1) + " " + Double.toString(I_100_2) + " " + Double.toString(F_100_2) + " "
                    + Double.toString(I_100_3) + " " + Double.toString(F_100_3) + " " + Double.toString(I_100_4) + " " + Double.toString(F_100_4) + " "
                    + Double.toString(I_100_5) + " " + Double.toString(F_100_5));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }        

        //calcula o intervalo de confianca do aluno 1
        intervalo_10_1 = intervaloConfiancaNormal(alunos.get(0), prova10);
         I_10_1 = intervalo_10_1.a;
         F_10_1 = intervalo_10_1.b;
         intervalo_20_1 = intervaloConfiancaNormal(alunos.get(0), prova20);
         I_20_1 = intervalo_20_1.a;
         F_20_1 = intervalo_20_1.b;
         intervalo_50_1 = intervaloConfiancaNormal(alunos.get(0), prova50);
         I_50_1 = intervalo_50_1.a;
         F_50_1 = intervalo_50_1.b;
         intervalo_100_1 = intervaloConfiancaNormal(alunos.get(0), prova100);
         I_100_1 = intervalo_100_1.a;
         F_100_1 = intervalo_100_1.b;

        //calcula o intervalo de confianca do aluno 2
         intervalo_10_2 = intervaloConfiancaNormal(alunos.get(1), prova10);
         I_10_2 = intervalo_10_2.a;
         F_10_2 = intervalo_10_2.b;
         intervalo_20_2 = intervaloConfiancaNormal(alunos.get(1), prova20);
         I_20_2 = intervalo_20_2.a;
         F_20_2 = intervalo_20_2.b;
         intervalo_50_2 = intervaloConfiancaNormal(alunos.get(1), prova50);
         I_50_2 = intervalo_50_2.a;
         F_50_2 = intervalo_50_2.b;
         intervalo_100_2 = intervaloConfiancaNormal(alunos.get(1), prova100);
         I_100_2 = intervalo_100_2.a;
         F_100_2 = intervalo_100_2.b;

        //calcula o intervalo de confianca do aluno 3
         intervalo_10_3 = intervaloConfiancaNormal(alunos.get(2), prova10);
         I_10_3 = intervalo_10_3.a;
         F_10_3 = intervalo_10_3.b;
         intervalo_20_3 = intervaloConfiancaNormal(alunos.get(2), prova20);
         I_20_3 = intervalo_20_3.a;
         F_20_3 = intervalo_20_3.b;
         intervalo_50_3 = intervaloConfiancaNormal(alunos.get(2), prova50);
         I_50_3 = intervalo_50_3.a;
         F_50_3 = intervalo_50_3.b;
         intervalo_100_3 = intervaloConfiancaNormal(alunos.get(2), prova100);
         I_100_3 = intervalo_100_3.a;
         F_100_3 = intervalo_100_3.b;

        //calcula o intervalo de confianca do aluno 4
         intervalo_10_4 = intervaloConfiancaNormal(alunos.get(3), prova10);
         I_10_4 = intervalo_10_4.a;
         F_10_4 = intervalo_10_4.b;
         intervalo_20_4 = intervaloConfiancaNormal(alunos.get(3), prova20);
         I_20_4 = intervalo_20_4.a;
         F_20_4 = intervalo_20_4.b;
         intervalo_50_4 = intervaloConfiancaNormal(alunos.get(3), prova50);
         I_50_4 = intervalo_50_4.a;
         F_50_4 = intervalo_50_4.b;
         intervalo_100_4 = intervaloConfiancaNormal(alunos.get(3), prova100);
         I_100_4 = intervalo_100_4.a;
         F_100_4 = intervalo_100_4.b;

        //calcula o intervalo de confianca do aluno 5
         intervalo_10_5 = intervaloConfiancaNormal(alunos.get(4), prova10);
         I_10_5 = intervalo_10_5.a;
         F_10_5 = intervalo_10_5.b;
         intervalo_20_5 = intervaloConfiancaNormal(alunos.get(4), prova20);
         I_20_5 = intervalo_20_5.a;
         F_20_5 = intervalo_20_5.b;
         intervalo_50_5 = intervaloConfiancaNormal(alunos.get(4), prova50);
         I_50_5 = intervalo_50_5.a;
         F_50_5 = intervalo_50_5.b;
         intervalo_100_5 = intervaloConfiancaNormal(alunos.get(4), prova100);
         I_100_5 = intervalo_100_5.a;
         F_100_5 = intervalo_100_5.b;

        try {
            arquivo = new FileWriter(new File("II4.txt"));
            arquivo.write(Double.toString(I_10_1) + " " + Double.toString(F_10_1) + " " + Double.toString(I_10_2) + " " + Double.toString(F_10_2) + " "
                    + Double.toString(I_10_3) + " " + Double.toString(F_10_3) + " " + Double.toString(I_10_4) + " " + Double.toString(F_10_4) + " "
                    + Double.toString(I_10_5) + " " + Double.toString(F_10_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_20_1) + " " + Double.toString(F_20_1) + " " + Double.toString(I_20_2) + " " + Double.toString(F_20_2) + " "
                    + Double.toString(I_20_3) + " " + Double.toString(F_20_3) + " " + Double.toString(I_20_4) + " " + Double.toString(F_20_4) + " "
                    + Double.toString(I_20_5) + " " + Double.toString(F_20_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_50_1) + " " + Double.toString(F_50_1) + " " + Double.toString(I_50_2) + " " + Double.toString(F_50_2) + " "
                    + Double.toString(I_50_3) + " " + Double.toString(F_50_3) + " " + Double.toString(I_50_4) + " " + Double.toString(F_50_4) + " "
                    + Double.toString(I_50_5) + " " + Double.toString(F_50_5));
            arquivo.write("\n");
            arquivo.write(Double.toString(I_100_1) + " " + Double.toString(F_100_1) + " " + Double.toString(I_100_2) + " " + Double.toString(F_100_2) + " "
                    + Double.toString(I_100_3) + " " + Double.toString(F_100_3) + " " + Double.toString(I_100_4) + " " + Double.toString(F_100_4) + " "
                    + Double.toString(I_100_5) + " " + Double.toString(F_100_5));
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static double calculaHabilidadeBissecao(Aluno aluno, Prova prova) {
        double A = -1, B = 1, medio, alpha = 0.0001;
        //encontra valores iniciais
        while (primeiraDerivada(aluno, prova, A) * primeiraDerivada(aluno, prova, B) > 0) {
            if (Math.abs(primeiraDerivada(aluno, prova, A) - 0) < Math.abs(primeiraDerivada(aluno, prova, B) - 0)) {
                A = A * 2;
            } else {
                B = B * 2;
            }
        }

        while (Math.abs(A - B) > alpha) {

            medio = (A + B) / 2;
            if (primeiraDerivada(aluno, prova, medio) * primeiraDerivada(aluno, prova, B) < 0) {
                A = medio;
            } else if (primeiraDerivada(aluno, prova, A) * primeiraDerivada(aluno, prova, medio) < 0) {
                B = medio;
            } else {
                System.out.println("\n\nERRO");
            }
        }
        return A;
    }

    private static double calculaHabilidadeNewton(Aluno aluno, Prova prova) {
        double aux = 0, valorAnt = 0, valorAtual = 999, alpha = 0.00001;
        //encontra valores iniciais

        while (Math.abs(valorAtual - valorAnt) > alpha) {
            valorAnt = aux;
            valorAtual = valorAnt - (primeiraDerivada(aluno, prova, valorAnt) / segundaDerivada(aluno, prova, valorAnt));
            aux = valorAtual;
        }
        return valorAtual;
    }

    private static double primeiraDerivada(Aluno aluno, Prova prova, double theta) {
        double soma = 0;
        double aux = 0, aux2 = 0, aux3 = 0, aux4 = 0, aux5 = 0;

        for (int i = 0; i < prova.questoes.size(); i++) {
            aux = prova.questoes.get(i).a * (theta - prova.questoes.get(i).b);
            aux = (pow(E, aux));
            aux2 = 1 + aux;
            aux3 = -prova.questoes.get(i).a * aux;
            aux3 = aux3 / aux2;
            aux5 = (1 - aluno.acertos[i]) * aux3;

            aux2 = 1 + aux;
            aux3 = prova.questoes.get(i).a * aux;
            aux3 = prova.questoes.get(i).a - (aux3 / aux2);
            aux4 = (aluno.acertos[i]) * aux3;

            aux = aux4 + aux5;
            soma = soma + aux;
        }

        return soma;
    }

    private static double segundaDerivada(Aluno aluno, Prova prova, double theta) {
        double soma = 0;
        double aux = 0, aux2 = 0, aux3 = 0, aux4 = 0, aux5 = 0;

        for (int i = 0; i < prova.questoes.size(); i++) {
            aux = prova.questoes.get(i).a * (theta - prova.questoes.get(i).b);
            aux = (pow(E, aux));
            aux2 = (pow(prova.questoes.get(i).a, 2)) * aux;

            aux3 = 1 + aux;
            aux4 = pow(aux3, 2);

            aux5 = -aux2 / aux4;
            soma = soma + aux5;
        }

        return soma;
    }   
    
    private static Intervalo intervaloConfiancaNormal(Aluno aluno, Prova prova) {
        Intervalo intervalo = new Intervalo();
        double mi = 0.0;
        double sigma = 0.0;
        double y;
               
        for(int i = 0; i < prova.questoes.size(); i++){
            double p = calculaProb(prova.questoes.get(i), aluno.habilidade);
            mi = mi + p;
            sigma = sigma + (p*(1-p));
        }
        
        sigma = Math.sqrt(sigma);
        //1.29
        intervalo.a = (-1.65 * sigma + mi) / prova.questoes.size();
        intervalo.b = (1.65 * sigma + mi) / prova.questoes.size();
        

        return intervalo;
        
    }

}
