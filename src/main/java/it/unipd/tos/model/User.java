////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class User {
    private String name;
    private Integer eta;

    User(String n, Integer e) {
        this.name = n;
        this.eta = e;
    }

    public String getName(){
        return name;
    }

    public Integer getEta(){
        return eta;
    }
}
