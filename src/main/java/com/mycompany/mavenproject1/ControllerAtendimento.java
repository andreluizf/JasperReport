

package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "nomeDoBean")   
@ViewScoped
public class ControllerAtendimento implements Serializable{
    public void iniciarAtendimento(){
        System.out.println("iniciar");
        RequestContext.getCurrentInstance().openDialog("page/atendimento/iniciarAtendimento");
    }
}
