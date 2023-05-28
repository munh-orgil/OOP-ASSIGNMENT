package frontend.widgets;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import backend.models.Users;

public class DualListBoxPanel extends JPanel {

  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

  private static final String ADD_BUTTON_LABEL = "Нэмэх >>";
  private static final String REMOVE_BUTTON_LABEL = "<< Хасах";
  private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";
  private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";

  private JLabel sourceLabel;
  private JList<Users> sourceList;
  private SortedListModel sourceListModel;
  private JList<Users> destList;
  private SortedListModel destListModel;
  private JLabel destLabel;
  private CustomButton addButton;
  private CustomButton removeButton;

  public DualListBoxPanel() {
    initScreen();
  }

  public SortedListModel getDestListModel() {
    return destListModel;
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

  public void addSourceElements(ListModel<Users> newValue) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(ListModel<Users> newValue) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(ListModel<Users> newValue) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, ListModel<Users> newValues) {
    int size = newValues.getSize();
    for (int i = 0; i < size; i++) {
      model.add(newValues.getElementAt(i));
    }
  }

  public void addSourceElements(Users[] newValue) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(Users[] newValue) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(Users[] newValue) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, Users[] newValues) {
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
    Vector<Users> selected = new Vector<>();
    for (int i = 0; i < sourceList.getModel().getSize(); i++) {
        selected.add(sourceList.getModel().getElementAt(i));
    }

    for (int i = selected.size() - 1; i >= 0; --i) {
      sourceListModel.removeElement(selected.get(i));
    }
    sourceList.getSelectionModel().clearSelection();
  }

  private void clearDestinationSelected() {
    Vector<Users> selected = new Vector<>();
    for (int i = 0; i < destList.getModel().getSize(); i++) {
        selected.add(destList.getModel().getElementAt(i));
    }

    for (int i = selected.size() - 1; i >= 0; --i) {
      destListModel.removeElement(selected.get(i));
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
    add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 2, 5, .5,
        1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        EMPTY_INSETS, 0, 0));

    addButton = new CustomButton(ADD_BUTTON_LABEL, 100, 50);
    addButton.setRadius(10);
    add(addButton, new GridBagConstraints(2, 2, 1, 2, 0, .25,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
    addButton.addActionListener(new AddListener());

    removeButton = new CustomButton(REMOVE_BUTTON_LABEL, 100, 50);
    removeButton.setRadius(10);
    add(removeButton, new GridBagConstraints(2, 4, 1, 2, 0, .25,
        GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
        0, 5, 0, 5), 0, 0));
    removeButton.addActionListener(new RemoveListener());

    destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
    destListModel = new SortedListModel();
    destList = new JList(destListModel);

    add(destLabel, new GridBagConstraints(3, 0, 1, 1, 0, 0,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
    add(new JScrollPane(destList), new GridBagConstraints(3, 1, 1, 5, .5,
        1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        EMPTY_INSETS, 0, 0));
  }

  private class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      DefaultListModel<Users> selected = new DefaultListModel<>();
      for (int i = 0; i < sourceList.getModel().getSize(); i++) {
          selected.addElement(sourceList.getModel().getElementAt(i));
      }

      addDestinationElements(selected);
      clearSourceSelected();
    }
  }

  private class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      DefaultListModel<Users> selected = new DefaultListModel<>();
      for (int i = 0; i < destList.getModel().getSize(); i++) {
          selected.addElement(destList.getModel().getElementAt(i));
      }
      
      addSourceElements(selected);
      clearDestinationSelected();
    }
  }

  private class SortedListModel extends AbstractListModel {

    SortedSet<Users> model;

    public SortedListModel() {
      model = new TreeSet<>();
    }

    public int getSize() {
      return model.size();
    }

    public Object getElementAt(int index) {
      return model.toArray()[index];
    }

    public void add(Users element) {
      if (model.add(element)) {
        fireContentsChanged(this, 0, getSize());
      }
    }

    public void addAll(Users elements[]) {
      Collection<Users> c = Arrays.asList(elements);
      model.addAll(c);
      fireContentsChanged(this, 0, getSize());
    }

    public void clear() {
      model.clear();
      fireContentsChanged(this, 0, getSize());
    }

    public boolean contains(Users element) {
      return model.contains(element);
    }

    public Users firstElement() {
      return model.first();
    }

    public Iterator<Users> iterator() {
      return model.iterator();
    }

    public Users lastElement() {
      return model.last();
    }

    public boolean removeElement(Users element) {
      boolean removed = model.remove(element);
      if (removed) {
        fireContentsChanged(this, 0, getSize());
      }
      return removed;
    }
  }

}
