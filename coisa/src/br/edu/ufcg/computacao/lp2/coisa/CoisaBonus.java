package br.edu.ufcg.computacao.lp2.coisa;

public class CoisaBonus {
    public static void main(String[] args) {
        controlarDisciplina();
        System.out.println("------");
        pesquisarResumos();
    }

    public static void controlarDisciplina() {
        int[] pesos = {2, 3, 5};
        Disciplina fmcc = new Disciplina("Terror do período", 3, pesos);
        fmcc.cadastraNota(1, 4.5);
        fmcc.cadastraNota(2, 6.5);
        fmcc.cadastraNota(3, 7.5);
        System.out.println(fmcc.calculaMedia());
        System.out.println(fmcc.aprovado());
    }

    public static void pesquisarResumos() {
        RegistroResumos registro = new RegistroResumos(100);
        registro.adiciona("Tipo", "Identifica a semântica (operações e significados) de um conjunto de dados.");
        registro.adiciona("Classes", "Classes definem um tipo e a base de código para criação de objetos.");
        String[] busca = registro.busca("IDENTIFICA");
        for (String l : busca) {
            System.out.println(l);
        }
        System.out.println("------");

        String[] busca2 = registro.busca("UM");
        for (String l : busca2) {
            System.out.println(l);
        }
        System.out.println("------");
    }
}


