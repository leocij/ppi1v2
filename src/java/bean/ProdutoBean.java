/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import trabalho.Categoria;
import trabalho.Produto;
import util.JPAUtil;

/**
 *
 * @author leoci
 */
@ManagedBean
@SessionScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private List<Produto> produtos = new ArrayList<>();
    private Integer idCategoria;
    
    public ProdutoBean() {
        listarTodos();
    }
    
    private void listarTodos() {
        EntityManager manager = JPAUtil.getManager();
        produtos = manager.createQuery("select c from Produto c", Produto.class).getResultList();
        JPAUtil.fecharEntityManager(manager);
    }
    
    public void novo(){
        produto = new Produto();
    }
    
    public void salvar(){
        EntityManager manager = JPAUtil.getManager();
        manager.getTransaction().begin();
        Categoria c = new Categoria(idCategoria);
        produto.setIdCategoria(c);
        manager.merge(produto);
        manager.getTransaction().commit();
        JPAUtil.fecharEntityManager(manager);
        listarTodos();
    }
    
    public void editar(Produto item){
        produto = item;
        idCategoria = item.getIdCategoria().getId();
    }
    
    public void apagar(Produto item){
        EntityManager manager = JPAUtil.getManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(Produto.class, item.getId()));
        manager.getTransaction().commit();
        JPAUtil.fecharEntityManager(manager);
        listarTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
}
