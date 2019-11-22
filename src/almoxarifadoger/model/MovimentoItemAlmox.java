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
public class MovimentoItemAlmox {

    private Long id;
    private ItemAlmoxarifado itemAlmox;
    private Responsavel responsavel;
    private Long data;
    private int tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemAlmoxarifado getItemAlmox() {
        return itemAlmox;
    }

    public void setItemAlmox(ItemAlmoxarifado itemAlmox) {
        this.itemAlmox = itemAlmox;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
}
