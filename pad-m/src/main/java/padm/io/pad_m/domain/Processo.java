package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbprocesso")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer numproc;

	private String tipo;

	private Integer ano;

	private String numanoproc;

	private String assunto;

	@ManyToOne
	@JoinColumn(name = "docproc_id")
	private Doc documento;

	@ManyToOne
	@JoinColumn(name = "setcriador_id")
	private Setor setorcriadorId;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento eventoId;

	@ManyToOne
	@JoinColumn(name = "fase_id")
	private Fase faseId;

	@ManyToOne
	@JoinColumn(name = "interessado_id")
	private Interessado interessadoId;

	@ManyToOne
	@JoinColumn(name = "minuta_id")
	private Minuta minutaId;

	private int lembreteId;

	@ManyToOne
	@JoinColumn(name = "sigilo_id")
	private Sigilo sigiloId;

	@Column(name = "class_id")
	private int classId;

	private String classificacao;

	private int rotuloId;

	private Integer usucriadorId;

	private LocalDateTime datacriacao;

	private int procapensadoId;

	private int flagapensoId;

	private String motivoapenso;

	private int procapensoprincId;

	private String motivoapensoprinc;

	private int procanexado_id;

	private int flaganexado_id;

	private String motivoanexo;

	private int procanexoprincId;

	private int flaganexoprincId;

	private String motivoanexoprinc;

	private LocalDateTime data;

	private String obs;

	private int seq;

	private int flag;

	public Processo() {
	}

	public Processo(Integer id, Integer numproc, String tipo, Integer ano, String numanoproc, String assunto,
			Doc documento, Evento eventoId, int classId, String classificacao, Fase faseId, int rotuloId,
			Integer usucriadorId, Setor setorcriadorId, LocalDateTime datacriacao, Interessado interessadoId,
			Minuta minutaId, int lembreteId, Sigilo sigiloId, int procapensadoId, int flagapensoId, String motivoapenso,
			int procapensoprincId, String motivoapensoprinc, int procanexado_id, int flaganexado_id, String motivoanexo,
			int procanexoprincId, int flaganexoprincId, String motivoanexoprinc, LocalDateTime data, String obs,
			int seq, int flag) {
		this.id = id;
		this.numproc = numproc;
		this.tipo = tipo;
		this.ano = ano;
		this.numanoproc = numanoproc;
		this.assunto = assunto;
		this.documento = documento;
		this.eventoId = eventoId;
		this.classId = classId;
		this.classificacao = classificacao;
		this.faseId = faseId;
		this.rotuloId = rotuloId;
		this.usucriadorId = usucriadorId;
		this.setorcriadorId = setorcriadorId;
		this.datacriacao = datacriacao;
		this.interessadoId = interessadoId;
		this.minutaId = minutaId;
		this.lembreteId = lembreteId;
		this.sigiloId = sigiloId;
		this.procapensadoId = procapensadoId;
		this.flagapensoId = flagapensoId;
		this.motivoapenso = motivoapenso;
		this.procapensoprincId = procapensoprincId;
		this.motivoapensoprinc = motivoapensoprinc;
		this.procanexado_id = procanexado_id;
		this.flaganexado_id = flaganexado_id;
		this.motivoanexo = motivoanexo;
		this.procanexoprincId = procanexoprincId;
		this.flaganexoprincId = flaganexoprincId;
		this.motivoanexoprinc = motivoanexoprinc;
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

	public Integer getNumproc() {
		return numproc;
	}

	public void setNumproc(Integer numproc) {
		this.numproc = numproc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getNumanoproc() {
		return numanoproc;
	}

	public void setNumanoproc(String numanoproc) {
		this.numanoproc = numanoproc;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getRotuloId() {
		return rotuloId;
	}

	public void setRotuloId(int rotuloId) {
		this.rotuloId = rotuloId;
	}

	public Integer getUsucriadorId() {
		return usucriadorId;
	}

	public void setUsucriadorId(Integer usucriadorId) {
		this.usucriadorId = usucriadorId;
	}

	public Setor getSetorcriadorId() {
		return setorcriadorId;
	}

	public void setSetorcriadorId(Setor setorcriadorId) {
		this.setorcriadorId = setorcriadorId;
	}

	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDateTime datacriacao) {
		this.datacriacao = datacriacao;
	}

	public Interessado getInteressadoId() {
		return interessadoId;
	}

	public void setInteressadoId(Interessado interessadoId) {
		this.interessadoId = interessadoId;
	}

	public Minuta getMinutaId() {
		return minutaId;
	}

	public void setMinutaId(Minuta minutaId) {
		this.minutaId = minutaId;
	}

	public int getLembreteId() {
		return lembreteId;
	}

	public void setLembreteId(int lembreteId) {
		this.lembreteId = lembreteId;
	}

	public Sigilo getSigiloId() {
		return sigiloId;
	}

	public void setSigiloId(Sigilo sigiloId) {
		this.sigiloId = sigiloId;
	}

	public int getProcapensadoId() {
		return procapensadoId;
	}

	public void setProcapensadoId(int procapensadoId) {
		this.procapensadoId = procapensadoId;
	}

	public int getFlagapensoId() {
		return flagapensoId;
	}

	public void setFlagapensoId(int flagapensoId) {
		this.flagapensoId = flagapensoId;
	}

	public String getMotivoapenso() {
		return motivoapenso;
	}

	public void setMotivoapenso(String motivoapenso) {
		this.motivoapenso = motivoapenso;
	}

	public int getProcapensoprincId() {
		return procapensoprincId;
	}

	public void setProcapensoprincId(int procapensoprincId) {
		this.procapensoprincId = procapensoprincId;
	}

	public String getMotivoapensoprinc() {
		return motivoapensoprinc;
	}

	public void setMotivoapensoprinc(String motivoapensoprinc) {
		this.motivoapensoprinc = motivoapensoprinc;
	}

	public int getProcanexado_id() {
		return procanexado_id;
	}

	public void setProcanexado_id(int procanexado_id) {
		this.procanexado_id = procanexado_id;
	}

	public int getFlaganexado_id() {
		return flaganexado_id;
	}

	public void setFlaganexado_id(int flaganexado_id) {
		this.flaganexado_id = flaganexado_id;
	}

	public String getMotivoanexo() {
		return motivoanexo;
	}

	public void setMotivoanexo(String motivoanexo) {
		this.motivoanexo = motivoanexo;
	}

	public int getProcanexoprincId() {
		return procanexoprincId;
	}

	public void setProcanexoprincId(int procanexoprincId) {
		this.procanexoprincId = procanexoprincId;
	}

	public int getFlaganexoprincId() {
		return flaganexoprincId;
	}

	public void setFlaganexoprincId(int flaganexoprincId) {
		this.flaganexoprincId = flaganexoprincId;
	}

	public String getMotivoanexoprinc() {
		return motivoanexoprinc;
	}

	public void setMotivoanexoprinc(String motivoanexoprinc) {
		this.motivoanexoprinc = motivoanexoprinc;
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

	public Doc getDocumento() {
		return documento;
	}

	public void setDocumento(Doc documento) {
		this.documento = documento;
	}

	public Evento getEventoId() {
		return eventoId;
	}

	public void setEventoId(Evento eventoId) {
		this.eventoId = eventoId;
	}

	public Fase getFaseId() {
		return faseId;
	}

	public void setFaseId(Fase faseId) {
		this.faseId = faseId;
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

}
