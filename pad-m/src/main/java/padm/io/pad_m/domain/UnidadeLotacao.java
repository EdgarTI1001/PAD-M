package padm.io.pad_m.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dbo.tbUnidadeLotacao")
public class UnidadeLotacao {

	@Id
	private Integer id;

	@Column(name = "ORGAOID")
	private int orgao;

	@Column(name = "LOCALIDADEID")
	private int localidade;

	private String nome;

	private String segmento;

	private String descricao;

	private String sigla;

	private String obs;

	private int seq;

	private int flag;

	private int placed;

	public UnidadeLotacao() {
	}

	public UnidadeLotacao(Integer id, int orgao, int localidade, String nome, String segmento, String descricao,
			String sigla, String obs, int seq, int flag, int placed) {
		this.id = id;
		this.orgao = orgao;
		this.localidade = localidade;
		this.nome = nome;
		this.segmento = segmento;
		this.descricao = descricao;
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

	public int getOrgao() {
		return orgao;
	}

	public void setOrgao(int orgao) {
		this.orgao = orgao;
	}

	public int getLocalidade() {
		return localidade;
	}

	public void setLocalidade(int localidade) {
		this.localidade = localidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		UnidadeLotacao other = (UnidadeLotacao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UnidadeLotacao [id=" + id + ", orgao=" + orgao + ", nome=" + nome + ", segmento=" + segmento
				+ ", descricao=" + descricao + ", sigla=" + sigla + ", obs=" + obs + ", seq=" + seq + ", flag=" + flag
				+ ", placed=" + placed + "]";
	}

}
