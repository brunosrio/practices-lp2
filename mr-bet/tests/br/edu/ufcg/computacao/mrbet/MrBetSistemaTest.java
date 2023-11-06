package br.edu.ufcg.computacao.mrbet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrBetSistemaTest {

    private MrBetSistema mrBet;
    @BeforeEach
    void preparaSistema() {
        this.mrBet = new MrBetSistema();
    }

    @BeforeEach
    void preparaTimes() {
        this.mrBet.cadastraTime("[250_PB]", "Nacional de Patos", "Canário");
        this.mrBet.cadastraTime("[252_PB]", "Sport Lagoa Seca", "Carneiro");
        this.mrBet.cadastraTime("[002_RJ]", "Clube de Regatas do Flamengo", "Urubu");
        this.mrBet.cadastraTime("[105_PB]", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
    }

    @Test
    void testaAdicionaCampeonato() {
        assertEquals("INCLUSÃO REALIZADA!", this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 20));
    }

    @Test
    void testaAdicionaCampeonatoExistente() {
        this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 20);
        assertEquals("CAMPEONATO JÁ EXISTE!", this.mrBet.adicionaCampeonato("brasileiRão SÉRIE A 2023", 19));
    }
    @Test
    void testaIncluirTimeNoCampeonato() {
        this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 10);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBet.incluiTimeNoCampeonato("[250_PB]", "Brasileirão série A 2023"));
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBet.incluiTimeNoCampeonato("[252_PB]", "Brasileirão série A 2023"));
    }

    @Test
    void testaIncluirTimeDuplicadoNoCampeonato() {
        this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 10);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBet.incluiTimeNoCampeonato("[250_PB]", "Brasileirão série A 2023"));
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBet.incluiTimeNoCampeonato("[252_PB]", "Brasileirão série A 2023"));
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBet.incluiTimeNoCampeonato("[252_PB]", "Brasileirão série A 2023"));
    }

    @Test
    void testaIncluirTimeInexistenteEmCampeonato() {
        this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 10);
        try {
            mrBet.incluiTimeNoCampeonato("[005_PB]", "Brasileirão série A 2023");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O TIME NÃO EXISTE!", e.getMessage());
        }
    }

    @Test
    void testaIncluirTimeEmCampeonatoInexistente() {
        try {
            this.mrBet.incluiTimeNoCampeonato("[252_PB]", "Brasileirão série D 2023");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O CAMPEONATO NÃO EXISTE!", e.getMessage());
        }
    }

    @Test
    void testaIncluirTimeEmCampeonatoCheio() {
        this.mrBet.adicionaCampeonato("Brasileirão série A 2023", 1);
        mrBet.incluiTimeNoCampeonato("[252_PB]", "Brasileirão série A 2023");
        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", mrBet.incluiTimeNoCampeonato("[250_PB]", "Brasileirão série A 2023"));
    }

    @Test
    void testaVerificacaoDeTimeEmCampeonato() {
        this.mrBet.adicionaCampeonato("Copa do Nordeste 2023", 10);
        mrBet.incluiTimeNoCampeonato("[250_PB]", "Copa do Nordeste 2023");
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", mrBet.verificaTimeNoCampeonato("[250_PB]","Copa do Nordeste 2023"));
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", mrBet.verificaTimeNoCampeonato("[252_PB]", "Copa do Nordeste 2023"));
    }

    @Test
    void testaVerificacaoTimeEmCapeonatoInexistente() {
        try {
            this.mrBet.verificaTimeNoCampeonato("[252_PB]", "Brasileirão Série D 2023");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O CAMPEONATO NÃO EXISTE!", e.getMessage());
        }
    }

    @Test
    void testaVerificacaoTimeInexistenteEmCapeonato() {
        this.mrBet.adicionaCampeonato("Copa do Nordeste 2023", 10);
        try {
            mrBet.verificaTimeNoCampeonato("[005_PB]", "Copa do Nordeste 2023");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O TIME NÃO EXISTE!", e.getMessage());
        }
    }

    @Test
    void testaStatusAposta() {
        this.mrBet.adicionaCampeonato("Copa do Nordeste 2023", 10);
        this.mrBet.adicionaCampeonato("Brasileirão 2023", 10);
        this.mrBet.adicionaAposta("[250_PB]", "Copa do Nordeste 2023", 2, 50.75);
        this.mrBet.adicionaAposta("[252_PB]", "Brasileirão 2023", 10, 10.25);
        System.out.println("\nApostas realizadas: ");
        for (String s : mrBet.statusDasApostas()) {
            System.out.println(s);
        }
    }

    @Test
    void testaMostraHistorico() {
        this.mrBet.adicionaCampeonato("Copa do Nordeste 2023", 10);
        this.mrBet.adicionaCampeonato("Brasileirão 2023", 10);
        this.mrBet.adicionaAposta("[250_PB]", "Copa do Nordeste 2023", 1, 50.75);
        this.mrBet.adicionaAposta("[252_PB]", "Brasileirão 2023", 1, 10.25);
        this.mrBet.incluiTimeNoCampeonato("[002_RJ]", "Brasileirão 2023");
        this.mrBet.incluiTimeNoCampeonato("[002_RJ]", "Copa do Nordeste 2023");
        this.mrBet.incluiTimeNoCampeonato("[250_PB]", "Brasileirão 2023");
        this.mrBet.incluiTimeNoCampeonato("[250_PB]", "Copa do Nordeste 2023");
        System.out.println("\nParticipação mais frequente em campeonatos");
        for (String linha : mrBet.buscaTimesComMaisCampeonatos()) {
            if (linha != null) {
                System.out.println(linha);
            }
        }
        System.out.println("\nAinda não participou de campeonato");
        for (String linha : mrBet.buscaTimesSemCampeonato()) {
            if (linha != null) {
                System.out.println(linha);
            }
        }
        System.out.println("\nPopularidade em apostas");
        for (String linha : mrBet.buscaPopularidadeDosTimesEmApostas()) {
            if (linha != null) {
                System.out.println(linha);
            }
        }
    }
}