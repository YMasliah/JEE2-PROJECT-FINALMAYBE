package myapp.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import database.beans.Kind;

@ManagedBean
@ApplicationScoped
public class Data {

    public Kind[] getKinds() {
        return Kind.values();
    }

}