package org.example;

import org.example.model.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Integer escolha;
        Integer escolhaNovaCarta;

        do {
            menu();
            System.out.print("Escolha: ");
            escolha = scanner().nextInt();

            if (escolha.equals(1)) {
                var baralho = baralho();


                System.out.println("====================");
                System.out.println("Vamos Jogar !!!");
                System.out.println("====================");


                List<Carta> dealer = new ArrayList<>();
                dealer.add(darCarta(baralho));
                dealer.add(darCarta(baralho));
                System.out.println("DEALER - Primeira carta: " + dealer.get(0).getValor() + " " + dealer.get(0).getNaipe());
                System.out.println("DEALER - Segunda carta:  X");
                Integer pontuacaoDealer = dealer.get(0).getValor() + dealer.get(1).getValor();
                System.out.println("A pontuação do Dealer é: " + pontuacaoDealer);

                List<Carta> player1 = new ArrayList<>();
                player1.add(darCarta(baralho));
                player1.add(darCarta(baralho));
                System.out.println("player1: " + player1.get(0).getValor() + " " + player1.get(0).getNaipe());
                System.out.println("player1: " + player1.get(1).getValor() + " " + player1.get(1).getNaipe());
                Integer pontuacaoPlayer1 = player1.get(0).getValor() + player1.get(1).getValor();

                do {
                    System.out.println("Sua pontuação é: " + pontuacaoPlayer1 + "\nDeseja outra carta?\n1 = Sim / 2 = Não");
                    System.out.print("Escolha: ");
                    escolhaNovaCarta = scanner().nextInt();

                    if (escolhaNovaCarta.equals(1)){
                        var novaCarta = darCarta(baralho);
                        pontuacaoPlayer1 = pontuacaoPlayer1 + novaCarta.getValor();

                    }


                }while (escolhaNovaCarta == 1);

                if (pontuacaoPlayer1 > 21) {
                    System.out.println("Pontuação: " + pontuacaoDealer + " Voce Perdeu!");
                } else if (pontuacaoPlayer1 == 21) {
                    System.out.println("BLACK JACK! VOCÊ GANHOU !");
                } else {
                    System.out.println(" -----------------");
                    System.out.println("Agora é A vez do Dealer");

                    System.out.println("DEALER - Primeira carta: " + dealer.get(0).getValor() + " " + dealer.get(0).getNaipe());
                    System.out.println("DEALER - Segunda carta: " + dealer.get(1).getValor() + " " + dealer.get(1).getNaipe());

                    do {

                        if (pontuacaoDealer < pontuacaoPlayer1){
                            var novaCarta = darCarta(baralho);
                            System.out.println("Nova carta: " + novaCarta.getValor() + " " + novaCarta.getNaipe());

                            pontuacaoDealer = pontuacaoDealer + novaCarta.getValor();
                            System.out.println("Pontuação Dealer: " + pontuacaoDealer);
                        }


                    }while (pontuacaoDealer < pontuacaoPlayer1);


                    if (pontuacaoDealer > pontuacaoPlayer1 && pontuacaoDealer <= 21) {
                        System.out.println("Pontuação Dealer: " + pontuacaoDealer);
                        System.out.println("O Dealer Ganhou!");
                    }else if (pontuacaoDealer.equals(pontuacaoPlayer1)){
                        System.out.println("Pontuação Dealer: " + pontuacaoDealer);
                        System.out.println("Sua Pontuação: " + pontuacaoPlayer1);
                        System.out.println("Empate!");
                    }else {
                        System.out.println("Sua Pontuação: " + pontuacaoPlayer1);
                        System.out.println("O Jogador ganhou !");
                    }

                }


            }


        }while (escolha !=4);

    }

    private static void menu() {
        System.out.println("======= BLACK JACK =======");
        System.out.println("ESCOLHA UMA OPÇÃO : ");
        System.out.println("1 : Para Jogar");
        System.out.println("2 : Ler as Regras");
        System.out.println("3 : Créditos");
        System.out.println("4 : Fechar o Jogo");
        System.out.println("======= ~~~~ ~~~~ =======");

    }

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static List<Carta> baralho() {

        var baralho = inicializarBaralho();
        var baralhoEmbaralhado = embaralhar(baralho);
        return baralhoEmbaralhado;
    }

    private static List<Carta> inicializarBaralho() {
        List<Carta> cartas = new ArrayList<>();
        String[] naipes = {"Paus", "Ouros", "Copas", "Espadas"};
        Integer[] valores = {1,2,3,4,5,6,7,8,9,10,10,10,10};

        for (String naipe : naipes) {
            for (Integer valor : valores) {
                cartas.add(new Carta(naipe,valor));
            }
        }
        return cartas;
    }

    public static List<Carta> embaralhar(List<Carta> baralho) {
        Collections.shuffle(baralho);
        return baralho;
    }

    public static Carta darCarta(List<Carta> baralho) {
        if (baralho.isEmpty()) {
            System.out.println("LOG: Acabaram as cartas\n -----");
            return null; // Não há cartas no baralho
        }
        System.out.println("LOG: Dando uma carta! -----");
        return baralho.remove(0); // Remove e retorna a carta do topo do baralho
    }

    public static void mostraBaralho(List<Carta> baralho) {

        for (Carta carta: baralho) {
            System.out.println(carta.getValor() + " de " + carta.getNaipe());
        }
    }

}