import dao.BatidaDAO;
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
    static BatidaDAO batidaDAO = new BatidaDAO();

    public static void main(String[] args) throws Exception {
        DadosUsuarioDTO dadosUsuarioDTO;

        try {
            dadosUsuarioDTO = JsonService.verificaJsonUsuario();
            recuperaEGravaBatida();
        }catch (Exception e){
            System.out.println(e.getMessage());
            boolean tokenValido = false;
            do{
                String token = JOptionPane.showInputDialog(null,"Informe o token");
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

    private static void recuperaEGravaBatida() {
        try {
            DadosBatidaDTO dadosBatidaDTO = JsonService.verificaJsonBatida();
            batidaDAO.gravaBatida(dadosBatidaDTO);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar as informações");
        }
    }

    private static void montaJFrame(DadosUsuarioDTO dadosUsuarioDTO) {
        JFrame jFrame = new JFrame("Proposta Betty");
        insereTrayWindows(jFrame);
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
                finalizarPrograma();
            }
        });

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                label.setBounds(50,50, 200,70);
                label.setText("Lendo todos os seus movimentos...");
                mainframe.remove(b);
                mainframe.add(bFechar);
                MoveMouseService mouse = new MoveMouseService();
                mouse.verificarMouse(dadosUsuarioDTO.getQuantidadeTempoCiclo(), dadosUsuarioDTO.getCiclosInativo(), dadosUsuarioDTO.getIdUsuario());
            }
        });
        mainframe.add(label);
        mainframe.add(b);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.getContentPane().add(mainframe);
        constroiJframe(jFrame);
    }

    private static void constroiJframe(JFrame jFrame) {
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setSize(new Dimension(250, 100));
    }

    private static void insereTrayWindows(JFrame jFrame) {
        if (SystemTray.isSupported()) {
            final PopupMenu popup = new PopupMenu();
            final SystemTray tray = SystemTray.getSystemTray();
            TrayIcon iconeTray = null;


            try {
                Image image = Toolkit.getDefaultToolkit().getImage("C:/Ponto/TrayIcon.png");
                iconeTray = new TrayIcon(image);
                iconeTray.setImageAutoSize(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            MenuItem sobre = new MenuItem("Sobre");
            sobre.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Criado por: Mateus Silva Freitas, Lucas Machado e Luiz Kischel");
            });

            MenuItem exibir = new MenuItem("Abrir");
            exibir.addActionListener(e -> {
                constroiJframe(jFrame);
            });

            MenuItem sair = new MenuItem("Finalizar");
            sair.addActionListener(e -> {
                finalizarPrograma();
            });

            popup.add(sobre);
            popup.addSeparator();
            popup.add(exibir);
            popup.add(sair);
            iconeTray.setPopupMenu(popup);

            try {
                tray.add(iconeTray);
            } catch (AWTException e) {
                JOptionPane.showMessageDialog(null, "Não foi possível adicionar o programa ao Tray");
            }
        }
    }

    private static void finalizarPrograma() {
        try {
            recuperaEGravaBatida();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar as informações");
        }
        System.exit(0);
    }
}
