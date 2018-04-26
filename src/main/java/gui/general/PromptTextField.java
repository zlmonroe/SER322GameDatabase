package gui.general;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PromptTextField extends JTextField {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String prompt;
    private String initText;
    private boolean entered;


    public PromptTextField(String p) {
        prompt = p;
        entered = false;
        this.setText(prompt);
        initFocus();
        setOpaque(false);
    }

    public PromptTextField(String p, String i) {
        prompt = p;
        initText = i;
        entered = true;
        this.setText(initText);
        initFocus();
        setOpaque(false);
    }

    public boolean textEntered() {
        return entered;
    }

    private void initFocus() {
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (getText().equals(prompt)) {
                    setText("");
                    entered = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (getText().equals("")) {
                    setText(prompt);
                    entered = false;
                }
                else {
                    entered = true;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
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
