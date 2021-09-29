/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.videoprojeto.dal;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author 3164527
 */
public class ConectaBanco {
    public Statement stm;//responsavel por realizar pesquisar dentro do banco de dados
    public ResultSet rs;//responsavel por armazenar o resultado de uma pesquisa passada para o statement 
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/sistemavideoaula";
    private String usuario = "postgres";
    private String senha = "135167";
    public Connection conn;//responsavel por realizar a conexao com o banco de dados
    
    //Método responsável por realizar a conexão com o banco.
    public void conexao(){
        
        try{
            System.setProperty("jdbc.driver", driver);
            conn = DriverManager.getConnection(caminho, usuario, senha);
            if(conn != null){
                JOptionPane.showMessageDialog(null, "Banco de dados conectado com sucesso.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados! \n Erro: "+ e.getMessage());
            System.exit(0);
        }
    }
    
    public void desconecta(){
        try {
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\n Erro: "+ex.getMessage());
        }
            
    }
}
