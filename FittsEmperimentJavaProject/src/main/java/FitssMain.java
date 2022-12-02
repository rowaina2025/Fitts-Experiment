import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.pow;
import javax.swing.*;

public class FitssMain {
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
    */
    private JFrame frame;
    private JButton button1;
    private int cntD = 0, cntW = 0, cnt = 0, st = 0;
    static private double[] time, id, tm;
    private final int[] D = {100, 300, 600};
    private final int[] W = {150, 90, 50};
    static FitssMain fitss;
    private final double pxToCm = 264583333e-2;
  
    public void start() {
        frame = new JFrame("2.2");
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
        button1 = new JButton();
        button1.setBounds(900, 0, 150, 1200);
        button1.setBackground(new Color(100, 0, 100));
        button1.setVisible(true);
        frame.add(button1);
        JLabel label = new JLabel("Fitts' Experiment");
        label.setBounds(0, 100, 250, 100);
        label.setFont(new Font("Verdana", Font.PLAIN,18));
        frame.add(label);
        JLabel counter = new JLabel();
        counter.setBounds(0, 125, 500, 300);
        counter.setFont(new Font("Verdana", Font.PLAIN,18));
        frame.add(counter);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cnt <= 90) {
                    if(cnt == 0) {
                        time[st] = System.currentTimeMillis();
                    }
                    else if(cnt % 10 == 0) {
                        st++;
                        time[st] = System.currentTimeMillis();
                        time[st - 1] = time[st] - time[st - 1];
                        double cmD = D[cntD] * pxToCm * 2;
                        double cmW = W[cntW] * pxToCm;
                        double ID = Math.log(2.0 * cmD  / cmW) / Math.log(2.0);
                        tm[st -1] = time[st - 1] / 1000.0;
                        id[st - 1] = ID;
                        System.out.println("State: " + st + " Actual time: " + tm[st - 1] + " ID: " + id[st - 1]);
                        cntD++;
                        cntD %= 3;
                    }
                    if(cnt % 30 == 0 && cnt != 0 && cntW < 2) {
                        cntW++;
                    }
                    switchButtons();
                    cnt++;
                    if(cnt <= 90) {
                        counter.setText("Remaining clicks: " + Integer.toString(90 - cnt));
                    }
                }
                else if(cnt > 90)
                    constructTable();
                if(cnt == 90)
                    counter.setText("Click to show results: Actual time and ID");
            }
        });
    }
    public void switchButtons () {
        button1.setBounds((int) (1000 + pow(-1, cnt) * D[cntD]), 0, W[cntW], 1200);
    }
    
    public void constructTable() {
        String[] colNames = {"State", "Actual time", "ID"};
        String rowData[][] = new String[9][3];
        for(int i = 0; i < 9; i++) {
            rowData[i][0] = Integer.toString(i + 1);
            rowData[i][1] = Double.toString(tm[i]);
            rowData[i][2] = Double.toString(id[i]);
        }
        JTable table = new JTable(rowData, colNames);
        frame.add(table);
        table.setVisible(true);
        table.setBounds(10, 400, 700, 1000);
        table.setFont(new Font("Verdana", Font.PLAIN,18));
    }
    
    public static void main(String[] args) throws InterruptedException {
        fitss = new FitssMain();
        time = new double[12];
        tm = new double[12];
        id = new double[12];
        fitss.start();
    }
}
       
