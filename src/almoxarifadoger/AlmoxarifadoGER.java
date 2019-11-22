/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger;

import almoxarifadoger.view.Principal;

/**
 *
 * @author gustavo
 */
public class AlmoxarifadoGER {

    public static Principal principal;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        principal = new Principal();
        principal.setVisible(true);
    }

    public static void atualizaTela() {
        AlmoxarifadoGER.principal.revalidate();
        AlmoxarifadoGER.principal.repaint();
    }

}
