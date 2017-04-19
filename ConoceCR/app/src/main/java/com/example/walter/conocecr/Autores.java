package com.example.walter.conocecr;

/**
 * Created by asvaa on 19/4/2017.
 */

public class Autores {


    private String atributo01;
    private String atributo02;
    private int id_imag;

    /*        */

    public Autores(String atributo01, String atributo02, int NumDibujo){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.id_imag = NumDibujo;
    }



    public String getAtributo01() {
        return atributo01;
    }

    public void setAtributo01(String atributo01) {
        this.atributo01 = atributo01;
    }

    public String getAtributo02() {
        return atributo02;
    }

    public void setAtributo02(String atributo02) {
        this.atributo02 = atributo02;
    }

    public int getId_imag() {
        return id_imag;
    }

    public void setId_imag(int id_imag) {
        this.id_imag = id_imag;
    }
}
