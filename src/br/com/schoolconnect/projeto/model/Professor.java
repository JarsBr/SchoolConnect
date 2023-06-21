package br.com.schoolconnect.projeto.model;
/**
 * Classe que representa um professor.
 */
public class Professor {
    
    private String matricula;
    private String nome;
    private String email;
    private String graus;
    private String curriculo;
    
	// métodos getters e setters
    
    /**
     * Obtém a matrícula do professor.
     * @return A matrícula do professor.
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Define a matrícula do professor.
     * @param matricula A matrícula do professor.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    /**
     * Obtém o nome do professor.
     * @return O nome do professor.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Define o nome do professor.
     * @param nome O nome do professor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Obtém o email do professor.
     * @return O email do professor.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Define o email do professor.
     * @param email O email do professor.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Obtém os graus acadêmicos do professor.
     * @return Os graus acadêmicos do professor.
     */
    public String getGraus() {
        return graus;
    }
    
    /**
     * Define os graus acadêmicos do professor.
     * @param graus Os graus acadêmicos do professor.
     */
    public void setGraus(String graus) {
        this.graus = graus;
    }
    
    /**
     * Obtém o currículo do professor.
     * @return O currículo do professor.
     */
    public String getCurriculo() {
        return curriculo;
    }
    
    /**
     * Define o currículo do professor.
     * @param curriculo O currículo do professor.
     */
    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }
    
    /**
     * Retorna uma representação em formato de string dos dados do professor.
     * @return Uma string com os dados do professor.
     */
    @Override
    public String toString() {
        return "" + matricula + "\t" + nome + "\t" + email;
    }
    
    /**
     * Atualiza as informações do professor com novos graus acadêmicos e currículo.
     * @param grausAcademicos Os novos graus acadêmicos do professor.
     * @param curriculo O novo currículo do professor.
     */
    public void atualizarInformacoes(String grausAcademicos, String curriculo) {
        this.graus = grausAcademicos;
        this.curriculo = curriculo;
    }
}