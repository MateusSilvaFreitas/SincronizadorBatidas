package dao;

import dto.DadosUsuarioDTO;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    public DadosUsuarioDTO verificaTokenUsuario(String token){
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select u.token, qntdTempoCiclo, qntdCiclosInativo, u.id from usuarios u inner join config c on c.id = u.id_config_usuario where u.token = '" + token + "'");

            while (result.next()){
                return new DadosUsuarioDTO(result.getString(1), result.getInt(2), result.getInt(3), result.getLong(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
