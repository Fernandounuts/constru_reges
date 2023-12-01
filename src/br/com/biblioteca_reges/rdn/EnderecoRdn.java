/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.rdn;

import br.com.biblioteca_reges.dominios.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anton
 */
public class EnderecoRdn {

    public int inserirEndereco(Endereco end) {

        try {

            int linhasAfetadas = 0;

            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO endereco (               ");
            str.append("            logradouro               ");
            str.append("            ,numero                  ");
            str.append("            ,uf                      ");
            str.append("            ,bairro                  ");
            str.append("            ,cep                     ");
            str.append("            ,cidade                   ");
            str.append("            ,idAluno)               ");
            str.append("      VALUES(                        ");
            str.append("             ?                       ");
            str.append("            ,?                       ");
            str.append("            ,?                       ");
            str.append("            ,?                       ");
            str.append("            ,?                       ");
            str.append("            ,?                       ");
            str.append("            ,?                       ");
            str.append("          )                          ");

            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            stmt.setString(1, end.getLogradouro());
            stmt.setString(2, end.getNumero());
            stmt.setString(3, end.getUf());
            stmt.setString(4, end.getBairro());
            stmt.setString(5, end.getCep());
            stmt.setString(6, end.getCidade());           
            stmt.setInt(7, end.getIdAluno());          
            
            
            linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return 0;
        }
    }

    public int alterarEndereco(Endereco end) {

        try {

            int linhasAfetadas = 0;

            StringBuilder str = new StringBuilder();

            str.append("UPDATE ENDERECO  SET NUMERO  = ? ");
            str.append("           ,LOGRADOURO       = ? ");
            str.append("           ,uf               = ? ");
            str.append("           ,bairro           = ? ");
            str.append("           ,cep              = ? ");
            str.append("           ,cidade           = ? ");
            str.append("WHERE idAluno               = ?   ");

            Connection conn = new ConnectionFactory().getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString());
            stmt.setString(1, end.getNumero());
            stmt.setString(2, end.getLogradouro());
            stmt.setString(3, end.getUf());
            stmt.setString(4, end.getBairro());
            stmt.setString(5, end.getCep());
            stmt.setString(6, end.getCidade());
            stmt.setInt(7, end.getIdAluno());

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            conn.close();

            return linhasAfetadas;

        } catch (SQLException ex) {

            System.out.println("Erro: " + ex.getMessage());

            return 0;

        }

    }

    public int deletarEnderecoPorAluno(int idAluno) {

        try {

            int linhasAfetadas;

            String str = "DELETE FROM ENDERECO WHERE idAluno = ?";

            Connection conn = new ConnectionFactory().getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString());
            stmt.setInt(1, idAluno);
            linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();
            return linhasAfetadas;

        } catch (Exception ex) {

            System.out.println("Erro: " + ex.getMessage());

            return 0;

        }

    }

   
    public Endereco obterPorIdAluno(int id) {

        try {

            Endereco ret = null;

            StringBuilder str = new StringBuilder();

            str.append("SELECT  ID              ");
            str.append("        ,LOGRADOURO      ");
            str.append("        ,NUMERO          ");
            str.append("        ,UF              ");
            str.append("        ,BAIRRO          ");
            str.append("        ,CEP             ");
            str.append("        ,idAluno        ");
            str.append("        ,CIDADE         ");
      
            str.append("FROM ENDERECO            ");
            str.append("WHERE idAluno = ?       ");

            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                
                
                //Endereco(String logradouro, String bairro, String numero, String cidade, String uf, String cep, int idAluno)
                    Endereco end = new Endereco(
                        rs.getInt("ID"),
                        rs.getString("LOGRADOURO"),
                         rs.getString("BAIRRO"),
                              rs.getString("NUMERO"),
                              rs.getString("CIDADE"),
                      
                              rs.getString("UF"),
                      
                      
                     
                        rs.getString("CEP"),
                         rs.getInt("idAluno"));
                    ret = end;
                    

            }

            stmt.close();
            conn.close();
            return ret;

        } catch (Exception ex) {

            System.out.println("Erro: " + ex.getMessage());

            return null;

        }

    }

}
