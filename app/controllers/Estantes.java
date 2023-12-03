package controllers;

import java.util.List;



import models.Estante;
import models.Livro;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
@With(Seguranca.class)

public class Estantes extends Controller{
    public static void form() {
		Estante est = (Estante)Cache.get("est");
    	Cache.clear();
		
		
		render( est);
	}
    public static void salvar(@Valid Estante est){
    	if(validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar o livro");
			Cache.set("est", est);
			form();
		}
		est.save();
		flash.success("Estante cadastrada");
		form();
	}
    public static void listar(String busca){
        List<Estante> lista;
		if (busca == null) {
			lista = Estante.findAll();
		} else {
			lista = Estante.find("setor like ?1 or carreira like ?1 or categoria like ?1  order by setor ", "%" + busca + "%" + busca + "%").fetch();
		}
        render (lista);
    }

    public static void deletar(Long id){
        Estante est = Estante.findById(id);
		est.delete();
		listar(null);
    }

    public static void editar(Long id){
        Estante est = Estante.findById(id);
		renderTemplate("Estantes/form.html", est);
    }
}
