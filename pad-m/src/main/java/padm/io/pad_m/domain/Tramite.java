package padm.io.pad_m.domain;

import java.util.Objects;

@Entity
public class Tramite {

	@Id
	private Integer id;

	private Sting tipo;
	private int setororigem;
	private int setorcriador;
	private int localtramite;
	private int setordestino;
	private DateTime datachegada;
	private DateTime datasaida;
	private DateTime datacheck;
	private int procId;
	private int docId;
	private int userId;
	private int usercriadorId;
	private int resposavelId;
	private int atendenteId;
	private int moderadorId;
	private int gestorId;
	private String tramitacao;
	private int finalidadeId;
	private String finalidade;
	private DateTime datadesarquivamento;
	private DateTime dataarquivamento;
	private String obs;
	private int sigiloId;
	private String visibilidade;
	private int recebimento;
	private DateTime datarecebimento;
	private int espera;
	private DateTime datainiciioespera;
	private DateTime datafimespera;
	private int seq;
	private int flag;
	private int placeid;

	public Tramite() {
	}

	public Tramite(Integer id, Sting tipo, int setororigem, int setorcriador, int localtramite, int setordestino,
			DateTime datachegada, DateTime datasaida, DateTime datacheck, int procId, int docId, int userId,
			int usercriadorId, int resposavelId, int atendenteId, int moderadorId, int gestorId, String tramitacao,
			int finalidadeId, String finalidade, DateTime datadesarquivamento, DateTime dataarquivamento, String obs,
			int sigiloId, String visibilidade, int recebimento, DateTime datarecebimento, int espera,
			DateTime datainiciioespera, DateTime datafimespera, int seq, int flag, int placeid) {
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
		this.docId = docId;
		this.userId = userId;
		this.usercriadorId = usercriadorId;
		this.resposavelId = resposavelId;
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
		this.datainiciioespera = datainiciioespera;
		this.datafimespera = datafimespera;
		this.seq = seq;
		this.flag = flag;
		this.placeid = placeid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sting getTipo() {
		return tipo;
	}

	public void setTipo(Sting tipo) {
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

	public DateTime getDatachegada() {
		return datachegada;
	}

	public void setDatachegada(DateTime datachegada) {
		this.datachegada = datachegada;
	}

	public DateTime getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(DateTime datasaida) {
		this.datasaida = datasaida;
	}

	public DateTime getDatacheck() {
		return datacheck;
	}

	public void setDatacheck(DateTime datacheck) {
		this.datacheck = datacheck;
	}

	public int getProcId() {
		return procId;
	}

	public void setProcId(int procId) {
		this.procId = procId;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUsercriadorId() {
		return usercriadorId;
	}

	public void setUsercriadorId(int usercriadorId) {
		this.usercriadorId = usercriadorId;
	}

	public int getResposavelId() {
		return resposavelId;
	}

	public void setResposavelId(int resposavelId) {
		this.resposavelId = resposavelId;
	}

	public int getAtendenteId() {
		return atendenteId;
	}

	public void setAtendenteId(int atendenteId) {
		this.atendenteId = atendenteId;
	}

	public int getModeradorId() {
		return moderadorId;
	}

	public void setModeradorId(int moderadorId) {
		this.moderadorId = moderadorId;
	}

	public int getGestorId() {
		return gestorId;
	}

	public void setGestorId(int gestorId) {
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

	public DateTime getDatadesarquivamento() {
		return datadesarquivamento;
	}

	public void setDatadesarquivamento(DateTime datadesarquivamento) {
		this.datadesarquivamento = datadesarquivamento;
	}

	public DateTime getDataarquivamento() {
		return dataarquivamento;
	}

	public void setDataarquivamento(DateTime dataarquivamento) {
		this.dataarquivamento = dataarquivamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getSigiloId() {
		return sigiloId;
	}

	public void setSigiloId(int sigiloId) {
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

	public DateTime getDatarecebimento() {
		return datarecebimento;
	}

	public void setDatarecebimento(DateTime datarecebimento) {
		this.datarecebimento = datarecebimento;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public DateTime getDatainiciioespera() {
		return datainiciioespera;
	}

	public void setDatainiciioespera(DateTime datainiciioespera) {
		this.datainiciioespera = datainiciioespera;
	}

	public DateTime getDatafimespera() {
		return datafimespera;
	}

	public void setDatafimespera(DateTime datafimespera) {
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

	public int getPlaceid() {
		return placeid;
	}

	public void setPlaceid(int placeid) {
		this.placeid = placeid;
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
				+ ", docId=" + docId + ", userId=" + userId + ", usercriadorId=" + usercriadorId + ", resposavelId="
				+ resposavelId + ", atendenteId=" + atendenteId + ", moderadorId=" + moderadorId + ", gestorId="
				+ gestorId + ", tramitacao=" + tramitacao + ", finalidadeId=" + finalidadeId + ", finalidade="
				+ finalidade + ", obs=" + obs + ", sigiloId=" + sigiloId + ", visibilidade=" + visibilidade
				+ ", recebimento=" + recebimento + ", espera=" + espera + ", seq=" + seq + ", flag=" + flag
				+ ", placeid=" + placeid + "]";
	}

}
