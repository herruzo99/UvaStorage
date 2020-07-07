/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaestadosunica.UI;

import maquinaestadosunica.Main;

/**
 *
 * @author yania
 */
class ControladorLoginWindow {
    LoginWindow  miVista;

    public ControladorLoginWindow(LoginWindow miVista) {
        this.miVista = miVista;
        miVista.clearLoginErrorMessage();
    }
/*
    public void procesaAyuda() {
        Main.getStateMachineLogin().help();
    }
*/
    public void procesaRecupera() {
       Main.getStateMachineLogin().recoverPassword();
    }

    public void procesaLogin() {
        miVista.clearLoginErrorMessage();
        // habla con Modelo
        // Cambiar true o false para probar un caso y otro
        boolean loginSucceed = true;
       
    }
    
}
