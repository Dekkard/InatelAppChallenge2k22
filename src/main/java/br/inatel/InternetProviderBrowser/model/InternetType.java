package br.inatel.InternetProviderBrowser.model;

public enum InternetType {
	CABEADA(1), SATÉLITE(2), RÁDIO(3);
	
	public int valor;
	private InternetType(int valor) {
		this.valor = valor;
	}
}
