package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbusuario")
public class Usuario {

	@Id
	private Integer id;

	private String nome;

	private Long cpf;

	private String nickname;

	@ManyToOne
	@JoinColumn(name = "servidor_id")
	private Servidor servidorId;

	@JsonIgnore
	private String senha;

	private int perfilId;

	private int ativo;

	private String certificado;

	private LocalDateTime dataexpiracao;

	private LocalDateTime ultimoacesso;

	private LocalDateTime datacriacao;

	private String image;

	private int flag;

	public Usuario() {
	}

	public Usuario(Integer id, String nome, Long cpf, String nickname, Servidor servidorId, String senha, int perfilId,
			int ativo, String certificado, LocalDateTime dataexpiracao, LocalDateTime ultimoacesso,
			LocalDateTime datacriacao, String image, int flag) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.nickname = nickname;
		this.servidorId = servidorId;
		this.senha = senha;
		this.perfilId = perfilId;
		this.ativo = ativo;
		this.certificado = certificado;
		this.dataexpiracao = dataexpiracao;
		this.ultimoacesso = ultimoacesso;
		this.datacriacao = datacriacao;
		this.image = image;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Servidor getServidorId() {
		return servidorId;
	}

	public void setServidorId(Servidor servidorId) {
		this.servidorId = servidorId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public LocalDateTime getDataexpiracao() {
		return dataexpiracao;
	}

	public void setDataexpiracao(LocalDateTime dataexpiracao) {
		this.dataexpiracao = dataexpiracao;
	}

	public LocalDateTime getUltimoacesso() {
		return ultimoacesso;
	}

	public void setUltimoacesso(LocalDateTime ultimoacesso) {
		this.ultimoacesso = ultimoacesso;
	}

	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDateTime datacriacao) {
		this.datacriacao = datacriacao;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nickname=" + nickname + ", servidorId="
				+ servidorId + ", senha=" + senha + ", perfilId=" + perfilId + ", ativo=" + ativo + ", certificado="
				+ certificado + ", dataexpiracao=" + dataexpiracao + ", ultimoacesso=" + ultimoacesso + ", datacriacao="
				+ datacriacao + ", image=" + image + ", flag=" + flag + "]";
	}

}
