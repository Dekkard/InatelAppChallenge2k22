package br.inatel.InternetProviderBrowser.model;

public enum InstallStatus {
	EM_ANDAMENTO(1), SUCESSO(2), FALHA(3), CANCELADO(4);
	
	public int valor;
	private InstallStatus(int valor) {
		this.valor = valor;
	}
}
