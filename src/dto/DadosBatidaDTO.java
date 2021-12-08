package dto;

import java.time.LocalDateTime;

public class DadosBatidaDTO {
    private LocalDateTime horarioBatidaInicio;
    private LocalDateTime horarioBatidaFim;
    private Long maiorTempoInatividade;
    private Long inatividadeTotal;
    private String hashUnico;

    public DadosBatidaDTO(){}

    public DadosBatidaDTO(LocalDateTime horarioBatidaInicio, LocalDateTime horarioBatidaFim, Long maiorTempoInatividade, Long inatividadeTotal) {
        this.horarioBatidaInicio = horarioBatidaInicio;
        this.horarioBatidaFim = horarioBatidaFim;
        this.maiorTempoInatividade = maiorTempoInatividade;
        this.inatividadeTotal = inatividadeTotal;
    }
    public DadosBatidaDTO(LocalDateTime horarioBatidaInicio, LocalDateTime horarioBatidaFim, Long maiorTempoInatividade, Long inatividadeTotal, String hashUnico) {
        this.horarioBatidaInicio = horarioBatidaInicio;
        this.horarioBatidaFim = horarioBatidaFim;
        this.maiorTempoInatividade = maiorTempoInatividade;
        this.inatividadeTotal = inatividadeTotal;
        this.hashUnico = hashUnico;
    }

    public LocalDateTime getHorarioBatidaInicio() {
        return horarioBatidaInicio;
    }

    public void setHorarioBatidaInicio(LocalDateTime horarioBatidaInicio) {
        this.horarioBatidaInicio = horarioBatidaInicio;
    }

    public LocalDateTime getHorarioBatidaFim() {
        return horarioBatidaFim;
    }

    public void setHorarioBatidaFim(LocalDateTime horarioBatidaFim) {
        this.horarioBatidaFim = horarioBatidaFim;
    }

    public Long getMaiorTempoInatividade() {
        return maiorTempoInatividade;
    }

    public void setMaiorTempoInatividade(Long maiorTempoInatividade) {
        this.maiorTempoInatividade = maiorTempoInatividade;
    }

    public Long getInatividadeTotal() {
        return inatividadeTotal;
    }

    public void setInatividadeTotal(Long inatividadeTotal) {
        this.inatividadeTotal = inatividadeTotal;
    }

    public String getHashUnico() {
        return hashUnico;
    }

    public void setHashUnico(String hashUnico) {
        this.hashUnico = hashUnico;
    }
}
