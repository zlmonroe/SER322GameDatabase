package gui.general;

import javax.swing.JTextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PromptTextField extends JTextField{
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
    }
    
    public PromptTextField(String p, String i) {
        prompt = p;
        initText = i;
        entered = true;
        this.setText(initText);
        initFocus();
    }
    
    public boolean textEntered() {
        return entered;
    }
    
    private void initFocus() {
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(getText().equals(prompt)) {
                    setText("");
                    entered = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if(getText().equals("")) {
                    setText(prompt);
                    entered = false;
                }
                else {
                    entered = true;
                }
            }
        });
    }

}
