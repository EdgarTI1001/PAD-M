package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbGestor")
public class Gestor {

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

	private String obs;

	@Column(name = "USERCRIADOR_ID")
	private Integer userCriadorId;

	private LocalDateTime datacriacao;

	private LocalDateTime dataremocao;

	private int seq;

	private int flag;

	private int placed;

	public Gestor() {

	}

	public Gestor(Integer id, Integer userId, Integer setorId, Integer servidorId, Integer sigiloId,
			String visibilidade, String obs, Integer userCriadorId, LocalDateTime datacriacao,
			LocalDateTime dataremocao, int seq, int flag, int placed) {
		this.id = id;
		this.userId = userId;
		this.setorId = setorId;
		this.servidorId = servidorId;
		this.sigiloId = sigiloId;
		this.visibilidade = visibilidade;
		this.obs = obs;
		this.userCriadorId = userCriadorId;
		this.datacriacao = datacriacao;
		this.dataremocao = dataremocao;
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getUserCriadorId() {
		return userCriadorId;
	}

	public void setUserCriadorId(Integer userCriadorId) {
		this.userCriadorId = userCriadorId;
	}

	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDateTime datacriacao) {
		this.datacriacao = datacriacao;
	}

	public LocalDateTime getDataremocao() {
		return dataremocao;
	}

	public void setDataremocao(LocalDateTime dataremocao) {
		this.dataremocao = dataremocao;
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
		Gestor other = (Gestor) obj;
		return Objects.equals(id, other.id);
	}

}
