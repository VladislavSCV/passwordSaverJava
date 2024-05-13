import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class all_passwords extends JFrame {
    private JPanel mainpanel2;
    private JButton button1;
    private JTable table1;
    private DefaultTableModel tableModel;

    public all_passwords() {
        setContentPane(mainpanel2);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(350, 300);
        setTitle("Password Memorizer");
//        Место начала сбора данных из бд
        try {
            String url = "jdbc:mysql://" + db_config.dbHost + ":" + db_config.dbPort + "/" + db_config.dbName;
            String usernameDB = "root";
            String passwordDB = "Vladislav113400";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM saved_password");

                List<String> arr = new ArrayList<>();

                while (resultSet.next()) {
                    String service = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String[] arr_sp = {service, password};
                    arr.add(Arrays.toString(arr_sp));
                }

                tableModel = new DefaultTableModel();
                table1.setModel(tableModel);
                tableModel.addColumn("SERVICE");
                tableModel.addColumn("PASSWORD");

                for (String i : arr) {
                    String[] objArr = i.substring(1, i.length() - 1).split(", ");
                    tableModel.addRow(objArr);
                }

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println();
                all_passwords.this.setVisible(false);
                new write_password_form().setVisible(true);

            }
        });
    }
}