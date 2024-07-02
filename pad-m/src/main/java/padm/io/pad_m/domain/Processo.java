package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbprocesso")
public class Processo {

	@Id
	private Integer id;

	private Integer numproc;

	private String tipo;

	private Long ano;

	private String numanoproc;

	private String assunto;

	private int docprocId;

	@Column(name = "evento_id")
	private int eventoId;

	@Column(name = "class_id")
	private int classId;

	private String classificacao;

	@Column(name = "fase_id")
	private int faseId;

	private int rotuloId;

	private int usucriadorId;

	@Column(name = "setorcriador_id")
	private int setorcriadorId;

	private LocalDateTime datacriacao;

	@Column(name = "interessado_id")
	private Interessado interessadoId;

	@Column(name = "minuta_id")
	private Minuta minutaId;
	
	private int lembreteId;

	@Column(name = "sigilo_id")
	private Sigilo sigiloId;

	private int procapensadoId;

	private int flagapensoId;

	private String motivoapenso;

	private int procapensoprincId;

	private String motivoapensoprinc;

	private int procoanexadoId;

	private int flaganexadoId;

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

	public Processo(Integer id, Integer numproc, String tipo, Long ano, String numanoproc, String assunto,
			int docprocId, int eventoId, int classId, String classificacao, int faseId, int rotuloId, int usucriadorId,
			int setorcriadorId, LocalDateTime datacriacao, Interessado interessadoId, Minuta minutaId, int lembreteId,
			Sigilo sigiloId, int procapensadoId, int flagapensoId, String motivoapenso, int procapensoprincId,
			String motivoapensoprinc, int procoanexadoId, int flaganexadoId, String motivoanexo, int procanexoprincId,
			int flaganexoprincId, String motivoanexoprinc, LocalDateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.numproc = numproc;
		this.tipo = tipo;
		this.ano = ano;
		this.numanoproc = numanoproc;
		this.assunto = assunto;
		this.docprocId = docprocId;
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
		this.procoanexadoId = procoanexadoId;
		this.flaganexadoId = flaganexadoId;
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

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
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

	public int getDocprocId() {
		return docprocId;
	}

	public void setDocprocId(int docprocId) {
		this.docprocId = docprocId;
	}

	public int getEventoId() {
		return eventoId;
	}

	public void setEventoId(int eventoId) {
		this.eventoId = eventoId;
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

	public int getFaseId() {
		return faseId;
	}

	public void setFaseId(int faseId) {
		this.faseId = faseId;
	}

	public int getRotuloId() {
		return rotuloId;
	}

	public void setRotuloId(int rotuloId) {
		this.rotuloId = rotuloId;
	}

	public int getUsucriadorId() {
		return usucriadorId;
	}

	public void setUsucriadorId(int usucriadorId) {
		this.usucriadorId = usucriadorId;
	}

	public int getSetorcriadorId() {
		return setorcriadorId;
	}

	public void setSetorcriadorId(int setorcriadorId) {
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

	public int getProcoanexadoId() {
		return procoanexadoId;
	}

	public void setProcoanexadoId(int procoanexadoId) {
		this.procoanexadoId = procoanexadoId;
	}

	public int getFlaganexadoId() {
		return flaganexadoId;
	}

	public void setFlaganexadoId(int flaganexadoId) {
		this.flaganexadoId = flaganexadoId;
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
		return "Processo [id=" + id + ", numproc=" + numproc + ", tipo=" + tipo + ", ano=" + ano + ", numanoproc="
				+ numanoproc + ", assunto=" + assunto + ", docprocId=" + docprocId + ", eventoId=" + eventoId
				+ ", classId=" + classId + ", classificacao=" + classificacao + ", faseId=" + faseId + ", rotuloId="
				+ rotuloId + ", usucriadorId=" + usucriadorId + ", setorcriadorId=" + setorcriadorId + ", datacriacao="
				+ datacriacao + ", interessadoId=" + interessadoId + ", minutaId=" + minutaId + ", lembreteId="
				+ lembreteId + ", sigiloId=" + sigiloId + ", procapensadoId=" + procapensadoId + ", flagapensoId="
				+ flagapensoId + ", motivoapenso=" + motivoapenso + ", procapensoprincId=" + procapensoprincId
				+ ", motivoapensoprinc=" + motivoapensoprinc + ", procoanexadoId=" + procoanexadoId + ", flaganexadoId="
				+ flaganexadoId + ", motivoanexo=" + motivoanexo + ", procanexoprincId=" + procanexoprincId
				+ ", flaganexoprincId=" + flaganexoprincId + ", motivoanexoprinc=" + motivoanexoprinc + ", data=" + data
				+ ", obs=" + obs + ", seq=" + seq + ", flag=" + flag + "]";
	}

}
