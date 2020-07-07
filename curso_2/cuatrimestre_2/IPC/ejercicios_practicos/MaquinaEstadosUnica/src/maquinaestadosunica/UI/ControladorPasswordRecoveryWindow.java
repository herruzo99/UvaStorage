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
class ControladorPasswordRecoveryWindow {
    PasswordRecoveryWindow miVista;

    public ControladorPasswordRecoveryWindow(PasswordRecoveryWindow miVista) {
        this.miVista = miVista;
    }

    void procesaCancelar() {
        Main.getStateMachineLogin().back();
    }
    
    
    
}
