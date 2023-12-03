package models;
import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Estante extends Model {
	@Required
    public String setor;
	@Required
    public String carreira;
	@Required
    public String categoria;
}
