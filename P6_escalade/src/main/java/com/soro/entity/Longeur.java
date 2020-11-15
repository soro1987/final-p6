package com.soro.entity;

import javax.persistence.*;

import java.util.List;

@Entity @Table(name = "longeur")
public class Longeur {
    @Id @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="cotation")
    private String cotation;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn (name = "voies_id")
    private Voies voies;

    @OneToMany(mappedBy = "longueur" )
    private List<Split> splits ;

    public Longeur() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voies getVoies() {
        return voies;
    }

    public void setVoies(Voies voies) {
        this.voies = voies;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public int getId() {
        return id;
    }


    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }


}
