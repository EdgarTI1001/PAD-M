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
@Table(name = "tbsetor")
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "orgaoid")
	private Orgao orgaoid;

	@ManyToOne
	@JoinColumn(name = "localidadeid")
	private Localidade localidadeid;

	private int unidadelotacaoid;

	private String nome;

	private String descricao;

	private String segmento;

	private String sigla;

	private int usercriadorId;

	private LocalDateTime datacriacao;

	private int responsavelId;

	@ManyToOne
	@JoinColumn(name = "atendente_id")
	private Atendente atendenteId;

	private int moderadorId;

	@ManyToOne
	@JoinColumn(name = "gestor_id")
	private Gestor gestorId;

	private String obs;

	private int seq;

	private int flag;

	private int placed;

	public Setor() {
	}

	public Setor(Integer id, Orgao orgaoid, Localidade localidadeid, int unidadelotacaoid, String nome,
			String descricao, String segmento, String sigla, int usercriadorId, LocalDateTime datacriacao,
			int responsavelId, Atendente atendenteId, int moderadorId, Gestor gestorId, String obs, int seq, int flag,
			int placed) {
		this.id = id;
		this.orgaoid = orgaoid;
		this.localidadeid = localidadeid;
		this.unidadelotacaoid = unidadelotacaoid;
		this.nome = nome;
		this.descricao = descricao;
		this.segmento = segmento;
		this.sigla = sigla;
		this.usercriadorId = usercriadorId;
		this.datacriacao = datacriacao;
		this.responsavelId = responsavelId;
		this.atendenteId = atendenteId;
		this.moderadorId = moderadorId;
		this.gestorId = gestorId;
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

	public Orgao getOrgaoid() {
		return orgaoid;
	}

	public void setOrgaoid(Orgao orgaoid) {
		this.orgaoid = orgaoid;
	}

	public Localidade getLocalidadeid() {
		return localidadeid;
	}

	public void setLocalidadeid(Localidade localidadeid) {
		this.localidadeid = localidadeid;
	}

	public int getUnidadelotacaoid() {
		return unidadelotacaoid;
	}

	public void setUnidadelotacaoid(int unidadelotacaoid) {
		this.unidadelotacaoid = unidadelotacaoid;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getUsercriadorId() {
		return usercriadorId;
	}

	public void setUsercriadorId(int usercriadorId) {
		this.usercriadorId = usercriadorId;
	}

	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDateTime datacriacao) {
		this.datacriacao = datacriacao;
	}

	public int getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(int responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Atendente getAtendenteId() {
		return atendenteId;
	}

	public void setAtendenteId(Atendente atendenteId) {
		this.atendenteId = atendenteId;
	}

	public int getModeradorId() {
		return moderadorId;
	}

	public void setModeradorId(int moderadorId) {
		this.moderadorId = moderadorId;
	}

	public Gestor getGestorId() {
		return gestorId;
	}

	public void setGestorId(Gestor gestorId) {
		this.gestorId = gestorId;
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

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Setor other = (Setor) obj;
		return Objects.equals(id, other.id);
	}

}
