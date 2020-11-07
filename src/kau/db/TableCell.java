package kau.db;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
    JCheckBox employee_check;
    public TableCell(){
    }
    public TableCell(JTable table){
        employee_check = new JCheckBox();
        employee_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(table.getSelectedRow());
            }
        });
    }
    @Override
    public Object getCellEditorValue(){
        //TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(column == 0)
            return employee_check;
        else return new JTextField();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(column == 0)
            return employee_check;
        else return new JTextField();
    }
}
