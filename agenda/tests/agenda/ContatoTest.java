package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContatoTest {

    private Contato contatoBase;

    @BeforeEach
    void preparaContato() {
        this.contatoBase = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
    }

    /*Testes do Construtor*/
    @Test
    void testaTelefoneVazio() {
        try {
            Contato novo = new Contato("Matheus", "Gaudencio", " ");
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    void testaNomeVazio() {
        try {
            Contato novo = new Contato(" ", "Gaudencio", "(83) 99999-0000");
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    void testaNomeNulo() {
        try {
            Contato novo = new Contato(null, "Gaudencio", "(83) 99999-0000");
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    void testaTelefoneNulo() {
        try {
            Contato novo = new Contato("Matheus","Gaudencio", null);
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    void testaSobrenomeNulo() {
        try {
            Contato novo = new Contato("Matheus", null, "(83) 99999-0000");
            fail();
        } catch (NullPointerException e) {}
    }

    /*Testes toString*/

    @Test
    void testaToString() {
        Contato novo = this.contatoBase;
        String saidaEsperada = "Matheus Gaudencio\n(83) 99999-0000";
        assertEquals(saidaEsperada, novo.toString());
    }

    @Test
    void testaToStringSemSobrenome() {
        Contato novo = new Contato("Matheus", "", "(83) 99999-0000");
        String saidaEsperada = "Matheus\n(83) 99999-0000";
        assertEquals(saidaEsperada, novo.toString());
    }

    @Test
    void testaToStringFavorito() {
        Contato novo = this.contatoBase;
        novo.favoritaContato();
        String saidaEsperada = "❤ Matheus Gaudencio\n(83) 99999-0000";
        assertEquals(saidaEsperada, novo.toString());
    }

    /*Teste dos acessadores*/
    @Test
    void testaGetNome() {
        assertEquals("Matheus", this.contatoBase.getNome());
    }

    @Test
    void testaGetSobrenome() {
        assertEquals("Gaudencio", this.contatoBase.getSobrenome());
    }

    @Test
    void testaGetTelefone() {
        assertEquals("(83) 99999-0000", this.contatoBase.getTelefone());
    }

    @Test
    void testaGetNomeCompleto() {
        assertEquals("Matheus Gaudencio", this.contatoBase.getNomeCompleto());
    }

    @Test
    void testaGetNomeCompletoSemSobrenome() {
        Contato novo = new Contato("Matheus", "", "(83) 99999-0000");
        assertEquals("Matheus", novo.getNomeCompleto());
    }

    /*Testa modificadores de atributos*/
    @Test
    void testaFavoritarContato() {
        this.contatoBase.favoritaContato();
        assertTrue(contatoBase.getEhFavorito());
    }

    @Test
    void testaDesfavoritarContato() {
        this.contatoBase.favoritaContato();
        this.contatoBase.desfavoritaContato();
        assertFalse(contatoBase.getEhFavorito());
    }




    /*Testes do Equals*/
    @Test
    void testaContatosIguais() {
        Contato c1 = new Contato("Livia", "Campos", "2101-9999");
        Contato c2 = new Contato("Livia", "Campos", "9973-2999");
        assertTrue(c1.equals(c2));
    }

    @Test
    void testaContatosDiferentes() {
        Contato c1 = new Contato("Livia", "Campos", "2101-9999");
        Contato c2 = new Contato("Livia", "Teste", "9973-2999");
        assertFalse(c1.equals(c2));
    }

    /*testes do bônus*/

    @Test
    void testaAlterarTelefone() {
        this.contatoBase.alteraTelefone("0000");
        assertEquals("0000", this.contatoBase.getTelefone());
    }

    @Test
    void testaAdicionarTag() {
        this.contatoBase.adicionaTag("Importante");
    }


}
