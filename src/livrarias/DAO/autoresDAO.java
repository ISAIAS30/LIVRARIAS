/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.DAO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import livrarias.bancoMysql;
import livrarias.classes.autores;


/**
 *
 * @author sandr
 */
public class autoresDAO {
    
    public void cadastrar(autores a) {
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("INSERT INTO autores (nome, endereco, numero, bairro, cidade, cpf) VALUES (?, ?, ?, ?, ?, ?)");
            
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setInt(3, a.getNumero());
            stmt.setString(4, a.getBairro());
            stmt.setString(5, a.getCidade());
            stmt.setBigDecimal(6,new BigDecimal(a.getCpf()));
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso");
            
        } catch (SQLException ex) {
        
        
        }
        
        
    }
    
    public void alterar(autores a) {
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("UPDATE autores set nome = ?, endereco = ?, numero = ?, bairro = ?, cidade = ?, cpf = ? where id_autor = ? ");
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setInt(3, a.getNumero());
            stmt.setString(4, a.getBairro());
            stmt.setString(5, a.getCidade());
            stmt.setBigDecimal(6,new BigDecimal(a.getCpf()));
            
            stmt.setInt(7, a.getId());
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Autor alterado com sucesso");
            
        } catch (SQLException ex) {
            
        }
        
            
    }
    
     public void excluir(autores a) {
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("DELETE FROM autores WHERE id_autor = ? ");
            stmt.setInt(1, a.getId());
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Autor excluido com sucesso");
            
        } catch (SQLException ex) {
            
        }
    }
    
    public List<autores> listar(){
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<autores> autores = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();
            
            while (rs.next()){

                autores a = new autores();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));
                a.setEndereco(rs.getString("endereco"));
                a.setNumero(rs.getInt("numero"));
                a.setBairro(rs.getString("bairro"));
                a.setCidade(rs.getString("cidade"));
                a.setCpf(new BigInteger(rs.getString("cpf")));                          

                autores.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(autoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    } 
    
     public List<autores> pesquisar(String texto){
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<autores> autores = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM autores where nome like ? ");
            stmt.setString(1, "%"+ texto + "%" );
            rs = stmt.executeQuery();
            
            while (rs.next()){

                autores a = new autores();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));
                a.setEndereco(rs.getString("endereco"));
                a.setNumero(rs.getInt("numero"));
                a.setBairro(rs.getString("bairro"));
                a.setCidade(rs.getString("cidade"));
                a.setCpf(new BigInteger(rs.getString("cpf")));   

                autores.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(autoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    } 
    
}
