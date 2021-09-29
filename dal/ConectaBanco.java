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
    
    //Método responsável por realizar a conexão com o banco.
    public static Connection conexao(){
        java.sql.Connection conn = null;
        String driver = "org.postgresql.Driver";
        String caminho = "jdbc:postgresql://localhost:5432/sistemavideoaula";
        String usuario = "postgres";
        String senha = "";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(caminho, usuario, senha);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar");
            return null;
        }       
    }
}
