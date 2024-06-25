package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbProcesso")
public class Processo {

	@Id
	private Integer id;

	private String tipo;

	private Long ano;

	@Column(name = "NUMANOPROC")
	private String numAnoProc;

	private String assunto;

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

	private LocalDateTime dataCriacao;

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

	private LocalDateTime data;

	private String obs;

	private int seq;

	private int flag;

	public Processo() {
	}

	public Processo(Integer id, String tipo, Long ano, String numAnoProc, String assunto, int idDocProc, int idEvento,
			int idClass, String classificacao, int idFase, int idRotulo, int idUsuCriador, int idSetCriador,
			LocalDateTime dataCriacao, int idInteressado, int idMinuta, int idLembrete, int idSigilo,
			int idProcapensado, int idFlagapenso, String motivoApenso, int idFlagapensoprinc, String motivoApensoPrinc,
			int idProcanexado, int idFlagAnexado, String motivoAnexo, int idProcAnexoPrinc, int idFlagAnexaPrinc,
			String motivoAnexoPrinc, LocalDateTime data, String obs, int seq, int flag) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public String getNumAnoProc() {
		return numAnoProc;
	}

	public void setNumAnoProc(String numAnoProc) {
		this.numAnoProc = numAnoProc;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public int getIdDocProc() {
		return idDocProc;
	}

	public void setIdDocProc(int idDocProc) {
		this.idDocProc = idDocProc;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getIdFase() {
		return idFase;
	}

	public void setIdFase(int idFase) {
		this.idFase = idFase;
	}

	public int getIdRotulo() {
		return idRotulo;
	}

	public void setIdRotulo(int idRotulo) {
		this.idRotulo = idRotulo;
	}

	public int getIdUsuCriador() {
		return idUsuCriador;
	}

	public void setIdUsuCriador(int idUsuCriador) {
		this.idUsuCriador = idUsuCriador;
	}

	public int getIdSetCriador() {
		return idSetCriador;
	}

	public void setIdSetCriador(int idSetCriador) {
		this.idSetCriador = idSetCriador;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getIdInteressado() {
		return idInteressado;
	}

	public void setIdInteressado(int idInteressado) {
		this.idInteressado = idInteressado;
	}

	public int getIdMinuta() {
		return idMinuta;
	}

	public void setIdMinuta(int idMinuta) {
		this.idMinuta = idMinuta;
	}

	public int getIdLembrete() {
		return idLembrete;
	}

	public void setIdLembrete(int idLembrete) {
		this.idLembrete = idLembrete;
	}

	public int getIdSigilo() {
		return idSigilo;
	}

	public void setIdSigilo(int idSigilo) {
		this.idSigilo = idSigilo;
	}

	public int getIdProcapensado() {
		return idProcapensado;
	}

	public void setIdProcapensado(int idProcapensado) {
		this.idProcapensado = idProcapensado;
	}

	public int getIdFlagapenso() {
		return idFlagapenso;
	}

	public void setIdFlagapenso(int idFlagapenso) {
		this.idFlagapenso = idFlagapenso;
	}

	public String getMotivoApenso() {
		return motivoApenso;
	}

	public void setMotivoApenso(String motivoApenso) {
		this.motivoApenso = motivoApenso;
	}

	public int getIdFlagapensoprinc() {
		return idFlagapensoprinc;
	}

	public void setIdFlagapensoprinc(int idFlagapensoprinc) {
		this.idFlagapensoprinc = idFlagapensoprinc;
	}

	public String getMotivoApensoPrinc() {
		return motivoApensoPrinc;
	}

	public void setMotivoApensoPrinc(String motivoApensoPrinc) {
		this.motivoApensoPrinc = motivoApensoPrinc;
	}

	public int getIdProcanexado() {
		return idProcanexado;
	}

	public void setIdProcanexado(int idProcanexado) {
		this.idProcanexado = idProcanexado;
	}

	public int getIdFlagAnexado() {
		return idFlagAnexado;
	}

	public void setIdFlagAnexado(int idFlagAnexado) {
		this.idFlagAnexado = idFlagAnexado;
	}

	public String getMotivoAnexo() {
		return motivoAnexo;
	}

	public void setMotivoAnexo(String motivoAnexo) {
		this.motivoAnexo = motivoAnexo;
	}

	public int getIdProcAnexoPrinc() {
		return idProcAnexoPrinc;
	}

	public void setIdProcAnexoPrinc(int idProcAnexoPrinc) {
		this.idProcAnexoPrinc = idProcAnexoPrinc;
	}

	public int getIdFlagAnexaPrinc() {
		return idFlagAnexaPrinc;
	}

	public void setIdFlagAnexaPrinc(int idFlagAnexaPrinc) {
		this.idFlagAnexaPrinc = idFlagAnexaPrinc;
	}

	public String getMotivoAnexoPrinc() {
		return motivoAnexoPrinc;
	}

	public void setMotivoAnexoPrinc(String motivoAnexoPrinc) {
		this.motivoAnexoPrinc = motivoAnexoPrinc;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
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
