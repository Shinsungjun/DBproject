package kau.db;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Window extends JFrame {
    JPanel attributeJpanel;
    Connection con;
    JCheckBox NameCb;
    JCheckBox SsnCb;
    JCheckBox BdateCb;
    JCheckBox AddressCb;
    JCheckBox SexCb;
    JCheckBox SalaryCb;
    JCheckBox SuperVisorCb;
    JCheckBox DnameCb;
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
    Object header[];
    Object content[][];
    String department[] = {"All", "Headquarters", "Administration", "Research"};
    ArrayList<JCheckBox> attributeCbs;
    public Window() throws SQLException {
        super("Company DB");
        setLayout(null);
        connect_db();
        test_db();
        add_checkbox_at_atrributeJpanel();
        add_buttons_at_frame();
        add_texts();
        init_table();
        //frame 크기지정
        setSize(1000, 500);
        //창보이게 하기
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void test_db() throws SQLException{
        String stmt1 = "select Lname, Salary from EMPLOYEE";
        PreparedStatement p = con.prepareStatement(stmt1);
        ResultSet r = p.executeQuery();

        while(r.next()){
            String lname = r.getString(1);
            String salary = r.getString(2);
            System.out.println(lname +" " + salary);
        }
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
        }
    }
    void connect_db() {
        con = null;
        // 연결
        try {
            Class.forName("com.mysql.jdbc.Driver"); //JDBC 드라이버 연결

            // 접속 url과 사용자, 비밀번호
            String url="jdbc:mysql://localhost:3306/company?serverTimezone=UTC";
            String user="root";
            String pwd="1234";

            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("정상적으로 연결되었습니다.");

        } catch (SQLException e) {
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("드라이버를 로드할 수 없습니다.");
            e.printStackTrace();
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
        attributeCbs = new ArrayList<>();
        NameCb = new JCheckBox("Name", true);
        SsnCb = new JCheckBox("Ssn", true);
        BdateCb = new JCheckBox("Bdate", true);
        AddressCb = new JCheckBox("Address", true);
        SexCb = new JCheckBox("Sex", true);
        SalaryCb = new JCheckBox("Salary", true);
        SuperVisorCb = new JCheckBox("SuperVisor", true);
        DnameCb = new JCheckBox("Dname", true);
        attributeCbs.add(NameCb);
        attributeCbs.add(SsnCb);
        attributeCbs.add(BdateCb);
        attributeCbs.add(AddressCb);
        attributeCbs.add(SexCb);
        attributeCbs.add(SalaryCb);
        attributeCbs.add(SuperVisorCb);
        attributeCbs.add(DnameCb);
        for(int i = 0; i < attributeCbs.size(); i++){
            attributeJpanel.add(attributeCbs.get(i));
        }
        this.add(attributeJpanel);
        attributeJpanel.setBounds(280,15,500,70);
    }
    public void init_table(){
        header = new Object[]{"Check", "Name", "Ssn", "Bdate", "Address",
                "Sex", "Salary", "SuperVisor", "Dname"}; //값을 받아서 넘어온 애들로만 만들기
        content = new Object[][]{{"false","John Smith", "123456789","1965-01-09","731 Fondren, Houston, TX",
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
        this.add(scrollpane);
        scrollpane.setBounds(20,90,960,310);
    }
    public void create_table(ArrayList<String> attributeHeader) {
        header = new Object[]{"Check", "Name", "Ssn", "Bdate", "Address",
                "Sex", "Salary", "SuperVisor", "Dname"}; //값을 받아서 넘어온 애들로만 만들기
        header = attributeHeader.toArray();
        content = new Object[][]{{"false","John Smith", "123456789","1965-01-09","731 Fondren, Houston, TX",
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
        this.add(scrollpane);
        scrollpane.setBounds(20,90,960,310);
    }
    public void add_buttons_at_frame(){
        departComboBox = new JComboBox<String>(department);
        this.add(departComboBox);
        departComboBox.setBounds(10,35,200,30);
        searchButton = new JButton("Search");
        searchButton.setBounds(820, 28, 100,40);
        ArrayList<String> header = new ArrayList<>();
        searchButton.addActionListener(e -> {
            //모든 attribute 모두 조회 후 check 된 것만 보여주는 식으로.
            header.clear();
            header.add("Check");
            for(int i =0; i<attributeCbs.size(); i++){
                if(attributeCbs.get(i).isSelected()){
                    header.add(attributeCbs.get(i).getText());
                }
            }
            create_table(header);
        });

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
}
