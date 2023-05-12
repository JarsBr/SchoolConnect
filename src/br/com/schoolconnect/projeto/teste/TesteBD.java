package br.com.schoolconnect.projeto.teste;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.schoolconnect.projeto.database.DbConnect;
import br.com.schoolconnect.projeto.model.Disciplina;
public class TesteBD {
public static void main(String[] args) {

	Disciplina teste = new Disciplina(001, "Disciplina teste", "Disciplina de teste", "Nada fica de floaga ai", 4);
	criar(teste);
}

public static boolean criar(Disciplina p) {
    java.sql.Connection conn = DbConnect.getConexaoMySQL();
    String insertSQL = "INSERT INTO disciplina (cod_disciplina, nome, descricao, conteudo, carga_horaria) VALUES ('"
            + p.getCodDisciplina() + "','"
            + p.getNome() + "','"	
            + p.getDescricao() + "','"
            + p.getConteudo() + "','"
            + p.getCargaHoraria() + "')";
    try {
        //Statement st = conn.createStatement();
        Statement st = (Statement)conn.createStatement();
        st.executeUpdate(insertSQL);
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(br.com.schoolconnect.projeto.model.DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
}
