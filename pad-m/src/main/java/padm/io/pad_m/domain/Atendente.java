package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbatendente")
public class Atendente {

	@Id
	private Integer id;

	@Column(name = "USER_ID")
	private Usuario usuario;

	@Column(name = "SETOR_ID")
	private Setor setor;

	@Column(name = "SERVIDOR_ID")
	private Servidor servidor;

	@Column(name = "SIGILO_ID")
	private Sigilo sigilo;

	private String visibilidade;

	private LocalDateTime data;

	private String obs;

	@Column(name = "USERCRIADOR_ID")
	private Usuario usecriadorid;

	@Column(name = "datacriacao")
	private LocalDateTime datacriacao;

	@Column(name = "dataremocao")
	private LocalDateTime dataremocao;

	private int seq;

	private int flag;

	private int placed;

	public Atendente() {

	}

	public Atendente(Integer id, Usuario usuario, Setor setor, Servidor servidor, Sigilo sigilo, String visibilidade,
			LocalDateTime data, String obs, Usuario usecriadorid, LocalDateTime datacriacao, LocalDateTime dataremocao,
			int seq, int flag, int placed) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.setor = setor;
		this.servidor = servidor;
		this.sigilo = sigilo;
		this.visibilidade = visibilidade;
		this.data = data;
		this.obs = obs;
		this.usecriadorid = usecriadorid;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
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

	public Usuario getUsecriadorid() {
		return usecriadorid;
	}

	public void setUsecriadorid(Usuario usecriadorid) {
		this.usecriadorid = usecriadorid;
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
		Atendente other = (Atendente) obj;
		return Objects.equals(id, other.id);
	}

}
