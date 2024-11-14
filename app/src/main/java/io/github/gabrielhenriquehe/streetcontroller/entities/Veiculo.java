package io.github.gabrielhenriquehe.streetcontroller.entities;

import androidx.room.*;

@Entity(tableName = "TB_veiculo")
public class Veiculo {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String placa;
    private String tipo;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private long condutorId;

    public Veiculo() {}

    public Veiculo(long id, String placa, String tipo, String marca, String modelo, int ano) {
        this.id = id;
        this.placa = placa;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getCondutorId() {
        return condutorId;
    }

    public void setCondutorId(long condutorId) {
        this.condutorId = condutorId;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
