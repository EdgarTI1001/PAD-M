package padm.io.pad_m.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbLocalidade")
public class Localidade {

	@Id
	private Integer id;

	@Column(name = "ORAGOID")
	private Integer orgaoId;

	private String nome;

	private String descricao;

	private String segmento;

	private String sigla;

	private String obs;

	private int seq;

	private int flag;

	private int placed;

	public Localidade() {

	}

	public Localidade(Integer id, Integer orgaoId, String nome, String descricao, String segmento, String sigla,
			String obs, int seq, int flag, int placed) {
		this.id = id;
		this.orgaoId = orgaoId;
		this.nome = nome;
		this.descricao = descricao;
		this.segmento = segmento;
		this.sigla = sigla;
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

	public Integer getOrgaoId() {
		return orgaoId;
	}

	public void setOrgaoId(Integer orgaoId) {
		this.orgaoId = orgaoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Localidade other = (Localidade) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
