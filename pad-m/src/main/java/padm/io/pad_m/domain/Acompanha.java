package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbAcompanha")
public class Acompanha {

	@Id
	private Integer id;

	@Column(name = "PROC_ID")
	private Integer procId;

	@Column(name = "USU_ID")
	private int usuId;

	private String interessado;

	private LocalDateTime data;

	private String obs;

	private int seq;

	private int flag;

	public Acompanha() {

	}

	public Acompanha(Integer id, Integer procId, int usuId, String interessado, LocalDateTime data, String obs, int seq,
			int flag) {
		this.id = id;
		this.procId = procId;
		this.usuId = usuId;
		this.interessado = interessado;
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

	public int getUsuId() {
		return usuId;
	}

	public void setUsuId(int usuId) {
		this.usuId = usuId;
	}

	public String getInteressado() {
		return interessado;
	}

	public void setInteressado(String interessado) {
		this.interessado = interessado;
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
		Acompanha other = (Acompanha) obj;
		return Objects.equals(id, other.id);
	}

}
