package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import trabalho.Categoria;
import util.JPAUtil;

@ManagedBean
@SessionScoped
public class CategoriaBean {

    private Categoria categoria = new Categoria();
    private List<Categoria> categorias = new ArrayList<>();

    public CategoriaBean() {
        listarTodos();
    }
    
    public void novo(){
        categoria = new Categoria();
    }
    
    public void salvar(){
        EntityManager manager = JPAUtil.getManager();
        manager.getTransaction().begin();
        manager.merge(categoria);
        manager.getTransaction().commit();
        JPAUtil.fecharEntityManager(manager);
        listarTodos();
    }
    
    public void editar(Categoria item){
        categoria = item;
    }
    
    public void apagar(Categoria item){
        EntityManager manager = JPAUtil.getManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(Categoria.class, item.getId()));
        manager.getTransaction().commit();
        JPAUtil.fecharEntityManager(manager);
        listarTodos();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    private void listarTodos() {
        EntityManager manager = JPAUtil.getManager();
        categorias = manager.createQuery("select c from Categoria c", Categoria.class).getResultList();
        JPAUtil.fecharEntityManager(manager);
    }

}
