package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbAtendente")
public class Atendente {

	@Id
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "SETOR_ID")
	private Integer setorId;

	@Column(name = "SERVIDOR_ID")
	private Integer servidorId;

	@Column(name = "SIGILO_ID")
	private Integer sigiloId;

	private String visibilidade;

	private LocalDateTime data;

	private String obs;

	@Column(name = "USERCRIADOR_ID")
	private Integer useCriadorId;

	@Column(name = "datacriacao")
	private LocalDateTime dataCriacao;

	@Column(name = "dataremocao")
	private LocalDateTime dataRemocao;

	private int seq;

	private int flag;

	private int placed;

	public Atendente() {

	}

	public Atendente(Integer id, Integer userId, Integer setorId, Integer servidorId, Integer sigiloId,
			String visibilidade, LocalDateTime data, String obs, Integer useCriadorId, LocalDateTime dataCriacao,
			LocalDateTime dataRemocao, int seq, int flag, int placed) {
		this.id = id;
		this.userId = userId;
		this.setorId = setorId;
		this.servidorId = servidorId;
		this.sigiloId = sigiloId;
		this.visibilidade = visibilidade;
		this.data = data;
		this.obs = obs;
		this.useCriadorId = useCriadorId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSetorId() {
		return setorId;
	}

	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public Integer getSigiloId() {
		return sigiloId;
	}

	public void setSigiloId(Integer sigiloId) {
		this.sigiloId = sigiloId;
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

	public Integer getUseCriadorId() {
		return useCriadorId;
	}

	public void setUseCriadorId(Integer useCriadorId) {
		this.useCriadorId = useCriadorId;
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
		Atendente other = (Atendente) obj;
		return Objects.equals(id, other.id);
	}

}
