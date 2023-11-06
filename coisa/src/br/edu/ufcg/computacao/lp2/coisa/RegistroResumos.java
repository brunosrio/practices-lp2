package br.edu.ufcg.computacao.lp2.coisa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representação do registro de resumos para cada aluno. O aluno faz o acompanhamento dos
 * estudos podendo adicionar mais resumos, visualizá-los ou fazer pesquisas.
 *
 * @author Bruno Osório
 * */
public class RegistroResumos {
    private Resumo[] resumos;
    private int numeroDeResumos = 0;
    private int contaResumos = 0;

    /**
     * Constrói um registro de resumos a partir do numero de resumos.
     * @param numeroDeResumos número de resumos máximo que o aluno pode registrar.
     * */
    public RegistroResumos(int numeroDeResumos) {
        this.numeroDeResumos = numeroDeResumos;
        this.resumos = new Resumo[numeroDeResumos];
    }

    /**
     * Adiciona um novo resumo no array de resumos.
     * Caso o limite seja atingido, o próximo resumo é adicionado na primeira posição e a sequência segue de forma
     * circular, sobrescrevendo as posições inicias.
     *
     * @param tema tema do resumo.
     * @param conteudo conteúdo do resumo.
     * */
    public void adiciona(String tema, String conteudo) {
        Resumo novo = new Resumo(tema, conteudo);
        if (contaResumos < numeroDeResumos) {
            this.resumos[contaResumos] = novo;
        } else {
            int novaPosicao = contaResumos % numeroDeResumos;
            this.resumos[novaPosicao] = novo;
        }
        contaResumos++;
    }

    /**
     * Retorna um array de strings com os resumos, cada indíce do array possui o tema e o conteúdo do resumo.
     *
     * @return um array de strings com os resumos.
     * */
    public String[] pegaResumos() {
        String[] stringResumos = new String[conta()];
        for (int i=0; i < conta(); i++) {
            stringResumos[i] = this.resumos[i].toString();
        }
        return stringResumos;
    }

    /**
     * Cria uma string com informações sobre o registro de resumos.
     * Informado a quantidade de resumos lidos e o tema de cada resumo cadastrado.
     *
     * @return a string para ser mostrada na saida do programa.
     * */
    public String imprimeResumos() {
        StringBuffer out = new StringBuffer();
        out.append("- ").append(conta()).append(" resumo(s) cadastrados(s)\n");
        out.append("- ").append(resumos[0].getTema());
        for (int i=1; i < resumos.length; i++) {
            if (resumos[i] != null) {
                out.append(" | ").append(resumos[i].getTema());
            }
        }
        return out.toString();
    }

    /**
     * Retorna a quantidade de resumos adicionados.
     * Caso o array de resumos esteja cheio, sempre será retornado o número máximo de resumos.
     *
     * @return um inteiro que representa a quantidade de resumos.
     * */
    public int conta() {
        return Math.min(this.contaResumos, this.numeroDeResumos);
    }

    /**
     * Verifica se existe um resumo no array de resumos a partir do seu tema.
     *
     * @param tema o tema a ser pesquisado.
     * @return um booleano que representa a resposta da pesquisa do tema.
     * */
    public boolean temResumo(String tema) {
        boolean existeResumo = false;
        for (int i = 0; i < resumos.length; i++) {
            if (resumos[i] != null && resumos[i].getTema().equals(tema)) {
                existeResumo = true;
            }
        }
        return existeResumo;
    }

    /**
     * Realiza a busca por um resumo que contenha a palavra chave informada.
     * A busca ignora maiúsculas e minúsculas. Caso a palavra chave esteja em um ou mais resumos,
     * será retornado um array em ordem alfabética com o tema dos resumos encontrados.
     *
     * @param chaveDeBusca palavra chave informada.
     * @return array de strings com os temas.
     */
    public String[] busca(String chaveDeBusca) {
        ArrayList<String> lista = new ArrayList<>();
        String[] meusResumos = pegaResumos();

        for (int i=0; i < conta(); i++) {
            String[] elementos = meusResumos[i].split(" ");
            for (int j=0; j<elementos.length; j++){
                if (elementos[j].equalsIgnoreCase(chaveDeBusca)) {
                   lista.add(this.resumos[i].getTema());
                }
            }
        }

        String[] listaArray = lista.toArray(new String[lista.size()]);
        Arrays.sort(listaArray);
        return listaArray;
    }

}
