package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Moderador {

	@Id
	private Integer id;

	@Column(name = "SETOR_ID")
	private Integer idSetor;

	@Column(name = "SERVIDOR_ID")
	private Integer idServidor;

	@Column(name = "SIGILO_ID")
	private Sigilo sigilo;

	private String visibilidade;

	private LocalDateTime data;

	private String obs;

	@Column(name = "USERCRIADOR_ID")
	private Integer idCriador;

	@Column(name = "DATACRIACAO")
	private LocalDateTime dataCriacao;

	@Column(name = "DATAREMOCAO")
	private LocalDateTime dataRemocao;

	private int seq;

	private int flag;

	private int placed;

	public Moderador() {
	}

	public Moderador(Integer id, Integer idSetor, Integer idServidor, Sigilo sigilo, String visibilidade,
			LocalDateTime data, String obs, Integer idCriador, LocalDateTime dataCriacao, LocalDateTime dataRemocao,
			int seq, int flag, int placed) {
		this.id = id;
		this.idSetor = idSetor;
		this.idServidor = idServidor;
		this.sigilo = sigilo;
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

	public Sigilo getSigilo() {
		return sigilo;
	}

	public void setSigilo(Sigilo sigilo) {
		this.sigilo = sigilo;
	}

	public String getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
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

	public Integer getIdCriador() {
		return idCriador;
	}

	public void setIdCriador(Integer idCriador) {
		this.idCriador = idCriador;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(LocalDateTime dataRemocao) {
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
		return "Moderador [id=" + id + ", idSetor=" + idSetor + ", idServidor=" + idServidor + ", sigilo=" + sigilo
				+ ", visibilidade=" + visibilidade + ", data=" + data + ", obs=" + obs + ", idCriador=" + idCriador
				+ ", dataCriacao=" + dataCriacao + ", dataRemocao=" + dataRemocao + ", seq=" + seq + ", flag=" + flag
				+ ", placed=" + placed + "]";
	}

}
