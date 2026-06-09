package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuProxyServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void deveCarregarServicoRealApenasNaPrimeiraChamadaEReutilizarCacheNasSeguintes() {
        ExibicaoMenu proxy = new MenuProxyService();

        proxy.exibirMenuDigital();
        String primeiroOutput = outputStreamCaptor.toString();
        outputStreamCaptor.reset();

        assertTrue(primeiroOutput.contains("[PROXY] Cache vazio. Instanciando serviço real pela primeira vez..."),
            "O Proxy deveria identificar que o objeto real ainda não havia sido criado.");
        assertTrue(primeiroOutput.contains("[DOWNLOAD] Baixando 50MB de imagens"),
            "O construtor do serviço real deveria ter sido disparado.");
        assertTrue(primeiroOutput.contains("[MENU REAL] Renderizando na tela"),
            "O método real finalizado deve ser executado pelo Proxy.");

        proxy.exibirMenuDigital();
        String segundoOutput = outputStreamCaptor.toString();

        assertTrue(segundoOutput.contains("[PROXY] Utilizando dados em cache armazenados em memória."),
            "O Proxy deveria utilizar a instância existente sem recriar o objeto real.");
        assertFalse(segundoOutput.contains("[DOWNLOAD] Baixando 50MB de imagens"),
            "O serviço real não deveria ser instanciado novamente.");
        assertTrue(segundoOutput.contains("[MENU REAL] Renderizando na tela"),
            "A delegação final para exibição deve continuar funcionando via cache.");
    }
}
