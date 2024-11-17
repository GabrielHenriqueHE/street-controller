package io.github.gabrielhenriquehe.streetcontroller.entities;

import androidx.room.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(tableName = "TB_veiculo",
        foreignKeys = @ForeignKey(
                entity = Condutor.class,
                parentColumns = "id",
                childColumns = "condutorId",
                onDelete = ForeignKey.SET_DEFAULT
        ))
public class Veiculo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String placa;
    private String tipo;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;

    @ColumnInfo(name = "condutorId", defaultValue = "NULL")
    private Long condutorId;

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Long getCondutorId() {
        return condutorId;
    }

    public void setCondutorId(Long condutorId) {
        this.condutorId = condutorId;
    }
}
