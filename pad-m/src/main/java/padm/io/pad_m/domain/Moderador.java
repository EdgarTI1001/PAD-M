package padm.io.pad_m.domain;

import java.util.Objects;

@Entity
public class Moderador {

	@Id
	private Integer id;

	@Column(name = "SETOR_ID")
	private Integer idSetor;

	@Column(name = "SERVIDOR_ID")
	private Integer idServidor;

	@Column(name = "SIGILO_ID")
	private Integer idSigilo;
	private String visibilidade;
	private DateTime data;
	private String obs;

	@Column(name = "USERCRIADOR_ID")
	private Integer idCriador;

	@Column(name = "DATACRIACAO")
	private DateTime dataCriacao;
	@Column(name = "DATAREMOCAO")
	private DataeTime dataRemocao;
	private int seq;
	private int flag;
	private int placed;

	public Moderador() {
	}

	public Moderador(Integer id, Integer idSetor, Integer idServidor, Integer idSigilo, String visibilidade,
			DateTime data, String obs, Integer idCriador, DateTime dataCriacao, DataeTime dataRemocao, int seq,
			int flag, int placed) {
		this.id = id;
		this.idSetor = idSetor;
		this.idServidor = idServidor;
		this.idSigilo = idSigilo;
		this.visibilidade = visibilidade;
		this.data = data;
		this.obs = obs;
		this.idCriador = idCriador;
		this.dataCriacao = dataCriacao;
		this.dataRemocao = dataRemocao;
		this.seq = seq;
		this.flag = flag;
		this.placed = placed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public Integer getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(Integer idServidor) {
		this.idServidor = idServidor;
	}

	public Integer getIdSigilo() {
		return idSigilo;
	}

	public void setIdSigilo(Integer idSigilo) {
		this.idSigilo = idSigilo;
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

	public Integer getIdCriador() {
		return idCriador;
	}

	public void setIdCriador(Integer idCriador) {
		this.idCriador = idCriador;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public DataeTime getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(DataeTime dataRemocao) {
		this.dataRemocao = dataRemocao;
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

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
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
		Moderador other = (Moderador) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Moderador [id=" + id + ", idSetor=" + idSetor + ", idServidor=" + idServidor + ", idSigilo=" + idSigilo
				+ ", visibilidade=" + visibilidade + ", obs=" + obs + ", idCriador=" + idCriador + ", seq=" + seq
				+ ", flag=" + flag + ", placed=" + placed + "]";
	}

}
