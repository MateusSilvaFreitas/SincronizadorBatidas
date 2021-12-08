package service;

import dto.DadosBatidaDTO;
import dto.DadosUsuarioDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class JsonService {

    private static FileWriter fileWriter;
    private static FileReader fileReader;

    public static void gravaArquivoJSON(DadosUsuarioDTO dadosUsuarioDTO) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("token", dadosUsuarioDTO.getToken());
        obj.put("quantidadeTempoCiclo", dadosUsuarioDTO.getQuantidadeTempoCiclo());
        obj.put("ciclosInativo", dadosUsuarioDTO.getCiclosInativo());
        try {
            fileWriter = new FileWriter("./data.json");
            fileWriter.write(obj.toJSONString());

        } catch (Exception e) {
            throw new Exception("Erro ao gravar arquivo: " + e.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                throw new Exception("Erro ao fechar arquivo: " + e.getMessage());
            }
        }
    }
    public static void gravaArquivoJSONInatividade(DadosBatidaDTO dadosBatidaDTO) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("batidaInicio", dadosBatidaDTO.getHorarioBatidaInicio().toString());
        obj.put("batidaFim", dadosBatidaDTO.getHorarioBatidaFim());
        obj.put("inatividadeTotal", dadosBatidaDTO.getInatividadeTotal());
        obj.put("maiorTempoInatividade", dadosBatidaDTO.getMaiorTempoInatividade());
        obj.put("hashUnico", dadosBatidaDTO.hashCode());
        try {
            fileWriter = new FileWriter("./dadosBatida.json");
            fileWriter.write(obj.toJSONString());

        } catch (Exception e) {
            throw new Exception("Erro ao gravar arquivo: " + e.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                throw new Exception("Erro ao fechar arquivo: " + e.getMessage());
            }
        }
    }

    public static DadosBatidaDTO verificaJsonBatida() throws Exception {
        try{
            File json = new File("./dadosBatida.json");
            fileReader = new FileReader(json);
            BufferedReader leitor = new BufferedReader(fileReader);
            JSONParser parser = new JSONParser();
            JSONObject jsonFormatado = (JSONObject) parser.parse(leitor.readLine());
            return new DadosBatidaDTO(
                    LocalDateTime.parse(jsonFormatado.get("batidaInicio").toString()),
                    null,
                    Long.parseLong(jsonFormatado.get("inatividadeTotal").toString()),
                    Long.parseLong(jsonFormatado.get("maiorTempoInatividade").toString()),
                    jsonFormatado.get("hashUnico").toString()
            );
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao ler o arquivo de batida");
        }
    }

    public static DadosUsuarioDTO verificaJsonUsuario() throws Exception {
        try{
            File json = new File("./data.json");
            fileReader = new FileReader(json);
            BufferedReader leitor = new BufferedReader(fileReader);
            JSONParser parser = new JSONParser();
            JSONObject jsonFormatado = (JSONObject) parser.parse(leitor.readLine());
            return new DadosUsuarioDTO(jsonFormatado.get("token").toString(), Integer.parseInt(jsonFormatado.get("quantidadeTempoCiclo").toString()), Integer.parseInt(jsonFormatado.get("ciclosInativo").toString()));
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao ler o arquivo de usuário");
        }
    }
}
