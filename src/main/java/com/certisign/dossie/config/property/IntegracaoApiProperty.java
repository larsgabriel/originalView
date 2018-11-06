package com.certisign.dossie.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integracao")
public class IntegracaoApiProperty {

	private final Contexto contexto = new Contexto();

	private String originPermitida = "http://localhost:8085";

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public static class Contexto {
		private String contextoPedido;

		public String getContextoPedido() {
			return contextoPedido;
		}

		public void setContextoPedido(String contextoPedido) {
			this.contextoPedido = contextoPedido;
		}
	}
}
