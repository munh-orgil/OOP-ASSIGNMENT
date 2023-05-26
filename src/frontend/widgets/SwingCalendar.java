package frontend.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class SwingCalendar extends JPanel {
 
    DefaultTableModel model;
    Calendar cal = new GregorianCalendar();
    JLabel label;
    JTable table;
   
    public SwingCalendar() {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        CustomButton b1 = new CustomButton("<-", 50, 40);
        b1.setRadius(10);
        b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            cal.add(Calendar.MONTH, -1);
            updateMonth();
            }
        });

        CustomButton b2 = new CustomButton("->", 50, 40);
        b2.setRadius(10);
        b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            cal.add(Calendar.MONTH, +1);
            updateMonth();
        }
        });

        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        table = new JTable(model);
        table.setRowHeight(40);
        JScrollPane pane = new JScrollPane(table);
        table.setShowGrid(false);
        //   Border roundedBorder = new LineBorder(Color.black, 1, true);
        //   table.setBorder(roundedBorder);


        this.updateMonth();
        pane.setPreferredSize(new Dimension(280, (table.getRowCount() + 1) * 45));
        this.setPreferredSize(new Dimension(280, (table.getRowCount() + 1) * 45 + 40));

        this.setLayout(new BorderLayout());
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        this.add(emptyPanel, BorderLayout.NORTH);
        this.add(b1,BorderLayout.WEST);
        this.add(label,BorderLayout.CENTER);
        this.add(b2,BorderLayout.EAST);
        this.add(pane, BorderLayout.SOUTH);
    }
   
    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for(int day = 1; day <= numberOfDays; day++){
            model.setValueAt(day, i / 7 , i % 7);    
            i = i + 1;
        }
        Calendar currentDate = Calendar.getInstance();
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentRow = (currentDay + firstDayOfMonth - 2) / 7;
        int currentCol = (currentDay + firstDayOfMonth - 2) % 7;

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

        TableCellRenderer customRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = cellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                if (row == currentRow && column == currentCol && cal.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)) {
                    component.setBackground(Color.decode("#187bcd"));
                } else {
                    // Reset the background color
                    component.setBackground(table.getBackground());
                }

                return component;
            }
        };

        DefaultTableCellRenderer cellCenterRenderer = new DefaultTableCellRenderer();
        cellCenterRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellCenterRenderer);
        }
        table.getColumnModel().getColumn(currentCol).setCellRenderer(customRenderer);

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
    }
}
