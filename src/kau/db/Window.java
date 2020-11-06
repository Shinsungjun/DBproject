package kau.db;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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
    JButton updateButton;
    JTextField salaryTextField;
    JLabel chooseText;
    JLabel numberOfPeopleText;
    JLabel newSalaryText;
    JLabel scopeText;
    JTable table;
    DefaultTableModel dtm;
    String department[] = {"All", "Headquarters", "Administration", "Research"};
    public Window() {
        super("Company DB");
        setLayout(null);
        add_checkbox_at_atrributeJpanel();
        add_buttons_at_frame();
        add_texts();
        final Object header[] = {"Check","Name", "Ssn", "Bdate", "Address",
                            "Sex", "Salary", "SuperVisor", "Dname"};
        Object content[][] = {{"false","John Smith", "123456789","1965-01-09","731 Fondren, Houston, TX",
                            "M", "30000.00", "Franklin Wong", "Research"},
                {"true","John SS", "123456789","1965-01-09","731 Fondren, Houston, TX",
                        "M", "30000.00", "Franklin Wong", "Research"}};
        dtm = new DefaultTableModel(content, header){
            public boolean isCellEditable(int i, int c){
                //if(c == 0) return true;
                //return false;
                return true;
            }
        };
        table = new JTable(dtm);
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
        table.getColumnModel().getColumn(0).setCellEditor(new TableCell());
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);
        scrollpane.setBounds(20,90,960,310);
        //frame 크기지정
        setSize(1000,500);
        //창보이게 하기
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
        JCheckBox employee_check;
        public TableCell(){
            employee_check = new JCheckBox();
            employee_check.addActionListener(e -> {
                System.out.println(table.getValueAt(table.getSelectedRow(), 1));
            });
        }
        @Override
        public Object getCellEditorValue(){
            //TODO Auto-generated method stub
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return employee_check;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return employee_check;
        }
    }
    public void add_texts(){
        scopeText = new JLabel("Scope");
        this.add(scopeText);
        scopeText.setBounds(15, 15, 100, 20);
        newSalaryText = new JLabel("New Salary :");
        this.add(newSalaryText);
        newSalaryText.setBounds(525, 420, 100, 20);
        numberOfPeopleText = new JLabel("Number of People :");
        this.add(numberOfPeopleText);
        numberOfPeopleText.setBounds(30, 410, 200, 20);
        chooseText = new JLabel("Choose :");
        this.add(chooseText);
        chooseText.setBounds(30,430,100,20);
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
        att7 = new JCheckBox("SuperVisor", true);
        att8 = new JCheckBox("Dname", true);
        attributeJpanel.add(att1);
        attributeJpanel.add(att2);
        attributeJpanel.add(att3);
        attributeJpanel.add(att4);
        attributeJpanel.add(att5);
        attributeJpanel.add(att6);
        attributeJpanel.add(att7);
        attributeJpanel.add(att8);
        this.add(attributeJpanel);
        attributeJpanel.setBounds(280,15,500,70);
    }

    public void add_buttons_at_frame(){
        departComboBox = new JComboBox<String>(department);
        this.add(departComboBox);
        departComboBox.setBounds(10,35,200,30);
        searchButton = new JButton("Search");
        searchButton.setBounds(820, 28, 100,40);
        this.add(searchButton);
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(820, 410, 100,40);
        this.add(deleteButton);
        updateButton = new JButton("Update");
        updateButton.setBounds(700, 410, 100, 40);
        this.add(updateButton);
        salaryTextField = new JTextField(10);
        salaryTextField.setBounds(600, 410, 100, 40);
        this.add(salaryTextField);
    }
}
