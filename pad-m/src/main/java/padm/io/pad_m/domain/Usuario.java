package padm.io.pad_m.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbusuario")
public class Usuario {

	@Id
	private Integer id;

	private String name;

	private Long cpf;

	private String nickName;

	private int servidor;

	@JsonIgnore
	private String senha;

	private int perfil;

	private int ativo;

	private String certificado;

	@Column(name = "dataExpiracao")
	private LocalDateTime dataExpiracao;

	@Column(name = "ultimoAcesso")
	private LocalDateTime ultimoAcesso;

	@Column(name = "dataCriacao")
	private LocalDateTime dataCriacao;

	private String image;

	private int flag;

	public Usuario() {
	}

	public Usuario(Integer id, String name, Long cpf, String nickName, int servidor, String senha, int perfil,
			int ativo, String certificado, LocalDateTime dataExpiracao, LocalDateTime ultimoAcesso,
			LocalDateTime dataCriacao, String image, int flag) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.nickName = nickName;
		this.servidor = servidor;
		this.senha = senha;
		this.perfil = perfil;
		this.ativo = ativo;
		this.certificado = certificado;
		this.dataExpiracao = dataExpiracao;
		this.ultimoAcesso = ultimoAcesso;
		this.dataCriacao = dataCriacao;
		this.image = image;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getServidor() {
		return servidor;
	}

	public void setServidor(int servidor) {
		this.servidor = servidor;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
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

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public LocalDateTime getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		return "Usuario [id=" + id + ", name=" + name + ", cpf=" + cpf + ", nickName=" + nickName + ", servidor="
				+ servidor + ", senha=" + senha + ", perfil=" + perfil + ", lotacao=" + ", ativo=" + ativo
				+ ", certificado=" + certificado + ", dataExpiracao=" + dataExpiracao + ", ultimoAcesso=" + ultimoAcesso
				+ ", dataCriacao=" + dataCriacao + ", image=" + image + ", flag=" + flag + "]";
	}

}
