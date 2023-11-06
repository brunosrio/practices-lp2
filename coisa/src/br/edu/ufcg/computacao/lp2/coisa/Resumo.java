package br.edu.ufcg.computacao.lp2.coisa;

/**
 * Representação de um resumo.
 * Todo resumo possui tema e conteúdo.
 *
 * @author Bruno Osório
 **/
public class Resumo {
    private String conteudo;
    private String tema;

    /**
     * Constrói um resumo a partir do seu tema e conteúdo.
     *
     * @param tema o tema do resumo.
     * @param conteudo o conteúdo do resumo.
     * */
    public Resumo(String tema, String conteudo) {
        this.tema = tema;
        this.conteudo = conteudo;
    }

    public String getTema(){
        return this.tema;
    }

    /**
     * Retorna a String que representa um resumo. A representação
     * segue o formato "TEMA: CONTEUDO".
     *
     * @return a representação em String de um resumo.
     * */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append(tema).append(": ").append(conteudo);
        return out.toString();
    }

}
