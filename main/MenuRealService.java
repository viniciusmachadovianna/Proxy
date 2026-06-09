public class MenuRealService implements ExibicaoMenu {

    public MenuRealService() {
        carregarImagensPesadas();
    }

    private void carregarImagensPesadas() {
        System.out.println("[DOWNLOAD] Baixando 50MB de imagens de hambúrgueres em 4K...");
    }

    @Override
    public void exibirMenuDigital() {
        System.out.println("[MENU REAL] Renderizando na tela: Big Burger - R$ 29,90 | Smash - R$ 19,90");
    }
}