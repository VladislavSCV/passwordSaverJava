import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class write_password_form extends JFrame{
    private JPanel mainpanel;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label2;
    private JLabel label1;
    private JButton return_all_password;

    public write_password_form() {

        setContentPane(mainpanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(350, 300);
        setTitle("Password Memorizer");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db_handler db_h = new db_handler();

                String service = textField1.getText();
                String password = textField2.getText();

                if ((service.trim() == "") || (password.trim() == "")){
                    textField2.setText("Произошла ошибка!");
                    System.out.println("Введены пробелы");

                }else{
                    try {
                        db_h.add_passw_to_db(service, password);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        return_all_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                write_password_form.this.setVisible(false);
                new all_passwords().setVisible(true);
            }
        });
    }
}
