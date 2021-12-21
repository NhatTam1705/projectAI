
package view;

import javax.swing.*;

import model.DuLieuCoVua;

import java.awt.*;


public class ThongTinPanel extends JPanel{
    public ThongTinPanel(){
        setLayout(new BorderLayout());
        JPanel northPane = new PanelChuaThongTin();       
        JPanel centerPane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();        
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;        

        String[][] values = new String[][]{
            {"Đồ Án","CỜ VUA MINIMAX CẮT TỈA ALPHA BETA "+DuLieuCoVua.VERSION},
            
            {"Author","Nhóm_4"},
            {"Hướng Dẫn cách chơi  ","http://www.hoccovua.com"},
            {"Phiên bản V1 khởi tạo ngày ","1/12/2021"}
        };
        for(int i=0; i<values.length; i++){
            JLabel header = new JLabel(values[i][0]+": ");
            header.setFont(new Font(header.getFont().getName(),Font.BOLD,13));
            JLabel data = new JLabel(values[i][1]);
            c.gridx = 0;
            c.gridy = i;
            centerPane.add(header,c);
            c.gridx = 1;
            centerPane.add(data,c);
        }
        centerPane.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        add(northPane,BorderLayout.NORTH);
        add(centerPane,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    }    
    public static void hienThongBao(){                   
        JFrame f = new JFrame("Thông Tin Chi TIết");
        ThongTinPanel ap = new ThongTinPanel();
        f.getContentPane().add(ap);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    class PanelChuaThongTin extends JPanel{
        PanelChuaThongTin(){          
            JLabel label = new JLabel(" Thông Tin Game",JLabel.LEFT);
            label.setFont(new Font(label.getFont().getName(),Font.BOLD,15));
            label.setForeground(Color.decode("#9900AF"));
            add(label);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = this.getWidth()-5;
            int height = this.getHeight() - 1;
            g.setColor(Color.decode("#9900FF"));
            g.drawLine(0, height, width, height);
        }
    }
}
