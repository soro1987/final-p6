package com.soro.entity;
import javax.persistence.*;

/*Represente les split contenu dans les longeur */

@Entity
@Table(name = "split")
public class Split {
    @Id @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="numero")
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "longuer_id")
    private Longeur longueur;

    public Split() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Longeur getLongueur() {
        return longueur;
    }

    public void setLongueur(Longeur longeur) {
        this.longueur = longeur;
    }

    public int getId() {
        return id;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
