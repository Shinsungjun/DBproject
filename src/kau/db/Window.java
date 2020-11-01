package kau.db;
import javax.swing.*;
import java.awt.*;
public class Window extends JFrame{
    JPanel attributeJpanel;
    JCheckBox att1;
    JCheckBox att2;
    JCheckBox att3;
    JCheckBox att4;
    JCheckBox att5;
    JCheckBox att6;
    JCheckBox att7;
    JCheckBox att8;
    JComboBox<String> departComboBox;
    JButton searchButton;
    JButton deleteButton;
    JButton updateButtion;
    JTextField salaryText;
    String department[] = {"All", "Headquarters", "Administration", "Research"};
    public Window() {
        super("Company DB");
        setLayout(null);
        add_checkbox_at_atrributeJpanel();
        add(attributeJpanel);
        add_buttons_at_frame();
        //frame 크기지정
        setSize(1000,500);
        //창보이게 하기
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void add_checkbox_at_atrributeJpanel(){
        attributeJpanel = new JPanel();
        attributeJpanel.setLayout(new GridLayout(2, 4));
        att1 = new JCheckBox("Name", true);
        att2 = new JCheckBox("Ssn", true);
        att3 = new JCheckBox("Bdate", true);
        att4 = new JCheckBox("Address", true);
        att5 = new JCheckBox("Sex", true);
        att6 = new JCheckBox("Salary", true);
        att7 = new JCheckBox("Super_name", true);
        att8 = new JCheckBox("Dname", true);
        attributeJpanel.add(att1);
        attributeJpanel.add(att2);
        attributeJpanel.add(att3);
        attributeJpanel.add(att4);
        attributeJpanel.add(att5);
        attributeJpanel.add(att6);
        attributeJpanel.add(att7);
        attributeJpanel.add(att8);
    }

    public void add_buttons_at_frame(){
        attributeJpanel.setBounds(280,15,500,70);
        departComboBox = new JComboBox<String>(department);
        this.add(departComboBox);
        departComboBox.setBounds(10,20,200,30);
        searchButton = new JButton("Search");
        searchButton.setBounds(820, 25, 100,40);
        this.add(searchButton);
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(820, 410, 100,40);
        this.add(deleteButton);
        updateButtion = new JButton("Update");
        updateButtion.setBounds(700, 410, 100, 40);
        this.add(updateButtion);
        salaryText = new JTextField(10);
        salaryText.setBounds(600, 410, 100, 40);
        this.add(salaryText);
    }
}
