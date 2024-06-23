package padm.io.pad_m.domain;

import java.util.Objects;

@Entity
public class Sigilo {

	@Id
	private Integer id;

	private String visibilidade;
	private DateTime data;
	private String obs;
	private int seq;
	private int flag;

	public Sigilo() {
	}

	public Sigilo(Integer id, String visibilidade, DateTime data, String obs, int seq, int flag) {
		this.id = id;
		this.visibilidade = visibilidade;
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

	public String getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
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
		Sigilo other = (Sigilo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sigilo [id=" + id + ", visibilidade=" + visibilidade + ", obs=" + obs + ", seq=" + seq + ", flag="
				+ flag + "]";
	}

}
