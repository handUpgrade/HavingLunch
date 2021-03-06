import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.ColorChooserUI;

public class Worldcup {
    static JPanel panelNorth;
    static JPanel panelCenter;
    static JLabel message;
    static JLabel vs;
    static JButton left;
    static JButton right;
    static String[] images={
        //사진 파일
        "ima1.png","ima2.png","ima3.png","ima4.png","ima5.png","ima6.png","ima7.png","ima8.png","ima9.png","ima10.png",
        "ima11.png","ima12.png","ima13.png","ima14.png","ima15.png","ima16.png",
    };

    static int imageIndex = 2;

    static ImageIcon changeImage(String filename){
        ImageIcon icon = new ImageIcon("./"+filename);
        Image originImage = icon.getImage();
        Image changedImage =originImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon_new = new ImageIcon(changedImage);
        return icon_new;
    }

    static class MyFrame extends JFrame implements ActionListener{
        public MyFrame(String title){
            super(title);
            
            //UI
            this.setSize(550,550);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panelNorth = new JPanel();
            message = new JLabel("HAVE LUNCH TOGETHER");
            message.setFont(new Font("Arial",Font.BOLD, 40));
            message.setForeground(Color.white);
            panelNorth.add(message);
            panelNorth.setBackground(Color.black);
            this.add("North", panelNorth);

            panelCenter = new JPanel();
            panelCenter.setBackground(Color.black);
            vs = new JLabel("vs");
            vs.setFont(new Font("Arial", Font.BOLD, 40));
            vs.setForeground(Color.white);
            
            //왼쪽 부분
            left = new JButton("LeftButton");
            left.setIcon(changeImage("ima1.png"));
            left.setPreferredSize(new Dimension(230,230));
            left.setBackground(Color.black);
            
            // 오른쪽 부분
            right = new JButton("RightButton");
            right.setIcon(changeImage("ima2.png"));
            right.setPreferredSize(new Dimension(230,230));
            right.setBackground(Color.black);
        
            //클릭
            left.addActionListener(this);
            right.addActionListener(this);
            panelCenter.add(left);
            panelCenter.add(vs);
            panelCenter.add(right);
            this.add("Center", panelCenter);

            this.pack();
            randomImages();
            // mainSound("fa.wav");
        }
        //버튼 클릭
        
        @Override
        public void actionPerformed(ActionEvent e){
            // 16개가 넘엇을 때

            if( imageIndex == 16){
                message.setText("JJJJJJAAAAAASSSSS");

                if(e.getActionCommand().equals("LeftButton")){
                    right.hide();
                    vs.hide();
                    
                }
                else{
                    left.hide();
                    vs.hide();
                }
            }
            else{        
                if(e.getActionCommand().equals("LeftButton")){
                    right.setIcon(changeImage(images[imageIndex]));
                    imageIndex++;
                }else{
                    left.setIcon(changeImage(images[imageIndex]));
                    imageIndex++;
                }
            }
        }
    }
    static void mainSound(String filename){
        File file = new File("./wav/"+filename);
        if(file.exists()){
            try{
                AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("No sound");
        }
    }

    static void randomImages(){
        Random random = new Random();
        for (int i = 0; i < 16; i++){
            int randomIma = random.nextInt(15)+1;
            String temp = images[0];
            images[0] = images[randomIma];
            images[randomIma] = temp;
        }
    }


    public static void main(String[] args){
        new MyFrame("Worldcup");
    }

}