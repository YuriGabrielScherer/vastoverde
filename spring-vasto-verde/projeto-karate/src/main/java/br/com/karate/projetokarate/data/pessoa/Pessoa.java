package br.com.karate.projetokarate.data.pessoa;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.data.campeonato.Campeonato;
import br.com.karate.projetokarate.data.generic.Auditable;
import br.com.karate.projetokarate.model.atleta.Grau;
import br.com.karate.projetokarate.security.permission.Permissao;

@Entity
@Table(name = "pessoas")
public class Pessoa extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(unique = true)
	private int codigo;

	@NotNull
	@Column(length = 100)
	private String nome;

	@NotNull
	@Column(length = 50, unique = true)
	private String email;

	@NotNull
	@Column(unique = true)
	private String login;

	@NotNull
	@Column(length = 70)
	private String senha;

	@Column(length = 14)
	private String telefone;

	@NotNull
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@CPF
	@NotNull
	@Column(length = 11, unique = true)
	private String cpf;

	@Column(name = "tipo_usuario")
	private EnumTipoPessoa tipoUsuario;

	@NotNull
	@Column(name = "sexo")
	private char sexo;

	@Column()
	@JoinColumn(name = "roles", unique = false, updatable = true)
	@ManyToMany()
	public List<Permissao> permissao;

	@Column(name = "nome_responsavel", length = 100)
	private String nomeResponsavel;

	@Column(name = "telefone_responsavel", length = 14)
	private String telefoneResponsavel;

	@CPF
	@Column(name = "cpf_responsavel", length = 11, unique = true)
	private String cpfResponsavel;
	
	@Column(length = 35)
	private String endereco;

	private Grau faixaId;

	@Max(value = 99999)
	@Column(name = "confederacao", unique = true)
	private int confederacao;

	@Max(value = 99999)
	@Column(name = "federacao", unique = true)
	private int federacao;
	
	@ManyToMany(mappedBy = "pessoas") // Estou dizendo aqui que sem uma pessoa não há campeonato - Definindo a dona da Relação
	private List<Campeonato> campeonatos;

	@NotNull
	@ManyToOne()
	private Associacao associacao;
	
	@ManyToMany(mappedBy = "arbitros")
	private List<Campeonato> campeonatosArbitro;
	
	@OneToOne()
	private Associacao associacaoResponsavel; 

	@NotNull
	private boolean ativo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Permissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(List<Permissao> permissao) {
		this.permissao = permissao;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Grau getFaixaId() {
		return faixaId;
	}

	public void setFaixaId(Grau faixaId) {
		this.faixaId = faixaId;
	}

	public int getConfederacao() {
		return confederacao;
	}

	public void setConfederacao(int confederacao) {
		this.confederacao = confederacao;
	}

	public int getFederacao() {
		return federacao;
	}

	public void setFederacao(int federacao) {
		this.federacao = federacao;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public Associacao getAssociacao() {
		return associacao;
	}

	public void setAssociacao(Associacao associacao) {
		this.associacao = associacao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getid() {
		return id;
	}

	public void setid(int idPessoa) {
		this.id = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomePessoa) {
		this.nome = nomePessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailPessoa) {
		this.email = emailPessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpfPessoa) {
		this.cpf = cpfPessoa;
	}

	public EnumTipoPessoa getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(EnumTipoPessoa tipoUsuarioPessoa) {
		this.tipoUsuario = tipoUsuarioPessoa;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexoPessoa) {
		this.sexo = sexoPessoa;
	}
}