package br.edu.ufcg.computacao.mrbet;

import java.util.Scanner;

/**
 * Interface com menu de texto para manipular o sistema
 */
public class MainMrBet {
    public static void main(String[] args) {
        MrBetSistema mrBet = new MrBetSistema();

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            verificaEntradaVazia(escolha);
            verificaEntradaNula(escolha);
            comando(escolha, mrBet, scanner);
        }

    }

    /**
     * Recebe o opcao escolhida pelo usuario e chama o metodo responsável pela funcionalidade.
     * @param escolha escolha do usuario
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void comando(String escolha, MrBetSistema mrBet, Scanner scanner) {
        switch (escolha) {
            case "M":
                cadatraTime(mrBet, scanner);
                break;
            case "R":
                recuperaTime(mrBet, scanner);
                break;
            case ".":
                adicionaCampeonato(mrBet, scanner);
                break;
            case "B":
                incluiTimeNoCampeonato(mrBet, scanner);
                break;
            case "E":
                exibeCampeonatosDoTime(mrBet, scanner);
                break;
            case "T":
                gerenciaApostas(mrBet, scanner);
                break;
            case "H":
                mostraHistorico(mrBet, scanner);
                break;
            case "!":
                sai();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    /**
     * Apresenta um menu com as opções disponiveis para o usuario
     * @param scanner leitor das entradas do sistema
     * @return
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                """
                        (M)Minha inclusão de times
                        (R)Recuperar time
                        (.)Adicionar campeonato
                        (B)Bora incluir time em campeonato e Verificar se time está em campeonato
                        (E)Exibir campeonatos que o time participa
                        (T)Tentar a sorte e status
                        (P)Time(s) com participação mais frequente em campeonatos
                        (N)Time(s) que ainda não participou de campeonato
                        (L)Popularidade em aposta
                        (!)Já pode fechar o programa!

                        Opção>\s""");

        return scanner.nextLine().toUpperCase();
    }

    /**
     * Cadastra o time no sistema
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void cadatraTime(MrBetSistema mrBet, Scanner scanner) {
        System.out.println("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Mascote: ");
        String mascote = scanner.nextLine();
        System.out.println(mrBet.cadastraTime(codigo, nome, mascote) + "\n");
    }

    /**
     * Exibe um time ao usuario
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void recuperaTime(MrBetSistema mrBet, Scanner scanner) {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.println(mrBet.recuperaTime(codigo) + "\n");
    }

    /**
     * Adiciona campeonato ao sistema
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void adicionaCampeonato(MrBetSistema mrBet, Scanner scanner) {
        System.out.print("Campeonato: ");
        String nome = scanner.nextLine();
        System.out.print("Participantes: ");
        int qtdeTimes = Integer.parseInt(scanner.nextLine());
        System.out.println(mrBet.adicionaCampeonato(nome, qtdeTimes) + "\n");
    }

    /**
     * Adiciona um time em um campeonato
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do usuario
     */
    private static void incluiTimeNoCampeonato(MrBetSistema mrBet, Scanner scanner) {
        System.out.print("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato: ");
        String opcao = scanner.nextLine().toUpperCase();
        System.out.print("Código: ");
        String codigoTime = scanner.nextLine();
        System.out.print("Campeonato: ");
        String nomeCampeonato = scanner.nextLine();
        switch (opcao) {
            case "I":
                System.out.println(mrBet.incluiTimeNoCampeonato(codigoTime, nomeCampeonato) + "\n");
                break;
            case "V":
                System.out.println(mrBet.verificaTimeNoCampeonato(codigoTime, nomeCampeonato) + "\n");
                break;
            default:
                System.out.println("Opção inválida" + "\n");
        }
    }

    /**
     * Exibe os campeonatos que um time participa
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void exibeCampeonatosDoTime(MrBetSistema mrBet, Scanner scanner) {
        System.out.print("Time: ");
        String codigo = scanner.nextLine();
        for (String cp : mrBet.buscaCampeonatosDoTime(codigo)) {
            System.out.println(cp);
        }
    }

    /**
     * Gerencia as funcionalidades de apostas do sistema.
     * Pode realizar uma nova aposta ou exibir todas as apostas feitas.
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    private static void gerenciaApostas(MrBetSistema mrBet, Scanner scanner) {
        System.out.print("(A) Apostar ou (S) Status das apostas? ");
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "A":
                System.out.print("Código: ");
                String codigoTime = scanner.nextLine();
                System.out.print("Campeonato: ");
                String nomeCampeonato = scanner.nextLine();
                System.out.print("Colocação: ");
                int colocacao = scanner.nextInt();
                System.out.print("Valor da aposta: R$");
                double valor = Double.parseDouble(scanner.nextLine());
                System.out.println(mrBet.adicionaAposta(codigoTime, nomeCampeonato, colocacao, valor));
                break;
            case "S":
                for (String s : mrBet.statusDasApostas()) {
                    System.out.println(s);
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    /**
     * Mostra informações registradas no sistema.
     * Exibe o time com mais campeonatos, os que ainda não estão em campoenatos
     * e os que mais aparecem em primeiro nas apostas.
     * @param mrBet controlador do sistema
     * @param scanner leitor das entradas do sistema
     */
    public static void mostraHistorico(MrBetSistema mrBet, Scanner scanner) {
        System.out.println("Participação mais frequente em campeonatos\n");
        for (String linha : mrBet.buscaTimesComMaisCampeonatos()) {
            if (linha != null) {
                System.out.println(linha + "\n");
            }
        }
        System.out.println("Ainda não participou de campeonato\n");
        for (String linha : mrBet.buscaTimesSemCampeonato() ) {
            if (linha != null) {
                System.out.println(linha + "\n");
            }
        }
        System.out.println("Popularidade em apostas\n");
        for (String linha : mrBet.buscaPopularidadeDosTimesEmApostas()) {
            if (linha != null) {
                System.out.println(linha + "\n");
            }
        }

    }

    private static void sai() {
        System.exit(0);
        System.out.println("Por hoje é só pessoal!");
    }

    /**
     * Lança uma exceção caso a entrada do usuario esteja vazia.
     * @param escolha entrada do usuario
     */
    private static void verificaEntradaVazia(String escolha) {
        if (escolha.isBlank()) { throw new IllegalArgumentException(); }
    }

    /**
     * Lança uma exceção caso a entrada do usuario seja nula.
     * @param escolha entrada do usuario
     */
    private static void verificaEntradaNula(String escolha) {
        if (escolha == null) { throw new NullPointerException(); }
    }

}
