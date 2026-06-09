public class MenuProxyService implements ExibicaoMenu {
    private MenuRealService menuReal;
    private boolean cacheValido;

    public MenuProxyService() {
        this.cacheValido = false; 
    }

    @Override
    public void exibirMenuDigital() {
        System.out.println("[PROXY] Requisição de menu recebida.");
        
        if (menuReal == null) {
            System.out.println("[PROXY] Cache vazio. Instanciando serviço real pela primeira vez...");
            this.menuReal = new MenuRealService();
            this.cacheValido = true;
        } else {
            System.out.println("[PROXY] Utilizando dados em cache armazenados em memória.");
        }

        menuReal.exibirMenuDigital();
    }
}