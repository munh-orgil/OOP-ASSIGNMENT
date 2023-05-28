package frontend.teacher;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.*;

import backend.models.Logins;
import backend.models.Users;
import frontend.modules.Modules;
import frontend.student.layout.Layout;
import frontend.widgets.CustomButton;
import backend.Model;
 
public class CreateClasses {
    public static JPanel panel = new JPanel(new GridBagLayout());
    private JTextField ClassNameField, DescField;
    public void createClasses(){

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(5, 10, 5, 10);


    JLabel ClassName = new JLabel("Хичээлийн нэр:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(ClassName, constraints);
        
        ClassNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        ClassNameField.setPreferredSize(new Dimension(10, 40));
        panel.add(ClassNameField, constraints);

        
        JLabel DescLabel = new JLabel("Тайлбар:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(DescLabel, constraints);

        DescField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        DescField.setPreferredSize(new Dimension(10, 40));
        panel.add(DescField, constraints);

        class DualListBoxPanel extends JPanel {

            private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
          
            private static final String ADD_BUTTON_LABEL = "Add >>";
          
            private static final String REMOVE_BUTTON_LABEL = "<< Remove";
          
            private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";
          
            private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";
          
            private JLabel sourceLabel;
          
            private JList sourceList;
          
            private SortedListModel sourceListModel;
          
            private JList destList;
          
            private SortedListModel destListModel;
          
            private JLabel destLabel;
          
            private JButton addButton;
          
            private JButton removeButton;
          
            public DualListBoxPanel() {
              initScreen();
            }
          
            public String getSourceChoicesTitle() {
              return sourceLabel.getText();
            }
          
            public void setSourceChoicesTitle(String newValue) {
              sourceLabel.setText(newValue);
            }
          
            public String getDestinationChoicesTitle() {
              return destLabel.getText();
            }
          
            public void setDestinationChoicesTitle(String newValue) {
              destLabel.setText(newValue);
            }
          
            public void clearSourceListModel() {
              sourceListModel.clear();
            }
          
            public void clearDestinationListModel() {
              destListModel.clear();
            }
          
            public void addSourceElements(ListModel newValue) {
              fillListModel(sourceListModel, newValue);
            }
          
            public void setSourceElements(ListModel newValue) {
              clearSourceListModel();
              addSourceElements(newValue);
            }
          
            public void addDestinationElements(ListModel newValue) {
              fillListModel(destListModel, newValue);
            }
          
            private void fillListModel(SortedListModel model, ListModel newValues) {
              int size = newValues.getSize();
              for (int i = 0; i < size; i++) {
                model.add(newValues.getElementAt(i));
              }
            }
          
            public void addSourceElements(Object newValue[]) {
              fillListModel(sourceListModel, newValue);
            }
          
            public void setSourceElements(Object newValue[]) {
              clearSourceListModel();
              addSourceElements(newValue);
            }
          
            public void addDestinationElements(Object newValue[]) {
              fillListModel(destListModel, newValue);
            }
          
            private void fillListModel(SortedListModel model, Object newValues[]) {
              model.addAll(newValues);
            }
          
            public Iterator sourceIterator() {
              return sourceListModel.iterator();
            }
          
            public Iterator destinationIterator() {
              return destListModel.iterator();
            }
          
            public void setSourceCellRenderer(ListCellRenderer newValue) {
              sourceList.setCellRenderer(newValue);
            }
          
            public ListCellRenderer getSourceCellRenderer() {
              return sourceList.getCellRenderer();
            }
          
            public void setDestinationCellRenderer(ListCellRenderer newValue) {
              destList.setCellRenderer(newValue);
            }
          
            public ListCellRenderer getDestinationCellRenderer() {
              return destList.getCellRenderer();
            }
          
            public void setVisibleRowCount(int newValue) {
              sourceList.setVisibleRowCount(newValue);
              destList.setVisibleRowCount(newValue);
            }
          
            public int getVisibleRowCount() {
              return sourceList.getVisibleRowCount();
            }
          
            public void setSelectionBackground(Color newValue) {
              sourceList.setSelectionBackground(newValue);
              destList.setSelectionBackground(newValue);
            }
          
            public Color getSelectionBackground() {
              return sourceList.getSelectionBackground();
            }
          
            public void setSelectionForeground(Color newValue) {
              sourceList.setSelectionForeground(newValue);
              destList.setSelectionForeground(newValue);
            }
          
            public Color getSelectionForeground() {
              return sourceList.getSelectionForeground();
            }
          
            private void clearSourceSelected() {
              Object selected[] = sourceList.getSelectedValues();
              for (int i = selected.length - 1; i >= 0; --i) {
                sourceListModel.removeElement(selected[i]);
              }
              sourceList.getSelectionModel().clearSelection();
            }
          
            private void clearDestinationSelected() {
              Object selected[] = destList.getSelectedValues();
              for (int i = selected.length - 1; i >= 0; --i) {
                destListModel.removeElement(selected[i]);
              }
              destList.getSelectionModel().clearSelection();
            }
          
            private void initScreen() {
              setBorder(BorderFactory.createEtchedBorder());
              setLayout(new GridBagLayout());
              sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
              sourceListModel = new SortedListModel();
              sourceList = new JList(sourceListModel);
              add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                  GridBagConstraints.CENTER, GridBagConstraints.NONE,
                  EMPTY_INSETS, 0, 0));
              add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5,
                  1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                  EMPTY_INSETS, 0, 0));
          
              addButton = new JButton(ADD_BUTTON_LABEL);
              add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25,
                  GridBagConstraints.CENTER, GridBagConstraints.NONE,
                  EMPTY_INSETS, 0, 0));
              addButton.addActionListener(new AddListener());
              removeButton = new JButton(REMOVE_BUTTON_LABEL);
              add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25,
                  GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                      0, 5, 0, 5), 0, 0));
              removeButton.addActionListener(new RemoveListener());
          
              destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
              destListModel = new SortedListModel();
              destList = new JList(destListModel);
              add(destLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                  GridBagConstraints.CENTER, GridBagConstraints.NONE,
                  EMPTY_INSETS, 0, 0));
              add(new JScrollPane(destList), new GridBagConstraints(2, 1, 1, 5, .5,
                  1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                  EMPTY_INSETS, 0, 0));
            }
          
            class AddListener implements ActionListener {
              public void actionPerformed(ActionEvent e) {
                Object selected[] = sourceList.getSelectedValues();
                addDestinationElements(selected);
                clearSourceSelected();
              }
            }
          
            class RemoveListener implements ActionListener {
              public void actionPerformed(ActionEvent e) {
                Object selected[] = destList.getSelectedValues();
                addSourceElements(selected);
                clearDestinationSelected();
              }
            }
          
            class SortedListModel extends AbstractListModel {
          
              SortedSet model;
          
              public SortedListModel() {
                model = new TreeSet();
              }
          
              public int getSize() {
                return model.size();
              }
          
              public Object getElementAt(int index) {
                return model.toArray()[index];
              }
          
              public void add(Object element) {
                if (model.add(element)) {
                  fireContentsChanged(this, 0, getSize());
                }
              }
          
              public void addAll(Object elements[]) {
                Collection c = Arrays.asList(elements);
                model.addAll(c);
                fireContentsChanged(this, 0, getSize());
              }
          
              public void clear() {
                model.clear();
                fireContentsChanged(this, 0, getSize());
              }
          
              public boolean contains(Object element) {
                return model.contains(element);
              }
          
              public Object firstElement() {
                return model.first();
              }
          
              public Iterator iterator() {
                return model.iterator();
              }
          
              public Object lastElement() {
                return model.last();
              }
          
              public boolean removeElement(Object element) {
                boolean removed = model.remove(element);
                if (removed) {
                  fireContentsChanged(this, 0, getSize());
                }
                return removed;
              }
            }
        }
    }
}