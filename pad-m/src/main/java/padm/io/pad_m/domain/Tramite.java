package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbtramite")
public class Tramite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	private int setororigem;

	private int setorcriador;

	private int localtramite;

	private int setordestino;

	private LocalDateTime datachegada; // setar na controller

	private LocalDateTime datasaida;  // setar na controller

	private LocalDateTime datacheck;

	@ManyToOne
	@JoinColumn(name = "proc_id")
	private Processo procId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuario userId;  // setar na controller

	private int usercriadorId;

	private int responsavelId;

	@ManyToOne
	@JoinColumn(name = "atendente_id")
	private Atendente atendenteId;

	@ManyToOne
	@JoinColumn(name = "moderador_id")
	private Moderador moderadorId;

	@ManyToOne
	@JoinColumn(name = "gestor_id")
	private Gestor gestorId;

	private String tramitacao;

	private int finalidadeId;

	private String finalidade;

	private LocalDateTime datadesarquivamento;

	private LocalDateTime dataarquivamento;

	private String obs;

	@ManyToOne
	@JoinColumn(name = "sigilo_id")
	private Sigilo sigiloId;

	private String visibilidade;

	private int recebimento;

	private LocalDateTime datarecebimento;

	private int espera;

	private LocalDateTime datainicioespera;

	private LocalDateTime datafimespera;

	private int seq;

	private int flag;

	private int placed;

	public Tramite() {
	}

	public Tramite(Integer id, String tipo, int setororigem, int setorcriador, int localtramite, int setordestino,
			LocalDateTime datachegada, LocalDateTime datasaida, LocalDateTime datacheck, Processo procId,
			Usuario userId, int usercriadorId, int responsavelId, Atendente atendenteId, Moderador moderadorId,
			Gestor gestorId, String tramitacao, int finalidadeId, String finalidade, LocalDateTime datadesarquivamento,
			LocalDateTime dataarquivamento, String obs, Sigilo sigiloId, String visibilidade, int recebimento,
			LocalDateTime datarecebimento, int espera, LocalDateTime datainicioespera, LocalDateTime datafimespera,
			int seq, int flag, int placed) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.setororigem = setororigem;
		this.setorcriador = setorcriador;
		this.localtramite = localtramite;
		this.setordestino = setordestino;
		this.datachegada = datachegada;
		this.datasaida = datasaida;
		this.datacheck = datacheck;
		this.procId = procId;
		this.userId = userId;
		this.usercriadorId = usercriadorId;
		this.responsavelId = responsavelId;
		this.atendenteId = atendenteId;
		this.moderadorId = moderadorId;
		this.gestorId = gestorId;
		this.tramitacao = tramitacao;
		this.finalidadeId = finalidadeId;
		this.finalidade = finalidade;
		this.datadesarquivamento = datadesarquivamento;
		this.dataarquivamento = dataarquivamento;
		this.obs = obs;
		this.sigiloId = sigiloId;
		this.visibilidade = visibilidade;
		this.recebimento = recebimento;
		this.datarecebimento = datarecebimento;
		this.espera = espera;
		this.datainicioespera = datainicioespera;
		this.datafimespera = datafimespera;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getSetororigem() {
		return setororigem;
	}

	public void setSetororigem(int setororigem) {
		this.setororigem = setororigem;
	}

	public int getSetorcriador() {
		return setorcriador;
	}

	public void setSetorcriador(int setorcriador) {
		this.setorcriador = setorcriador;
	}

	public int getLocaltramite() {
		return localtramite;
	}

	public void setLocaltramite(int localtramite) {
		this.localtramite = localtramite;
	}

	public int getSetordestino() {
		return setordestino;
	}

	public void setSetordestino(int setordestino) {
		this.setordestino = setordestino;
	}

	public LocalDateTime getDatachegada() {
		return datachegada;
	}

	public void setDatachegada(LocalDateTime datachegada) {
		this.datachegada = datachegada;
	}

	public LocalDateTime getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(LocalDateTime datasaida) {
		this.datasaida = datasaida;
	}

	public LocalDateTime getDatacheck() {
		return datacheck;
	}

	public void setDatacheck(LocalDateTime datacheck) {
		this.datacheck = datacheck;
	}

	public int getUsercriadorId() {
		return usercriadorId;
	}

	public void setUsercriadorId(int usercriadorId) {
		this.usercriadorId = usercriadorId;
	}

	public int getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(int responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Moderador getModeradorId() {
		return moderadorId;
	}

	public void setModeradorId(Moderador moderadorId) {
		this.moderadorId = moderadorId;
	}

	public Gestor getGestorId() {
		return gestorId;
	}

	public void setGestorId(Gestor gestorId) {
		this.gestorId = gestorId;
	}

	public String getTramitacao() {
		return tramitacao;
	}

	public void setTramitacao(String tramitacao) {
		this.tramitacao = tramitacao;
	}

	public int getFinalidadeId() {
		return finalidadeId;
	}

	public void setFinalidadeId(int finalidadeId) {
		this.finalidadeId = finalidadeId;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public LocalDateTime getDatadesarquivamento() {
		return datadesarquivamento;
	}

	public void setDatadesarquivamento(LocalDateTime datadesarquivamento) {
		this.datadesarquivamento = datadesarquivamento;
	}

	public LocalDateTime getDataarquivamento() {
		return dataarquivamento;
	}

	public void setDataarquivamento(LocalDateTime dataarquivamento) {
		this.dataarquivamento = dataarquivamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Sigilo getSigiloId() {
		return sigiloId;
	}

	public void setSigiloId(Sigilo sigiloId) {
		this.sigiloId = sigiloId;
	}

	public String getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}

	public int getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(int recebimento) {
		this.recebimento = recebimento;
	}

	public LocalDateTime getDatarecebimento() {
		return datarecebimento;
	}

	public void setDatarecebimento(LocalDateTime datarecebimento) {
		this.datarecebimento = datarecebimento;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public LocalDateTime getDatainicioespera() {
		return datainicioespera;
	}

	public void setDatainicioespera(LocalDateTime datainicioespera) {
		this.datainicioespera = datainicioespera;
	}

	public LocalDateTime getDatafimespera() {
		return datafimespera;
	}

	public void setDatafimespera(LocalDateTime datafimespera) {
		this.datafimespera = datafimespera;
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

	public Processo getProcId() {
		return procId;
	}

	public void setProcId(Processo procId) {
		this.procId = procId;
	}

	public Usuario getUserId() {
		return userId;
	}

	public void setUserId(Usuario userId) {
		this.userId = userId;
	}

	public Atendente getAtendenteId() {
		return atendenteId;
	}

	public void setAtendenteId(Atendente atendenteId) {
		this.atendenteId = atendenteId;
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
		Tramite other = (Tramite) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tramite [id=" + id + ", setororigem=" + setororigem + ", setorcriador=" + setorcriador
				+ ", localtramite=" + localtramite + ", setordestino=" + setordestino + ", procId=" + procId
				+ ", userId=" + userId + ", usercriadorId=" + usercriadorId + ", resposavelId="
				+ responsavelId + ", atendenteId=" + atendenteId + ", moderadorId=" + moderadorId + ", gestorId="
				+ gestorId + ", tramitacao=" + tramitacao + ", finalidadeId=" + finalidadeId + ", finalidade="
				+ finalidade + ", obs=" + obs + ", sigiloId=" + sigiloId + ", visibilidade=" + visibilidade
				+ ", recebimento=" + recebimento + ", espera=" + espera + ", seq=" + seq + ", flag=" + flag
				+ ", placed=" + placed + "]";
	}

}
