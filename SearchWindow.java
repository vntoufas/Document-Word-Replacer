import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Billy
 */
public class SearchWindow extends JFrame{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JButton StartButton;
    private JButton ResetButton;
    private JButton OpenButton;
    protected String filename;
    protected String namenewfile="newfile.txt";
    protected String AbsolutePath;
    protected String to_replace=null;//λέξη προς αντιακτασταση
    protected String replace_with=null;//η λέξη που θα αντικαθιστά την to_replace λεξη
    String previous_str=null;//μεταβλητη για την καταχώρηση της λέξης που προιγειται
    int  many_times;
    
    public SearchWindow(){//ΔΗΜΙΟΥΡΓΩ ΤΟ ΓΡΑΦΙΚΟ ΠΕΡΙΒΑΛΛΟΝ
    super("Word replac-R");
    setSize(420,270);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane=getContentPane();
    pane.setLayout(null);
    setVisible(true);
    label1=new JLabel("File Path : ");
    label1.setBounds(20,20,140,30);        
    text1=new JTextField(20);
    text1.setBounds(80,20,140,30);
    OpenButton=new JButton("...");//επιπλεον έφτιαξα παραθυρο για επιλογή αρχείου
    OpenButton.setBounds(215,20,40,30);
    text2=new JTextField(20);
    text2.setBounds(60,50,140,30); 
    label2=new JLabel("Find : ");
    label2.setBounds(20,50,140,30);
    label3=new JLabel("Replace With : ");
    label3.setBounds(20,80,140,30);
    text3=new JTextField(20);
    text3.setBounds(110,80,140,30); 
    label4=new JLabel("previous Word:");
    label4.setBounds(20,110,140,30);
    text4=new JTextField(20);
    text4.setBounds(110,110,140,30); 
    StartButton=new JButton("Start");
    StartButton.setBounds(20,160,140,30);
    ResetButton=new JButton ("Reset Values");
    ResetButton.setBounds(20,190,140,30);
         
    add(label1);//προσθήκη ολων των στοιχείων του GUI
    add(text1);
    add(OpenButton);
    add(label2);
    add(text2);
    add(label3);
    add(text3);
    add(label4);
    add(text4);
    add(ResetButton);
    add(StartButton);
    setVisible(true);
    repaint();
    
    OpenFile open_file=new OpenFile();//ανοιγμα αρχείου
    OpenButton.addActionListener(open_file);//προσθηκη actionListener στο πληκτρο OpenButton
    previous_str=text4.getText().toString();
    Start start=new Start();
    StartButton.addActionListener(start);
    Reset reset=new Reset();
    ResetButton.addActionListener(reset);
    }//κλεινει το SearchWindow
    
    private class Reset implements ActionListener{//οταν πατησω reset 
        public void actionPerformed(ActionEvent ResetClick){
        Object source2=ResetClick.getSource();
        text1.setText("");//θετει τα 4 JTextField se
        text2.setText("");
        text3.setText("");
        text4.setText("");
        }
    }
    
    private class OpenFile implements ActionListener{//αν πατησουμε να ανοιξουμε ενα αρχειο
    public void actionPerformed(ActionEvent OpenFileClick){//τοτε εκτελείται ο παρακάτω κώδικας
    Object source=OpenFileClick.getSource();
    if (source==OpenButton)
        {
        JFileChooser fc=new JFileChooser();//δημιουργώ εάν αντικείμενο του file chooser
        int response =fc.showOpenDialog(null);//αρχικοποιω
        if(response==JFileChooser.APPROVE_OPTION){//αν διαλέξει αρχείο ο χρήστης τοτε ...
        text1.setText(fc.getSelectedFile().toString());//θέτω το path του αρχείου στο text1
        filename=fc.getSelectedFile().toString();//και παίρνω το ονομα του αρχείου
        }
    
    else{
        text1.setText("No file selected");//
        filename=null;}   
        }
   }//κλεινει την actionPerformed
}//κλεινει την OpenFile
  
    
    private class Start implements ActionListener{//αν ο χρήστης πατήσει το Start εκτελούνται τα παρακάτω
    public void actionPerformed(ActionEvent StartClick){
    Object source=StartClick.getSource();
    
            //String namenewfile=toString(Users/Billy/newfile.txt);
            BufferedWriter out;
            Scanner in;
            String str;
            String temp=null;
            System.out.println(text4.getText());
   try {  
        in=new Scanner(new FileReader(filename));//νεος scanner που διαβαζει απο αρχειο
        out = new BufferedWriter(new FileWriter(namenewfile));//νεος writter που γραφει το namenewfile (νεο αρχειο)
        to_replace=text2.getText();//παιρνω το string απο το JTextField text2
        replace_with=text3.getText().toString();//παιρνω το string απο το JTextField text3 
        while(in.hasNext())//επανάληψη μεχρι το τέλος του αρχειου (οσο υπαρχουν στοιχεια
        {str=in.next();//διαβάζει την επόμενη λέξη
        if (str.equals(to_replace))//έλεγχος της λεξης του πρωτότυπου 
            {str=replace_with;
        many_times++;}//αντικατασταση της λέξης
        out.write(str+" ");//εγγραφή της νέας λέξης στο νεό αρχείο
        }
     out.close();//κλείνει το αρχείο   
     JOptionPane.showMessageDialog (null, many_times+" occurances were found", "Found!", JOptionPane.INFORMATION_MESSAGE);
   }catch(IOException e){System.out.println("kati pige lathos sto try ");}
   }//κλεινει την actionPerformed
}//κλεινει την Start
}//Κλείνει την κλάση
