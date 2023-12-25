import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new MainFrame();
    }
}

class MainFrame extends JFrame {
    final Item[] items = new Item[]{
            new Item("Item 1", 500),
            new Item("Item 2", 600),
            new Item("Item 3", 300),
            new Item("Item 4", 1000),
            new Item("Item 5", 200)
    };

    MainFrame() {
        this.setTitle("Hopelist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);

        JList<Item> list = new JList<>(items);

        DefaultListModel<Item> hopeListModel = new DefaultListModel<>();
        JList<Item> hopeList = new JList<>(hopeListModel);

        JComboBox<Integer> amount = new JComboBox<>();
        amount.setPreferredSize(new Dimension(60, 20));
        for(int i = 0; i < 4; i++) {
            amount.addItem(i+1);
        }

        // 加入按鈕
        JButton addBtn = new JButton("ADD");
        addBtn.addActionListener(event-> {
            if (list.getSelectedValue()==null) {
                JOptionPane.showMessageDialog(this, "You have to select a item.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                hopeListModel.addElement(new Item(list.getSelectedValue().name, list.getSelectedValue().price * (Integer) amount.getSelectedItem(), (Integer) amount.getSelectedItem()));
                list.clearSelection();
            }
        });

        // 刪除按鈕
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> {
            if (hopeList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "You have to select a item.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                hopeListModel.remove(hopeListModel.indexOf(hopeList.getSelectedValue()));
            }
        });

        // 結帳按鈕
        JButton sumBtn = new JButton("Order");
        sumBtn.addActionListener(e -> {
            int sum = 0;
            for (int i = 0; i < hopeListModel.size(); i++) {
                sum += hopeListModel.elementAt(i).price;
            }
            JOptionPane.showMessageDialog(this, "Total:" + sum);
        });

        JPanel listPanel = new JPanel();
        listPanel.add(new JLabel("Item List"));
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(270, 270));
        listPanel.add(new JScrollPane(listScrollPane));
        listPanel.add(new JLabel("Your Hope list"));
        listPanel.add(new JScrollPane(hopeList));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(amount);
        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(sumBtn);

        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
    }


}