package view;
import javax.swing.*;

import model.DuLieuCoVua;

import java.awt.*;
import java.awt.event.*;

public class TuyChonPanel extends JFrame implements ActionListener{

     JRadioButton btTrang;
     JRadioButton btden;
     JButton ok;
     JButton cancel;
     final static int TRANG = 0;
     final static int DEN = 1;
     Maincovua CoVua;

    public TuyChonPanel(Maincovua covua){
        super("Tùy Chọn");
        this.CoVua = covua;
        JPanel mainPane = new JPanel(new BorderLayout());
        //mainPane.add(taoPanelChonMucDo(),BorderLayout.CENTER);
        mainPane.add(chonQuanCotheomau(),BorderLayout.NORTH);
        mainPane.add(taonutchon(),BorderLayout.SOUTH);
        mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setContentPane(mainPane);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ok.addActionListener(this);
        cancel.addActionListener(this);
    }
    public JPanel chonQuanCotheomau(){
        JPanel chonQuanCoTheoMau = new JPanel(new GridLayout(1,2));
        btTrang = new JRadioButton("Chơi cờ trắng",true);
        btden = new JRadioButton("Chơi cờ đen");
        ButtonGroup group = new ButtonGroup();
        group.add(btTrang);
        group.add(btden);
        chonQuanCoTheoMau.add(btTrang);
        chonQuanCoTheoMau.add(btden);
        chonQuanCoTheoMau.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5,5,5,5),
                BorderFactory.createTitledBorder("Chọn màu quân cờ ")));
        return chonQuanCoTheoMau;
    }
    public JPanel taonutchon(){
        JPanel buttonPane = new JPanel(new BorderLayout());
        JPanel pane = new JPanel(new GridLayout(1,2,5,0));
        pane.add(ok = new JButton("Chơi"));
        pane.add(cancel = new JButton("Hủy Bỏ"));
        buttonPane.add(pane,BorderLayout.EAST);
        buttonPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5,5,5,5),
                BorderFactory.createTitledBorder("Bạn Muốn Làm Gì")));
        return buttonPane;
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ok){
            CoVua.trangThai = DuLieuCoVua.DAKETTHUC;
            CoVua.init();           
        }
        setVisible(false);
    }
}
