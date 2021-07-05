/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;

/**
 *
 * @author shark3000
 */
public class ModuloConexao {

    //método responsável por estabelecer a conexão com o banco, Connection é do pacote java
    public static Connection conector() {
        //variável que vai receber a String de conexão
        java.sql.Connection conexao = null;
        //a linha abaixo cria um variável que "chama" o driver que foi importado para biblioteca
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/javasapataria";
        String user = "root";
        String password = "120689";
        
        //Estabelecendo conexão, tratamento de excessões, na tentativa de estabelecer conexão e não de certo
        try {
            // a linha abaixo executa o arquivo do driver
            Class.forName(driver);
            //a linha abaixo obtém a conexão usandos os parametros e armazena na variável conexão
            conexao = DriverManager.getConnection(url, user, password);
            //método conector requer retorno pois não é void
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e);
            return null;
        }
    }
}
