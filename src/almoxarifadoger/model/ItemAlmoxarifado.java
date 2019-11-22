/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.model;

/**
 *
 * @author gustavo
 */
public class ItemAlmoxarifado {

    private Long id;
    private Item item;
    private Almoxarifado Almoxarifado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Almoxarifado getAlmoxarifado() {
        return Almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado Almoxarifado) {
        this.Almoxarifado = Almoxarifado;
    }

    @Override
    public String toString() {
        return String.valueOf(id) ;
    }

}
