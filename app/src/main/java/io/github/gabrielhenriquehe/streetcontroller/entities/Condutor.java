package io.github.gabrielhenriquehe.streetcontroller.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "TB_condutor")
public class Condutor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "cpf")
    private String cpf;

    @ColumnInfo(name = "primeiro_nome")
    private String primeiroNome;

    @ColumnInfo(name = "ultimo_nome")
    private String ultimoNome;

    @ColumnInfo(name = "data_nascimento")
    private Date dataNascimento;

    @ColumnInfo(name = "vencimento_habilitacao")
    private Date vencimentoHabilitacao;

    public Condutor() {
    }

    public Condutor(String cpf, String primeiroNome, String ultimoNome, Date dataNascimento, Date vencimentoHabilitacao) {
        this.cpf = cpf;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.dataNascimento = dataNascimento;
        this.vencimentoHabilitacao = vencimentoHabilitacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getVencimentoHabilitacao() {
        return vencimentoHabilitacao;
    }

    public void setVencimentoHabilitacao(Date vencimentoHabilitacao) {
        this.vencimentoHabilitacao = vencimentoHabilitacao;
    }

    @Override
    public String toString() {
        return this.primeiroNome + " " + this.ultimoNome + " (" + this.formatarCpf(this.cpf) + ")";
    }

    private String formatarCpf(String cpf) {
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }

        return cpf;
    }
}
