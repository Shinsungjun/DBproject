package kau.db;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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
    JTextField SsnTextField;
    JLabel chooseText;
    JLabel numberofPeople;
    JLabel numberOfPeopleText;
    JLabel newSalaryText;
    JLabel SsnText;
    JLabel scopeText;
    JTable table;
    DefaultTableModel dtm;
    Vector header;
    Vector<Vector<String>> content;
    String department[] = {"All", "Headquarters", "Administration", "Research"};
    ArrayList<JCheckBox> attributeCbs;
    ArrayList<ArrayList<String>> contents;
    ArrayList<Boolean> isAttributeChecked;
    JScrollPane tableScrollPane;
    String attribute[] = new String[]{"Name", "Ssn", "Bdate", "Address", "Sex", "Salary", "SuperVisor", "Dname"};
    Boolean start;
    public Window() throws SQLException {
        super("Company DB");
        setLayout(null);
        connect_db();
        init_table_db();
        init_table();
        add_checkbox_at_atrributeJpanel();
        add_buttons_at_frame();
        add_texts();
        start = true;
        //frame 크기지정
        setSize(1000, 500);
        //창보이게 하기
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    void init_table_db() throws SQLException{
        contents = new ArrayList<ArrayList<String>>();
        String stmt1 = "select distinct CONCAT(employee.fname,\" \",employee.minit,\" \",employee.lname)name, employee.ssn, employee.Bdate, employee.Address, employee.sex, employee.salary," +
                " if(employee.super_ssn is NULL, 'NULL' , CONCAT(mgr.fname,\" \",mgr.minit,\" \",mgr.lname))supermgr_name, dname \n" +
                "FROM employee, employee as mgr, department \n" +
                "WHERE department.dnumber =  employee.dno and (mgr.ssn = employee.super_ssn or employee.super_ssn is NULL)";
        PreparedStatement s = con.prepareStatement(stmt1);
        ResultSet r = s.executeQuery();
        while(r.next()) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 1; i < 9; i++) {
                temp.add(r.getString(i));
            }
            contents.add(temp);
        }
        String counter = "select count(*) from employee";
        PreparedStatement NumberOfEmployee = con.prepareStatement(counter);
        ResultSet c = NumberOfEmployee.executeQuery();
        while(c.next()) {
            numberofPeople = new JLabel(c.getString(1));
        }
    }
    void test_db() throws SQLException{
        String stmt1 = "select Lname, Salary from EMPLOYEE";
        PreparedStatement p = con.prepareStatement(stmt1);
        ResultSet r = p.executeQuery();

        while(r.next()) {
            String lname = r.getString(1);
            String salary = r.getString(2);
            System.out.println(lname + " " + salary);
        }
    }
    void set_table_db() throws SQLException {
        contents.clear();
        String whereDepartment[] = new String[]{"department.dnumber =  employee.dno", "department.dnumber = employee.dno and employee.dno = 1",
                "department.dnumber = employee.dno and employee.dno = 4","department.dnumber = employee.dno and employee.dno = 5"};
        String stmt = "select distinct CONCAT(employee.fname,\" \",employee.minit,\" \",employee.lname)name, employee.ssn, employee.Bdate, employee.Address, employee.sex, employee.salary," +
                " if(employee.super_ssn is NULL, 'NULL' , CONCAT(mgr.fname,\" \",mgr.minit,\" \",mgr.lname))supermgr_name, dname \n" +
                "FROM employee, employee as mgr, department \n" +
                "WHERE " + whereDepartment[departComboBox.getSelectedIndex()] + " and (mgr.ssn = employee.super_ssn or employee.super_ssn is NULL)";

        PreparedStatement p = con.prepareStatement(stmt);
        ResultSet r = p.executeQuery();
        while(r.next()) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 1; i < isAttributeChecked.size()+1; i++) {
                if(isAttributeChecked.get(i-1))
                    temp.add(r.getString(i));
            }
            contents.add(temp);
        }
        numberofPeople.setText(String.valueOf(contents.size()));
    }


    public void add_texts(){
        scopeText = new JLabel("Scope");
        this.add(scopeText);
        scopeText.setBounds(15, 15, 100, 20);
        SsnText = new JLabel("Ssn");
        this.add(SsnText);
        SsnText.setBounds(540, 450, 100, 20);
        this.add(SsnText);
        newSalaryText = new JLabel("New Salary");
        this.add(newSalaryText);
        newSalaryText.setBounds(615, 450, 100, 20);
        numberOfPeopleText = new JLabel("Number of People : ");
        this.add(numberOfPeopleText);
        numberOfPeopleText.setBounds(30, 410, 200, 20);
        numberofPeople.setBounds(155, 410,200,20);
        this.add(numberofPeople);
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
        System.out.println("hello!");
        header = new Vector<String>(8);
        for(int i = 0; i < attribute.length; i++){
            header.add(attribute[i]);
        }
        content = new Vector<Vector<String>>(10);
        for(int i =0; i<contents.size(); i++){
            Vector temp = new Vector(9);
            for(int j = 0; j<contents.get(i).size(); j++){
                temp.add(j, contents.get(i).get(j));
            }
            content.add(i, temp);
        }
        dtm =new DefaultTableModel(content, header);
        table = new JTable(dtm);
        tableScrollPane = new JScrollPane(table);
        this.add(tableScrollPane);
        tableScrollPane.setBounds(20,90,960,310);
    }
    public void create_table() {
         //값을 받아서 넘어온 애들로만 만들기
        header.removeAllElements();
        content.removeAllElements();
        for(int i=0; i<isAttributeChecked.size(); i++){
            if(isAttributeChecked.get(i)){
                header.add(attribute[i]);
            }
        }
        for(int i =0; i<contents.size(); i++){
            Vector temp = new Vector(9);
            for(int j = 0; j<contents.get(i).size(); j++){
                temp.add(j, contents.get(i).get(j));
            }
            content.add(i, temp);
        }
        DefaultTableModel m = (DefaultTableModel)table.getModel();
        m.fireTableStructureChanged();
        m.fireTableDataChanged();
    }

    public void add_buttons_at_frame(){
        departComboBox = new JComboBox<String>(department);
        isAttributeChecked = new ArrayList<>();
        this.add(departComboBox);
        departComboBox.setBounds(10,35,200,30);
        searchButton = new JButton("Search");
        searchButton.setBounds(820, 28, 100,40);
        ArrayList<String> header = new ArrayList<>();
        searchButton.addActionListener(e -> {
            isAttributeChecked.clear();
            //모든 attribute 모두 조회 후 check 된 것만 보여주는 식으로.
            for(int i =0; i<attributeCbs.size(); i++){
                if(attributeCbs.get(i).isSelected()){
                    isAttributeChecked.add(true);
                }
                else isAttributeChecked.add(false);
            }
            //db 조회를 다시.
            try {
                set_table_db();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //다시 조회한 db를 통해 새로운 테이블 만들기
            create_table();
        });

        this.add(searchButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            if(start){
                try {
                    delete_db();
                    start = false;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                if(header.contains("Name") || header.contains("Ssn")){
                    try {
                        delete_db();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        deleteButton.setBounds(820, 410, 100,40);
        this.add(deleteButton);
        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            if(start){
                try {
                    update_db();
                    start = false;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                if(header.contains("Name") || header.contains("Ssn")){
                    try {
                        update_db();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        updateButton.setBounds(700, 410, 100, 40);
        this.add(updateButton);
        salaryTextField = new JTextField(10);
        salaryTextField.setBounds(600, 410, 100, 40);
        SsnTextField =  new JTextField(10);
        SsnTextField.setBounds(500, 410, 100, 40);
        this.add(SsnTextField);
        this.add(salaryTextField);
    }
    public void delete_db() throws SQLException {
        String ssn = SsnTextField.getText();
        System.out.println(ssn);
        String stmt = "delete from EMPLOYEE " +
                "where ssn="+ssn+" and (ssn not in (select mgr_ssn " +
                "from DEPARTMENT) or super_ssn is null)";
        PreparedStatement p = con.prepareStatement(stmt);
        System.out.println(p);
        p.executeUpdate();
        create_table();
    }

    public void update_db() throws SQLException {
        String ssn = SsnTextField.getText();
        String salary = salaryTextField.getText();
        String stmt = "update employee set salary = ? where ssn = ?";
        PreparedStatement p = con.prepareStatement(stmt);
        p.setString(1, salary);
        p.setString(2, ssn);
        p.executeUpdate();
        create_table();
    }
}
