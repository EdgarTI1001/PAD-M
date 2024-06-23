package padm.io.pad_m.domain;

import java.util.Objects;

@Entity
public class Processo {

	@Id
	private Integer id;

	private String tipo;

	private Long ano;

	@Column(name = "NUMANOPROC")
	private String numAnoProc;

	private Srting assunto;

	@Column(name = "DOCPROC_ID")
	private int idDocProc;

	@Column(name = "EVENTO_ID")
	private int idEvento;

	@Column(name = "CLASS_ID")
	private int idClass;

	private String classificacao;

	@Column(name = "FASE_ID")
	private int idFase;

	@Column(name = "ROTULO_ID")
	private int idRotulo;

	@Column(name = "USUCRIADOR_ID")
	private int idUsuCriador;

	@Column(name = "SETCRIADOR_ID")
	private int idSetCriador;

	private DateTime dataCriacao;

	@Column(name = "INTERESSADO_ID")
	private int idInteressado;

	@Column(name = "MINUTA_ID")
	private int idMinuta;

	@Column(name = "LEMBRETE_ID")
	private int idLembrete;

	@Column(name = "SIGILO_ID")
	private int idSigilo;

	@Column(name = "PROCAPENSADO_ID")
	private int idProcapensado;

	@Column(name = "FLAGAPENSO_ID")
	private int idFlagapenso;

	private String motivoApenso;

	@Column(name = "FLAGAPENSOPRINC_ID")
	private int idFlagapensoprinc;

	@Column(name = "MOTIVOAPENSOPRINC_ID")
	private String motivoApensoPrinc;

	@Column(name = "PROCANEXADO_ID")
	private int idProcanexado;

	@Column(name = "FLAGANEXADO_ID")
	private int idFlagAnexado;

	private String motivoAnexo;

	@Column(name = "PROCANEXOPRINC_ID")
	private int idProcAnexoPrinc;

	@Column(name = "FLAGANEXOPRINC_ID")
	private int idFlagAnexaPrinc;

	private String motivoAnexoPrinc;

	private DateTime data;

	private String obs;

	private int seq;

	private int flag;

	public Processo() {
	}

	public Processo(Integer id, String tipo, Long ano, String numAnoProc, Srting assunto, int idDocProc, int idEvento,
			int idClass, String classificacao, int idFase, int idRotulo, int idUsuCriador, int idSetCriador,
			DateTime dataCriacao, int idInteressado, int idMinuta, int idLembrete, int idSigilo, int idProcapensado,
			int idFlagapenso, String motivoApenso, int idFlagapensoprinc, String motivoApensoPrinc, int idProcanexado,
			int idFlagAnexado, String motivoAnexo, int idProcAnexoPrinc, int idFlagAnexaPrinc, String motivoAnexoPrinc,
			DateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.tipo = tipo;
		this.ano = ano;
		this.numAnoProc = numAnoProc;
		this.assunto = assunto;
		this.idDocProc = idDocProc;
		this.idEvento = idEvento;
		this.idClass = idClass;
		this.classificacao = classificacao;
		this.idFase = idFase;
		this.idRotulo = idRotulo;
		this.idUsuCriador = idUsuCriador;
		this.idSetCriador = idSetCriador;
		this.dataCriacao = dataCriacao;
		this.idInteressado = idInteressado;
		this.idMinuta = idMinuta;
		this.idLembrete = idLembrete;
		this.idSigilo = idSigilo;
		this.idProcapensado = idProcapensado;
		this.idFlagapenso = idFlagapenso;
		this.motivoApenso = motivoApenso;
		this.idFlagapensoprinc = idFlagapensoprinc;
		this.motivoApensoPrinc = motivoApensoPrinc;
		this.idProcanexado = idProcanexado;
		this.idFlagAnexado = idFlagAnexado;
		this.motivoAnexo = motivoAnexo;
		this.idProcAnexoPrinc = idProcAnexoPrinc;
		this.idFlagAnexaPrinc = idFlagAnexaPrinc;
		this.motivoAnexoPrinc = motivoAnexoPrinc;
		this.data = data;
		this.obs = obs;
		this.seq = seq;
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", tipo=" + tipo + ", ano=" + ano + ", numAnoProc=" + numAnoProc + ", idDocProc="
				+ idDocProc + ", idEvento=" + idEvento + ", idClass=" + idClass + ", classificacao=" + classificacao
				+ ", idFase=" + idFase + ", idRotulo=" + idRotulo + ", idUsuCriador=" + idUsuCriador + ", idSetCriador="
				+ idSetCriador + ", idInteressado=" + idInteressado + ", idMinuta=" + idMinuta + ", idLembrete="
				+ idLembrete + ", idSigilo=" + idSigilo + ", idProcapensado=" + idProcapensado + ", idFlagapenso="
				+ idFlagapenso + ", motivoApenso=" + motivoApenso + ", idFlagapensoprinc=" + idFlagapensoprinc
				+ ", motivoApensoPrinc=" + motivoApensoPrinc + ", idProcanexado=" + idProcanexado + ", idFlagAnexado="
				+ idFlagAnexado + ", motivoAnexo=" + motivoAnexo + ", idProcAnexoPrinc=" + idProcAnexoPrinc
				+ ", idFlagAnexaPrinc=" + idFlagAnexaPrinc + ", motivoAnexoPrinc=" + motivoAnexoPrinc + ", obs=" + obs
				+ ", seq=" + seq + ", flag=" + flag + "]";
	}

}
