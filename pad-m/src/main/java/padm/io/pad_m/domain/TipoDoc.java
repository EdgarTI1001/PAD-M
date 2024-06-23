package padm.io.pad_m.domain;

import java.util.Objects;

@Entity
public class TipoDoc {

	@Id
	private Integer id;

	private String tipo;
	private DateTime data;
	private String obs;
	private int seq;
	private int flag;

	public TipoDoc() {
	}

	public TipoDoc(Integer id, String tipo, DateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.tipo = tipo;
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

	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
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
		TipoDoc other = (TipoDoc) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TipoDoc [id=" + id + ", tipo=" + tipo + ", obs=" + obs + ", seq=" + seq + ", flag=" + flag + "]";
	}

}
