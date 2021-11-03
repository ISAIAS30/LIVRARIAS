/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.classes;

/**
 *
 * @author sandr
 */
public class livros {
    
    private int idLivro;
    
    private String titulo;
    private int ano;
    private editoras editora;
    private autores autor;

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

   
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public editoras getEditora() {
        return editora;
    }

    public void setEditora(editoras editora) {
        this.editora = editora;
    }

    public autores getAutor() {
        return autor;
    }

    public void setAutor(autores autor) {
        this.autor = autor;
    }
    
    
 
}
