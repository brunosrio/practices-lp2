package br.edu.ufcg.computacao.lp2.coisa;

/**
 * Representação de um modelo para registro de tempo online para cada disciplina do aluno.
 * Cada disciplina tem um número de horas, e o aluno deve dedicar o dobro desse tempo online.
 * Por padrão cada disciplina tem 60 horas.
 *
 * @author Bruno Osório
*/
public class RegistroTempoOnline {
    private String nomeDisciplina;
    private int tempoOnlineEsperado = 120;
    private int tempoDedicadoOnline = 0;

    /**
     * Constrói o registro de uma disciplina a partir apenas do seu nome, o tempo online
     * esperado é o padrão.
     *
     * @param nomeDisciplina nome da disciplina informado
     **/
    public RegistroTempoOnline(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    /**
     *Constrói o registro de uma discplina a partir do seu nome e o tempo dedicado online esperado.
     *
     * @param nomeDisciplina nome da disciplina informado.
     * @param tempoOnlineEsperado tempo online esperado que o aluno dedique para a disciplina.
     **/
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineEsperado = tempoOnlineEsperado;
    }

    /**
     * Adiciona o tempo que o aluno dedicou online para a disciplina em horas.
     *
     * @param tempo tempo que o aluno dedicou online.
     **/
    public void adicionaTempoOnline(int tempo) {
        this.tempoDedicadoOnline += tempo;
    }

    /**
     * Verifica se o aluno atingiu o número de horas online esperado para a disciplina.
     *
     * @return true caso tenha atingido ou false para caso não tenha.
     * */
    public boolean atingiuMetaTempoOnline() {
        return this.tempoDedicadoOnline >= tempoOnlineEsperado;
    }

    /**
     * Retorna a String que representa o registro de tempo
     * dessa disciplina. A representção segue o formato:
     * "NOME_DA_DISCIPLINA - TEMPO_ONLINE / TEMPO_ONLINE_ESPERADO".
     *
     * @return a represetação em String do registro da disciplina.
     * */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append(this.nomeDisciplina).append(" ").append(this.tempoDedicadoOnline);
        out.append("/").append(this.tempoOnlineEsperado);
        return out.toString();
    }
}
