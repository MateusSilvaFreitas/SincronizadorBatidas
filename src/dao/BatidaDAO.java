package dao;

import dto.DadosBatidaDTO;
import dto.DadosUsuarioDTO;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BatidaDAO {
    public DadosUsuarioDTO gravaBatida(DadosBatidaDTO dadosBatidaDTO){
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select b.id from batidas b where b.hashUnico = " + dadosBatidaDTO.getHashUnico() + "'");

            if(result.next()){
                StringBuilder sql = new StringBuilder();
                sql.append("update batidas set ");
            } else {

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
