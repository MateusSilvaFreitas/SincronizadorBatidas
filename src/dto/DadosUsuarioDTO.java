package dto;

public class DadosUsuarioDTO {
    private String token;
    private int quantidadeTempoCiclo;
    private int ciclosInativo;
    private long idUsuario;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public DadosUsuarioDTO(){}

    public DadosUsuarioDTO(String token, int quantidadeTempoCiclo, int ciclosInativo, long idUsuario) {
        this.token = token;
        this.quantidadeTempoCiclo = quantidadeTempoCiclo;
        this.ciclosInativo = ciclosInativo;
        this.idUsuario = idUsuario;
    }

    public int getQuantidadeTempoCiclo() {
        return quantidadeTempoCiclo;
    }

    public void setQuantidadeTempoCiclo(int quantidadeTempoCiclo) {
        this.quantidadeTempoCiclo = quantidadeTempoCiclo;
    }

    public int getCiclosInativo() {
        return ciclosInativo;
    }

    public void setCiclosInativo(int ciclosInativo) {
        this.ciclosInativo = ciclosInativo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
