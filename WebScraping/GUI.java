package WebScraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

    public class GUI extends JPanel implements ActionListener {

        private JLabel Input;
        private JTextField textBox;
        private JButton submit;
        private String url;
        public static JFrame frame;
        public static JTextArea textArea;

        public GUI()
        {
            Input = new JLabel("Enter URL: ");
            textBox = new JTextField();
            textBox.setPreferredSize(new Dimension(300,20));
            submit = new JButton("Submit");
            submit.addActionListener(this::actionPerformed);
            textArea = new JTextArea();
            textArea.setPreferredSize(new Dimension(500,120));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setBackground(new Color(240, 240, 240));
            textArea.setBorder(BorderFactory.createLoweredBevelBorder());

            add(Input);
            add(textBox);
            add(submit);
            add(textArea);

            this.setBackground(Color.gray);
            this.setPreferredSize(new Dimension(500,150));

        }

        public void actionPerformed(ActionEvent a)
        {
            String value;
            try {
                value = textBox.getText();
            } catch (Exception EH) {
                JOptionPane.showMessageDialog(frame, "Try Again");
                textBox.requestFocusInWindow();
                textBox.selectAll();
                return;
            }
            String x[] = new Main().getText();
            for(String y: x) {
                textArea.append("Match Found " + y + "\n");
                submit.setEnabled(false);
            }

        }
}
