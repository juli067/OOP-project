package sb;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Billi {

    public static void main(String[] args) {

        // ---------------- LOGIN PAGE -----------------
        JFrame frame = new JFrame();
        frame.setTitle("Login page");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(211, 211, 211));
        frame.setLocationRelativeTo(null);

        JLabel itemLabel = new JLabel("Username");
        itemLabel.setFont(new Font("Serif", Font.BOLD, 16));
        itemLabel.setBounds(190, 70, 100, 25);
        frame.add(itemLabel);

        JTextField itemField = new JTextField();
        itemField.setBounds(300, 70, 200, 25);
        frame.add(itemField);

        JLabel qtyLabel = new JLabel("Password");
        qtyLabel.setFont(new Font("Serif", Font.BOLD, 16));
        qtyLabel.setBounds(190, 110, 100, 25);
        frame.add(qtyLabel);

        JPasswordField qtyField = new JPasswordField();
        qtyField.setBounds(300, 110, 200, 25);
        frame.add(qtyField);

        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setBounds(300, 150, 150, 25);
        frame.add(showPass);

        showPass.addActionListener(e -> {
            if (showPass.isSelected()) qtyField.setEchoChar((char) 0);
            else qtyField.setEchoChar('*');
        });

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 200, 90, 30);
        frame.add(loginButton);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(410, 200, 90, 30);
        frame.add(closeButton);

        closeButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);



        // ---------------- AFTER LOGIN -----------------
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = itemField.getText();
                String pass = new String(qtyField.getPassword());

                if (user.equals("juli") && pass.equals("067")) {

                    frame.dispose();

                    // Gradient Panel
                    JPanel gradientPanel = new JPanel() {
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D) g;
                            int w = getWidth();
                            int h = getHeight();
                            Color c1 = new Color(173, 216, 230);
                            Color c2 = new Color(255, 255, 255);
                            GradientPaint gp = new GradientPaint(0, 0, c1, 0, h, c2);
                            g2d.setPaint(gp);
                            g2d.fillRect(0, 0, w, h);
                        }
                    };
                    gradientPanel.setLayout(null);

                    JFrame billFrame = new JFrame("Billing Page");
                    billFrame.setSize(800, 800);
                    billFrame.setContentPane(gradientPanel);
                    billFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    billFrame.setLocationRelativeTo(null);

                    JLabel title = new JLabel("Smart Billing System");
                    title.setBounds(250, 30, 400, 40);
                    title.setFont(new Font("Serif", Font.BOLD, 26));
                    gradientPanel.add(title);

                    // -------------- INPUT FIELDS --------------
                    JLabel itemL = new JLabel("Item Name:");
                    itemL.setBounds(100, 120, 120, 30);
                    gradientPanel.add(itemL);

                    JTextField itemF = new JTextField();
                    itemF.setBounds(230, 120, 200, 30);
                    gradientPanel.add(itemF);

                    JLabel qtyL = new JLabel("Quantity:");
                    qtyL.setBounds(100, 170, 120, 30);
                    gradientPanel.add(qtyL);

                    JTextField qtyF = new JTextField();
                    qtyF.setBounds(230, 170, 200, 30);
                    gradientPanel.add(qtyF);

                    JLabel priceL = new JLabel("Price:");
                    priceL.setBounds(100, 220, 120, 30);
                    gradientPanel.add(priceL);

                    JTextField priceF = new JTextField();
                    priceF.setBounds(230, 220, 200, 30);
                    gradientPanel.add(priceF);

                    JLabel totalL = new JLabel("Total:");
                    totalL.setBounds(100, 270, 120, 30);
                    gradientPanel.add(totalL);

                    JTextField totalF = new JTextField();
                    totalF.setBounds(230, 270, 200, 30);
                    totalF.setEditable(false);
                    gradientPanel.add(totalF);

                    // NEW FIELD → After discount
                    JLabel finalL = new JLabel("After Discount Price:");
                    finalL.setBounds(100, 320, 160, 30);
                    gradientPanel.add(finalL);

                    JTextField finalF = new JTextField();
                    finalF.setBounds(260, 320, 200, 30);
                    finalF.setEditable(false);
                    gradientPanel.add(finalF);

                    JLabel recommendLabel = new JLabel("Discount Recommendation Will Appear Here");
                    recommendLabel.setBounds(100, 380, 500, 30);
                    recommendLabel.setForeground(Color.BLUE);
                    gradientPanel.add(recommendLabel);

                    JButton calcBtn = new JButton("Recommend Discount");
                    calcBtn.setBounds(480, 170, 200, 40);
                    gradientPanel.add(calcBtn);

                    JButton billBtn = new JButton("Generate Bill");
                    billBtn.setBounds(480, 240, 200, 40);
                    gradientPanel.add(billBtn);


                    // -------- DISCOUNT LOGIC --------
                    calcBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int qty = Integer.parseInt(qtyF.getText());
                                int price = Integer.parseInt(priceF.getText());
                                int total = qty * price;
                                totalF.setText(String.valueOf(total));

                                double discount = 0;
                                String msg = "";

                                if (total >= 5000) { discount = 0.15; msg = "15% Discount"; }
                                else if (total >= 2000) { discount = 0.10; msg = "10% Discount"; }
                                else if (total >= 1000) { discount = 0.05; msg = "5% Discount"; }
                                else msg = "No main discount.";

                                if (qty >= 5) {
                                    discount += 0.07;
                                    msg += " + 7% Quantity Discount";
                                }

                                recommendLabel.setText(msg);

                                double finalPrice = total - (total * discount);
                                finalF.setText(String.valueOf(finalPrice));

                            } catch (Exception ex) {
                                recommendLabel.setText("Please enter valid numbers!");
                            }
                        }
                    });


                    billBtn.addActionListener(e2 -> {
                        String info =
                                "Item: " + itemF.getText() + "\n" +
                                "Quantity: " + qtyF.getText() + "\n" +
                                "Price: " + priceF.getText() + "\n" +
                                "Total: " + totalF.getText() + "\n" +
                                "After Discount: " + finalF.getText();

                        JOptionPane.showMessageDialog(billFrame, info, "Final Bill",
                                JOptionPane.INFORMATION_MESSAGE);
                    });

                    billFrame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password");
                }
            }
        });
    }


}
