//package emailapp;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;


public class EmailAppSwing extends JFrame {

	
	/*public static void main(String[] args) {
	    SwingUtilities.invokeLater(EmailAppSwing::new);
	*///}

    private JTextField firstName, lastName, altEmail;
    private JComboBox<String> deptBox;
    private JSpinner capacity;

    private JPasswordField newPwd, confirmPwd;
    private JButton savePwdBtn;

    private JTextField emailField;
    private JLabel passwordLabel;
    private JButton toggleBtn;

    private Email email;
    private boolean showPwd = false;

    public EmailAppSwing() {

        setTitle("Email Administration System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(20,20));
        root.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
        root.setBackground(new Color(245,247,250));
        add(root);

        /* ================= FORM PANEL ================= */
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(20,20,20,20)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.anchor = GridBagConstraints.WEST;

        addRow(form,g,0,"First Name", firstName = new JTextField(16));
        addRow(form,g,1,"Last Name", lastName = new JTextField(16));

        g.gridx=0; g.gridy=2;
        form.add(new JLabel("Department"),g);
        g.gridx=1;
        deptBox = new JComboBox<>(new String[]{"sales","developer","accnts","general"});
        form.add(deptBox,g);

        addRow(form,g,3,"Mailbox Capacity",
                capacity = new JSpinner(new SpinnerNumberModel(500,100,2000,100)));
        addRow(form,g,4,"Alternate Email",
                altEmail = new JTextField(16));

        // ===== PASSWORD SECTION =====
        addRow(form,g,5,"New Password", newPwd = new JPasswordField(16));
        addRow(form,g,6,"Confirm Password", confirmPwd = new JPasswordField(16));

        savePwdBtn = new JButton("Save Password");
        savePwdBtn.setEnabled(false);
        savePwdBtn.addActionListener(e -> savePassword());

        g.gridx=1; g.gridy=7;
        form.add(savePwdBtn,g);

        JButton createBtn = new JButton("Create Email");
        createBtn.setPreferredSize(new Dimension(160,38));
        createBtn.setBackground(new Color(79,70,229));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);
        createBtn.addActionListener(e -> createEmail());

        g.gridy=8;
        form.add(createBtn,g);

        root.add(form, BorderLayout.NORTH);

        /* ================= GENERATED DETAILS ================= */
        JPanel details = new JPanel(new GridBagLayout());
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createTitledBorder("Generated Details"));

        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(10,10,10,10);
        d.anchor = GridBagConstraints.WEST;

        d.gridx=0; d.gridy=0;
        details.add(new JLabel("Email:"), d);

        d.gridx=1;
        emailField = new JTextField(30);
        emailField.setEditable(false);
        details.add(emailField,d);

        JButton copyBtn = new JButton("Copy");
        copyBtn.addActionListener(e -> copy(emailField.getText()));
        d.gridx=2;
        details.add(copyBtn,d);

        d.gridx=0; d.gridy=1;
        details.add(new JLabel("Password:"),d);

        passwordLabel = new JLabel("********");
        d.gridx=1;
        details.add(passwordLabel,d);

        toggleBtn = new JButton("Show");
        toggleBtn.setEnabled(false);
        toggleBtn.addActionListener(e -> togglePassword());
        d.gridx=2;
        details.add(toggleBtn,d);

        root.add(details, BorderLayout.CENTER);

        setVisible(true);
    }

    /* ================= LOGIC ================= */
    private void createEmail() {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Enter first & last name");
            return;
        }

        email = new Email(
                firstName.getText(),
                lastName.getText(),
                deptBox.getSelectedItem().toString()
        );

        email.setMailboxCapacity((int)capacity.getValue());
        email.setAlternateEmail(altEmail.getText());

        emailField.setText(email.getEmail());
        passwordLabel.setText("********");
        toggleBtn.setEnabled(true);
        savePwdBtn.setEnabled(true);
        showPwd = false;
    }

    private void savePassword() {
        String p1 = new String(newPwd.getPassword());
        String p2 = new String(confirmPwd.getPassword());

        if(p1.length() < 6) {
            JOptionPane.showMessageDialog(this,"Password must be at least 6 characters");
            return;
        }
        if(!p1.equals(p2)) {
            JOptionPane.showMessageDialog(this,"Passwords do not match");
            return;
        }

        email.changePassword(p1);
        newPwd.setText("");
        confirmPwd.setText("");
        passwordLabel.setText("********");

        JOptionPane.showMessageDialog(this,"Password saved successfully");
    }

    private void togglePassword() {
        showPwd = !showPwd;
        passwordLabel.setText(showPwd ? email.getPassword() : "********");
        toggleBtn.setText(showPwd ? "Hide" : "Show");
    }

    private void copy(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(text), null);
        JOptionPane.showMessageDialog(this,"Email copied");
    }

    private void addRow(JPanel p, GridBagConstraints g, int y,
                        String label, JComponent field) {
        g.gridx=0; g.gridy=y;
        p.add(new JLabel(label),g);
        g.gridx=1;
        p.add(field,g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmailAppSwing::new);
    }
}
