package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbMinuta")
public class Minuta {

	@Id
	private Integer id;

	@Column(name = "PROC_ID")
	private Integer procId;

	@Column(name = "USU_ID")
	private Integer usuId;

	@Column(name = "SET_ID")
	private Integer setorId;

	@Column(name = "NOMDOC")
	private String nomeDoc;

	@Column(name = "EXTDOC")
	private String extDoc;

	@Column(name = "TIPODOC")
	private String tipoDoc;

	@Column(name = "TAMDOC")
	private String tamDoc;

	private LocalDateTime data;

	private String obs;

	private int seq;

	private int flag;

	public Minuta() {

	}

	public Minuta(Integer id, Integer procId, Integer usuId, Integer setorId, String nomeDoc, String extDoc,
			String tipoDoc, String tamDoc, LocalDateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.procId = procId;
		this.usuId = usuId;
		this.setorId = setorId;
		this.nomeDoc = nomeDoc;
		this.extDoc = extDoc;
		this.tipoDoc = tipoDoc;
		this.tamDoc = tamDoc;
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

	public Integer getProcId() {
		return procId;
	}

	public void setProcId(Integer procId) {
		this.procId = procId;
	}

	public Integer getUsuId() {
		return usuId;
	}

	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	public Integer getSetorId() {
		return setorId;
	}

	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}

	public String getNomeDoc() {
		return nomeDoc;
	}

	public void setNomeDoc(String nomeDoc) {
		this.nomeDoc = nomeDoc;
	}

	public String getExtDoc() {
		return extDoc;
	}

	public void setExtDoc(String extDoc) {
		this.extDoc = extDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTamDoc() {
		return tamDoc;
	}

	public void setTamDoc(String tamDoc) {
		this.tamDoc = tamDoc;
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
		Minuta other = (Minuta) obj;
		return Objects.equals(id, other.id);
	}

}
