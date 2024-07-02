package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbtipoevento")
public class TipoEvento {

	@Id
	private Integer id;

	private String evento;
	
	private LocalDateTime data;
	
	private String obs;
	
	private int seq;
	
	private int flag;

	public TipoEvento() {
	}

	public TipoEvento(Integer id, String evento, LocalDateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.evento = evento;
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

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
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
		TipoEvento other = (TipoEvento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TipoEvento [id=" + id + ", evento=" + evento + ", obs=" + obs + ", seq=" + seq + ", flag=" + flag + "]";
	}

}
