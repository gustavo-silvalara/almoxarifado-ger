/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.utils;

/**
 *
 * @author gustavo
 */
public class StringUtils {

    public static boolean stringValida(String string) {
        return (string != null && !string.equals("") ? true : false);
    }
}
