package br.inatel.InternetProviderBrowser.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public enum UF {
	RO("Rondônia"), AC("Acre"), AM("Amazonas"), RR("Roraima"), PA("Pará"), AP("Amapá"), TO("Tocantins"),
	MA("Maranhão"), PI("Piauí"), CE("Ceará"), RN("Rio Grande do Norte"), PB("Paraíba"), PE("Pernambuco"),
	AL("Alagoas"), SE("Sergipe"), BA("Bahia"), MG("Minas Gerais"), ES("Espírito Santo"), RJ("Rio de Janeiro"),
	SP("São Paulo"), PR("Paraná"), SC("Santa Catarina"), RS("Rio Grande do Sul"), MS("Mato Grosso do Sul"),
	MT("Mato Grosso"), GO("Goiás"), DF("Distrito Federal");

	private String estado;

	UF(String string) {
		estado = string;
	}

	public String getEstado() {
		return estado;
	}

	public static Map<Integer, UF> listUF(boolean printFirst) {
		int[] ind = { 1 };
		Map<Integer, UF> mapperUF = Arrays.stream(UF.values()).sorted(Comparator.comparing(String::valueOf))
				.collect(Collectors.toMap((uf) -> ind[0]++, (uf) -> uf));
		if (printFirst)
			mapperUF.forEach((i, uf) -> System.out.println(i + ": " + uf + " (" + uf.estado + ")"));
		return mapperUF;
	}
}