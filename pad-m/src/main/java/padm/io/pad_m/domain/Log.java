package padm.io.pad_m.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
public class Log implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prox_logid")
	@SequenceGenerator(name = "prox_logid", sequenceName = "prox_logid", allocationSize = 1)
	private Long id;
	
	@Column(name = "id_usuario")
	private String usuario;

	private String tabela;

	private String acao;

	private String detalhe;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime data;

	private String ip;

	public Log() {
	}

	public Log(Long id, String usuario, String tabela, String acao, String datalhe, LocalDateTime data, String ip) {
		this.id = id;
		this.usuario = usuario;
		this.tabela = tabela;
		this.acao = acao;
		this.detalhe = datalhe;
		this.data = data;
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String datalhe) {
		this.detalhe = datalhe;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Log [usuario=" + usuario + ", tabela=" + tabela + ", acao=" + acao + ", datalhe=" + detalhe + ", data="
				+ data + ", ip=" + ip + "]";
	}

}