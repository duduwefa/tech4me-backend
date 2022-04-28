package br.com.tech4me.filmes.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mov_id")
    private Integer id;
    @Column(name="mov_title")
    private String titulo;
    @Column(name="mov_year")
    private Integer ano;
    @Column(name="mov_time")
    private Integer duracao;
    @Column(name="mov_lang")
    private String idioma;
    @Column(name="mov_dt_rel")
    private LocalDate dataLancamento;
    @Column(name="mov_rel_country")
    private String paisLancamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_direction"
        , joinColumns = @JoinColumn(name = "mov_id", referencedColumnName = "mov_id")
        , inverseJoinColumns = @JoinColumn(name = "dir_id", referencedColumnName = "dir_id")
    )
    private List<Diretor> diretores;

    @OneToMany(mappedBy = "id.filme")
    private List<Atuacao> atuacoes;

    //#region Getter / Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getPaisLancamento() {
        return paisLancamento;
    }

    public void setPaisLancamento(String paisLancamento) {
        this.paisLancamento = paisLancamento;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public List<Atuacao> getAtuacoes() {
        return atuacoes;
    }
    //#endregion

    @Override
    public String toString() {
        return String.format("Título: %s (%d)\n\tDiretores: %s\n\tAtuações: %s", 
        titulo.trim(), ano, getDiretores(), getAtuacoes());    }
}