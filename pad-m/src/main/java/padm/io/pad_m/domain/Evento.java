package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbEvento")
public class Evento {

	@Id
	private Integer id;

	@Column(name = "TIPO_ID")
	private Integer tipoId;

	@Column(name = "TRAMITE_ID")
	private Integer tramiteId;

	@Column(name = "PROC_ID")
	private Integer procId;

	@Column(name = "DOC_ID")
	private Integer docId;

	private LocalDateTime dataevento;

	private LocalDateTime datainicio;

	private LocalDateTime datatermino;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USERCRIADO_ID")
	private Integer userCriadoId;

	@Column(name = "RESPONSAVEL_ID")
	private Integer responsavelId;

	@Column(name = "ATENDENTE_ID")
	private Integer atendenteId;

	@Column(name = "MODERADOR_ID")
	private Integer moderadorId;

	@Column(name = "GESTOR_ID")
	private Integer gestorId;

	@Column(name = "SETOR_ID")
	private Integer setorId;

	private String evento;

	private String orgenv;

	private LocalDateTime dataorgenv;

	private String usuenv;

	private LocalDateTime datausuenv;

	private String orgrec;

	private LocalDateTime dataorgrec;

	private String usurec;

	private LocalDateTime datausurec;

	private LocalDateTime datachegada;

	private LocalDateTime datasaida;

	@Column(name = "FINALIDADE_ID")
	private Integer finalidadeId;

	private String finalidade;

	@Column(name = "MOTIVO_ID")
	private Integer motivoId;

	private String motivo;

	private String statusenviado;

	private String statusrecebido;

	private String statusproc;

	private String obs;

	private Integer seq;

	private Integer flag;

	private Integer placed;

	public Evento() {

	}

	public Evento(Integer id, Integer tipoId, Integer tramiteId, Integer procId, Integer docId,
			LocalDateTime dataevento, LocalDateTime datainicio, LocalDateTime datatermino, Integer userId,
			Integer userCriadoId, Integer responsavelId, Integer atendenteId, Integer moderadorId, Integer gestorId,
			Integer setorId, String evento, String orgenv, LocalDateTime dataorgenv, String usuenv,
			LocalDateTime datausuenv, String orgrec, LocalDateTime dataorgrec, String usurec, LocalDateTime datausurec,
			LocalDateTime datachegada, LocalDateTime datasaida, Integer finalidadeId, String finalidade,
			Integer motivoId, String motivo, String statusenviado, String statusrecebido, String statusproc, String obs,
			Integer seq, Integer flag, Integer placed) {
		this.id = id;
		this.tipoId = tipoId;
		this.tramiteId = tramiteId;
		this.procId = procId;
		this.docId = docId;
		this.dataevento = dataevento;
		this.datainicio = datainicio;
		this.datatermino = datatermino;
		this.userId = userId;
		this.userCriadoId = userCriadoId;
		this.responsavelId = responsavelId;
		this.atendenteId = atendenteId;
		this.moderadorId = moderadorId;
		this.gestorId = gestorId;
		this.setorId = setorId;
		this.evento = evento;
		this.orgenv = orgenv;
		this.dataorgenv = dataorgenv;
		this.usuenv = usuenv;
		this.datausuenv = datausuenv;
		this.orgrec = orgrec;
		this.dataorgrec = dataorgrec;
		this.usurec = usurec;
		this.datausurec = datausurec;
		this.datachegada = datachegada;
		this.datasaida = datasaida;
		this.finalidadeId = finalidadeId;
		this.finalidade = finalidade;
		this.motivoId = motivoId;
		this.motivo = motivo;
		this.statusenviado = statusenviado;
		this.statusrecebido = statusrecebido;
		this.statusproc = statusproc;
		this.obs = obs;
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

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public Integer getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Integer tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Integer getProcId() {
		return procId;
	}

	public void setProcId(Integer procId) {
		this.procId = procId;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public LocalDateTime getDataevento() {
		return dataevento;
	}

	public void setDataevento(LocalDateTime dataevento) {
		this.dataevento = dataevento;
	}

	public LocalDateTime getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(LocalDateTime datainicio) {
		this.datainicio = datainicio;
	}

	public LocalDateTime getDatatermino() {
		return datatermino;
	}

	public void setDatatermino(LocalDateTime datatermino) {
		this.datatermino = datatermino;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserCriadoId() {
		return userCriadoId;
	}

	public void setUserCriadoId(Integer userCriadoId) {
		this.userCriadoId = userCriadoId;
	}

	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Integer getAtendenteId() {
		return atendenteId;
	}

	public void setAtendenteId(Integer atendenteId) {
		this.atendenteId = atendenteId;
	}

	public Integer getModeradorId() {
		return moderadorId;
	}

	public void setModeradorId(Integer moderadorId) {
		this.moderadorId = moderadorId;
	}

	public Integer getGestorId() {
		return gestorId;
	}

	public void setGestorId(Integer gestorId) {
		this.gestorId = gestorId;
	}

	public Integer getSetorId() {
		return setorId;
	}

	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getOrgenv() {
		return orgenv;
	}

	public void setOrgenv(String orgenv) {
		this.orgenv = orgenv;
	}

	public LocalDateTime getDataorgenv() {
		return dataorgenv;
	}

	public void setDataorgenv(LocalDateTime dataorgenv) {
		this.dataorgenv = dataorgenv;
	}

	public String getUsuenv() {
		return usuenv;
	}

	public void setUsuenv(String usuenv) {
		this.usuenv = usuenv;
	}

	public LocalDateTime getDatausuenv() {
		return datausuenv;
	}

	public void setDatausuenv(LocalDateTime datausuenv) {
		this.datausuenv = datausuenv;
	}

	public String getOrgrec() {
		return orgrec;
	}

	public void setOrgrec(String orgrec) {
		this.orgrec = orgrec;
	}

	public LocalDateTime getDataorgrec() {
		return dataorgrec;
	}

	public void setDataorgrec(LocalDateTime dataorgrec) {
		this.dataorgrec = dataorgrec;
	}

	public String getUsurec() {
		return usurec;
	}

	public void setUsurec(String usurec) {
		this.usurec = usurec;
	}

	public LocalDateTime getDatausurec() {
		return datausurec;
	}

	public void setDatausurec(LocalDateTime datausurec) {
		this.datausurec = datausurec;
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

	public Integer getFinalidadeId() {
		return finalidadeId;
	}

	public void setFinalidadeId(Integer finalidadeId) {
		this.finalidadeId = finalidadeId;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public Integer getMotivoId() {
		return motivoId;
	}

	public void setMotivoId(Integer motivoId) {
		this.motivoId = motivoId;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getStatusenviado() {
		return statusenviado;
	}

	public void setStatusenviado(String statusenviado) {
		this.statusenviado = statusenviado;
	}

	public String getStatusrecebido() {
		return statusrecebido;
	}

	public void setStatusrecebido(String statusrecebido) {
		this.statusrecebido = statusrecebido;
	}

	public String getStatusproc() {
		return statusproc;
	}

	public void setStatusproc(String statusproc) {
		this.statusproc = statusproc;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getPlaced() {
		return placed;
	}

	public void setPlaced(Integer placed) {
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
		Evento other = (Evento) obj;
		return Objects.equals(id, other.id);
	}

}
