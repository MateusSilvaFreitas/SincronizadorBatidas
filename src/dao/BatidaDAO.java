package dao;

import dto.DadosBatidaDTO;
import dto.DadosUsuarioDTO;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BatidaDAO {
    public DadosUsuarioDTO gravaBatida(DadosBatidaDTO dadosBatidaDTO){

        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select b.id from batidas b where b.hashUnico = ?");
            ps.setString(1, dadosBatidaDTO.getHashUnico());
            ResultSet result = ps.executeQuery();
            if(result.next()){
                StringBuilder sql = new StringBuilder();
                sql.append("update batidas set ");
                sql.append(" horario_batida_inicio = ? , ");
                sql.append(" horario_batida_fim = ? , ");
                sql.append(" maior_tempo_inatividade = ? , ");
                sql.append(" inatividade_total = ? ");
                sql.append(" where hashUnico = ? ");
                PreparedStatement ps2 = connection.prepareStatement(sql.toString());
                ps2.setTimestamp(1, Timestamp.valueOf(dadosBatidaDTO.getHorarioBatidaInicio()));
                ps2.setTimestamp(2, Timestamp.valueOf(dadosBatidaDTO.getHorarioBatidaFim() != null ? dadosBatidaDTO.getHorarioBatidaFim() : LocalDateTime.now()));
                ps2.setInt(3, dadosBatidaDTO.getMaiorTempoInatividade().intValue());
                ps2.setInt(4, dadosBatidaDTO.getInatividadeTotal().intValue());
                ps2.setString(5, dadosBatidaDTO.getHashUnico());

                ps2.executeUpdate();
                ps2.close();
            } else {
                String sql = "insert into batidas(id_usuario, horario_batida_inicio, horario_batida_fim, maior_tempo_inatividade, inatividade_total, hashUnico) values (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps2 = connection.prepareStatement(sql);
                ps2.setInt(1, dadosBatidaDTO.getIdUsuario().intValue());
                ps2.setTimestamp(2, Timestamp.valueOf(dadosBatidaDTO.getHorarioBatidaInicio()));
                ps2.setTimestamp(3, Timestamp.valueOf(dadosBatidaDTO.getHorarioBatidaFim() != null ? dadosBatidaDTO.getHorarioBatidaFim() : LocalDateTime.now()));
                ps2.setInt(4, dadosBatidaDTO.getMaiorTempoInatividade().intValue());
                ps2.setInt(5, dadosBatidaDTO.getInatividadeTotal().intValue());
                ps2.setString(6, dadosBatidaDTO.getHashUnico());

                ps2.execute();
                ps2.close();
            }
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
