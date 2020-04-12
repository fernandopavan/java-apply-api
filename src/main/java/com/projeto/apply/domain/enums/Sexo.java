package com.projeto.apply.domain.enums;

public enum Sexo {

    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino"),;

    private int id;
    private String descricao;

    Sexo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao () {
        return descricao;
    }

    public static Sexo toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (Sexo x : Sexo.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + id);
    }


}
