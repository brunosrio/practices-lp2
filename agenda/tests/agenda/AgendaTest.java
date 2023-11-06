package agenda;

import agenda.Agenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;
    private Contato contaoBase;

    @BeforeEach
    void preparaAgenda() {
        this.agenda = new Agenda();
    }

    /*Testes para cadastro de contato*/
    @Test
    void testaCadastrarEmPosicaoVazia() {
        String msg = agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("CADASTRO REALIZADO", msg);
    }

    @Test
    void testaSubstituirContato() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        String msg = agenda.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111");
        assertEquals("CADASTRO REALIZADO", msg);
    }

    @Test
    void testaContatoJaExistente() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        String msg = agenda.cadastraContato(5, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("CONTATO JA CADASTRADO", msg);
    }

    @Test
    void testaPosicaoLimiteSuperior() {
        String msg = agenda.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("CADASTRO REALIZADO", msg);
    }

    @Test
    void testaPosicaoAcimaDoLimiteSuperior() {
        String msg = agenda.cadastraContato(101, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("POSIÇÃO INVÁLIDA!", msg);
    }

    @Test
    void testaPosicaoLimiteInferior() {
        String msg = agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("CADASTRO REALIZADO", msg);
    }

    @Test
    void testaPosicaoAbaixoDoLimiteInferior() {
        String msg = agenda.cadastraContato(0, "Matheus", "Gaudencio", "(83) 99999-0000");
        assertEquals("POSIÇÃO INVÁLIDA!", msg);
    }

    /* Testes de exibição do contato*/
    @Test
    void testaExibirContatoExistente() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        String saida = "Matheus Gaudencio\n(83) 99999-0000";
        assertEquals(saida, agenda.getContato(1));
    }

    @Test
    void testaExibirContatos() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.cadastraContato(2, "teste", "teste1", "0911-0111");
        String saida = "1 - Matheus Gaudencio\n2 - teste teste1";
        assertEquals(saida, agenda.getContatos());
    }

    @Test
    void testaExibirContaoPosicaoVazia() {
        String msg = agenda.getContato(1);
        assertEquals("POSIÇÃO SEM CONTATO CADASTRADO", msg);
    }

    @Test
    void testaExibirPosicaoInvalidaAbaixo() {
        String msg = agenda.getContato(0);
        assertEquals("POSIÇÃO INVÁLIDA", msg);
    }

    @Test
    void testaExibirPosicaoInvalidaAcima() {
        String msg = agenda.getContato(101);
        assertEquals("POSIÇÃO INVÁLIDA", msg);
    }

    /*Testes de Favoritos*/
    @Test
    void testaFavoritarContato() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        String msg = agenda.adicionaFavorito(1, 2);
        String saidaEsperada = "CONTATO FAVORITADO NA POSICAO 2";
        assertEquals(saidaEsperada, msg);
    }

    @Test
    void testaFavoritoDuplicado() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.adicionaFavorito(1, 2);
        String msg = agenda.adicionaFavorito(1, 4);
        String saidaEsperada = "CONTATO JÁ É FAVORITO";
        assertEquals(saidaEsperada, msg);
    }


    @Test
    void testaExibirContatoFavorito() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.adicionaFavorito(1, 1);
        String msg = agenda.getContato(1);
        String saida = "❤ Matheus Gaudencio\n(83) 99999-0000";
        assertEquals(saida, msg);
    }

    @Test
    void testaExibirContatoDesfavoritado() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.adicionaFavorito(1, 1);
        agenda.removeFavorito(1);
        String msg = agenda.getContato(1);
        String saida = "Matheus Gaudencio\n(83) 99999-0000";
        assertEquals(saida, msg);
    }

    @Test
    void testaExibirFavoritos() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.cadastraContato(4, "Monitor", "Me ajude", "0800");
        agenda.adicionaFavorito(1, 1);
        agenda.adicionaFavorito(4, 8);
        String saidaEsperada = "1 - Matheus Gaudencio\n8 - Monitor Me ajude";
        assertEquals(saidaEsperada, agenda.listaFavoritos());
    }

    @Test
    void testaRemoverFavorito() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.adicionaFavorito(1, 1);
        assertTrue(agenda.removeFavorito(1));
    }

    @Test
    void testaAlterarTelefone() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        boolean msg = agenda.alteraTelefone(1, "009911");
        assertTrue(msg);
    }

    @Test
    void testaAdicionaTag() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.cadastraContato(2, "teste", "teste1", "(83) 99999-0000");
        boolean msg = agenda.adicionaTags("1 2", "Trabalho");
        assertTrue(msg);
    }

    @Test
    void testaRemoverContato() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
        agenda.adicionaFavorito(1, 1);
        boolean msg = agenda.removeContato(1);

        assertTrue(msg);
    }

    @Test
    void testaBuscaPeloNome() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "00");
        agenda.cadastraContato(2, "Matheus", "teste1", "99");
        agenda.cadastraContato(3, "teste", "Gaudencio", "(83) 99999-0000");
        String saidaEsperada = "Matheus Gaudencio\n00\nMatheus teste1\n99\n";
        String msg = agenda.buscaContatoPeloNome("matheus");
        assertEquals(saidaEsperada, msg);
    }

    @Test
    void testaBuscaPeloSobrenome() {
        agenda.cadastraContato(1, "Matheus", "Gaudencio", "00");
        agenda.cadastraContato(2, "Matheus", "teste1", "99");
        agenda.cadastraContato(3, "teste", "Gaudencio", "11");
        String saidaEsperada = "Matheus Gaudencio\n00\nteste Gaudencio\n11\n";
        String msg = agenda.buscaContatoPeloSobrenome("gaudencio");
        assertEquals(saidaEsperada, msg);
    }
}
