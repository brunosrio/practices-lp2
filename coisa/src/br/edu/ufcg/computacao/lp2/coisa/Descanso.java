package br.edu.ufcg.computacao.lp2.coisa;

/**
 * Representação do modelo de descanso a ser seguido por cada aluno.
 * Cada aluno precisa ter pelo menos 26 horas de descanso por semana.
 *
 * @author Bruno Osório
 */
public class Descanso {

    private final int DESCANSO_IDEAL = 26;
    private int horasDescanso = 0;
    private int numeroSemanas = 0;

    /**
     * Inicializa o atributo que representa a quantidade de horas de descanso do aluno.
     * @param valor a quantidade de horas descansadas pelo aluno.
     */
    public void defineHorasDescanso(int valor) {
        this.horasDescanso = valor;
    }

    /**
     * Inicializa o atributo que representas o numero de semanas.
     * @param valor a quantidade de semanas infomada.
     */
    public void defineNumeroSemanas(int valor) {
        this.numeroSemanas = valor;
    }

    /**
     * Retorna o estado de descanso do aluno.
     * Retorna "descansado" caso o aluno tenha descansado pelo menos 26 horas por semana.
     * Retorna "cansado" caso tenha descansado menos de 26 horas por semana.
     * O estado "cansado" também é retornado caso não haja registro de horas.
     *
     * @return uma String com a palavra que representa o estado do aluno.
     */
    public String getStatusGeral() {
        if(this.horasDescanso == 0 || this.numeroSemanas == 0) {
            return "cansado";
        } else if(this.horasDescanso / this.numeroSemanas >= DESCANSO_IDEAL) {
            return "descansado";
        } else {
            return "cansado";
        }
    }
}
