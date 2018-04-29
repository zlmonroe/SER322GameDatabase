package gui.general;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordPromptField extends JPasswordField {
    private static final long serialVersionUID = 1L;
    private String prompt;
    private String initText;
    private boolean entered;
    private boolean transparent;

    public PasswordPromptField(String p) {
        this.setEchoChar((char) 0);
        prompt = p;
        entered = false;
        this.setText(prompt);
        initFocus();
        setOpaque(false);
        this.setFont(new Font(this.getFont().getName(), Font.BOLD, 30));
        transparent = false;
    }

    public PasswordPromptField(String p, String i) {
        this.setEchoChar((char) 0);
        prompt = p;
        initText = i;
        entered = true;
        this.setText(initText);
        initFocus();
        setOpaque(false);
        transparent = false;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public boolean textEntered() {
        return entered;
    }

    private void initFocus() {
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (getText().equals(prompt)) {
                    setEchoChar('\u25CF');
                    setText("");
                    entered = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (getText().equals("")) {
                    setEchoChar((char) 0);
                    setText(prompt);
                    entered = false;
                }
                else {
                    entered = true;
                }
            }
        });
    }
    
    public void setPrompt(String t) {
        setEchoChar((char) 0);
        setText(prompt);
    }

    public String getEnteredText() {
        return textEntered() ? super.getText():"";
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if(transparent) {
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.65f));
        }
        super.paint(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g2d);
        g2d.dispose();
    }

}
