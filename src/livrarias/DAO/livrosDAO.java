/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.DAO;

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
import livrarias.classes.editoras;
import livrarias.classes.livros;

/**
 *
 * @author sandr
 */
public class livrosDAO {
    
     public void cadastrar(livros l) {
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("INSERT INTO livros ( id_editora, autores_id_autor, titulo, ano ) VALUES ( ?, ?, ?, ?) ");
            
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso");
            
        } catch (SQLException ex) {
            
        }  
    }
     public void alterar(livros l) {
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("UPDATE livros set id_editora = ?, autores_id_autor = ?, titulo = ?, ano = ? where id_livro = ? ");
            
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            
            stmt.setInt(5, l.getIdLivro());
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Livro alterado com sucesso");
            
        } catch (SQLException ex) {
            
        }
    }
    
     public void excluir(livros l) {
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("DELETE FROM livros WHERE id_livro = ? ");
            stmt.setInt(1, l.getIdLivro());
            
            stmt.executeUpdate() ;
            
            JOptionPane.showMessageDialog(null, "Livro excluido com sucesso");
            
        } catch (SQLException ex) {
            
        }
    }
     
      public List<livros> listar(){
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<livros> livros = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("select livros.id_livro, editoras.nome as nome_editoras, autores.nome as nome_autores, livros.titulo, livros.ano from livros\n" +
"inner join editoras on (editoras.id_editora = livros.id_editora )\n" +
"inner join autores on (autores.id_autor = livros.autores_id_autor )");
            rs = stmt.executeQuery();
            
            while (rs.next()){

                livros l = new livros();
                l.setIdLivro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("Ano"));
                
                editoras e = new editoras();
                e.setNome(rs.getString("nome_editoras"));
                l.setEditora(e);
                
                autores a = new autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

               livros.add(l); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return livros;
    } 
    
     public List<livros> pesquisar(String texto){
        
        Connection conn =  bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<livros> livros = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("select id_livro, editoras.nome AS nome_editoras, autores.nome AS nome_autores, titulo, ano from livros\n" +
"inner join editoras on (editoras.id_editora = livros.id_editora )\n"+
"inner join autores on (autores.id_autor = livros.autores_id_autor ) WHERE titulo like ? ");
            stmt.setString(1, "%"+ texto + "%" );
            rs = stmt.executeQuery();
            
            while (rs.next()){

                livros l = new livros();
                l.setIdLivro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("Ano"));
                
                editoras e = new editoras();
                e.setNome(rs.getString("nome_editoras"));
                l.setEditora(e);
                
                autores a = new autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

                livros.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return livros;
    } 
    
}
