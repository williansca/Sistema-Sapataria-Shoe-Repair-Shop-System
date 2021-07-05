/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author shark3000
 */
public class UsuarioDao {

    // o método abaixo verifica se o usuário já é utilizado por outra pessoa
    public static boolean isLoginAvailable(String loginUsuario) {
        String sql = "select loginUsuario from tbUsuarios where loginUsuario = ?";

        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, loginUsuario);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public static void insertOrUpdate(String nomeUsuario, String sobrenomeUsuario, String telefoneUsuario, String loginUsuario, String senhaUsuario, String perfilUsuario, Float comissaoUsuario, String statusUsuario) throws SQLException {

        String sql = "insert into tbUsuarios (nomeUsuario,sobrenomeUsuario,telefoneUsuario,loginUsuario,senhaUsuario,perfilUsuario,comissaoUsuario,statusUsuario) values (?,?,?,?,?,?,?,?) on duplicate key update nomeUsuario = values(nomeUsuario),sobrenomeUsuario = values(sobrenomeUsuario),telefoneUsuario = values(telefoneUsuario),loginUsuario = values(loginUsuario),senhaUsuario = values(senhaUsuario),perfilUsuario = values(perfilUsuario),comissaoUsuario = values(comissaoUsuario),statusUsuario = values(statusUsuario)";
        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement st = conexao.prepareStatement("select * from tbusuarios where nomeUsuario = ?")) {
            st.setString(1, nomeUsuario);
            st.executeQuery();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, nomeUsuario);
            pst.setString(2, sobrenomeUsuario);
            pst.setString(3, telefoneUsuario);
            pst.setString(4, loginUsuario);
            pst.setString(5, senhaUsuario);
            pst.setString(6, perfilUsuario);
            pst.setFloat(7, comissaoUsuario);
            pst.setString(8, statusUsuario);

            pst.execute();
        }

    }

    //o método abaixo verifica se o usuário tem registro no banco com a tabela OS
    public static boolean isUsedUser(int codigoUsuario) {
        String sql = "select codigoUsuario from tbOs where codigoUsuario = ?";

        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, codigoUsuario);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }

    public static void remover(int codigoUsuario) {

        String sql = "delete from tbusuarios where codigoUsuario = ?";
        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, codigoUsuario);
            int apagado = pst.executeUpdate();
            // a linha abaixo server de apoio ao entendimento a logica, a variavel apagado vai ter o valor de 1 apos o exercuteUpdate
            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void inativarUsuario(int codigoUsuario) {
        
        String sql = "update tbUsuarios set statusUsuario = 'USUÁRIO INATIVO' where codigoUsuario = ?";
        try (Connection conexao = ModuloConexao.conector();
                PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, codigoUsuario);
            int desativado = pst.executeUpdate();
            if (desativado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário desativado com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
