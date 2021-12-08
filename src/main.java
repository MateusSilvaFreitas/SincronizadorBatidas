import dao.UserDAO;
import dto.DadosBatidaDTO;
import dto.DadosUsuarioDTO;
import service.JsonService;
import service.MoveMouseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    static UserDAO dao = new UserDAO();

    public static void main(String[] args) throws Exception {
        DadosUsuarioDTO dadosUsuarioDTO;
        try {
            dadosUsuarioDTO = JsonService.verificaJsonUsuario();
            try {
                DadosBatidaDTO dadosBatidaDTO = JsonService.verificaJsonBatida();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar as informações");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            boolean tokenValido = false;
            do{
                String token = JOptionPane.showInputDialog(null,"Informe o token", JOptionPane.PLAIN_MESSAGE);
                dadosUsuarioDTO = dao.verificaTokenUsuario(token);
                if(dadosUsuarioDTO != null) {
                    JsonService.gravaArquivoJSON(dadosUsuarioDTO);
                    tokenValido = true;
                } else {
                    JOptionPane.showMessageDialog(null,"Token inválido");
                }
            }while (!tokenValido);
        }
        montaJFrame(dadosUsuarioDTO);
    }

    private static void montaJFrame(DadosUsuarioDTO dadosUsuarioDTO) {
        JFrame jFrame = new JFrame("Proposta Betty");
        JPanel mainframe = new JPanel();

        final JLabel label =new JLabel("Clique para iniciar a contagem");
        label.setBounds(50,50, 200,20);
        JButton b=new JButton("Iniciar");
        b.setBounds(0,100,95,30);


        JButton bFechar = new JButton("Finalizar");
        bFechar.setBounds(0,100,95,70);
        bFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DadosBatidaDTO dadosBatidaDTO = JsonService.verificaJsonBatida();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar as informações");
                }
                System.exit(2);
            }
        });

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                label.setBounds(50,50, 200,70);
                label.setText("Lendo todos os seus movimentos...");
                mainframe.remove(b);
                mainframe.add(bFechar);
                MoveMouseService mouse = new MoveMouseService();
                mouse.verificarMouse(dadosUsuarioDTO.getQuantidadeTempoCiclo(), dadosUsuarioDTO.getCiclosInativo());
            }
        });
        mainframe.add(label);
        mainframe.add(b);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(mainframe);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setSize(new Dimension(250, 100));
    }
}
