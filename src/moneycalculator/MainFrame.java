package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.control.Command;
import moneycalculator.model.Currency;
import moneycalculator.model.ui.MoneyDialog;
import moneycalculator.model.ui.MoneyDisplay;
import moneycalculator.model.ui.swing.SwingMoneyDialog;
import moneycalculator.model.ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame {
    
    private final Currency[] currencies;
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    
    
    public MainFrame(Currency[] currencies) {
        this.currencies = currencies;
        this.setTitle("Money Calculator");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(moneyDialog(), BorderLayout.NORTH);
        this.add(moneyDisplay(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
        this.setVisible(true);
        
    }
    public void add(Command command){
        commands.put(command.name(), command);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }
    
    private Component moneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        this.moneyDialog = dialog;
        return dialog;
    }    

    private Component moneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(calculateButton());
        return panel;
    }

    private Component calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        };

    }
    
    
}
