package gui.general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PasswordTextField extends PromptTextField{
    
    ArrayList<Character> holder;

    public PasswordTextField(String p) {
        super(p);
        holder = new ArrayList<Character>();
        makeInvisible();
    }
    
    public PasswordTextField(String p, String i) {
        super(p, i);
        holder = new ArrayList<Character>();
        makeInvisible();
    }
    
    @Override
    public String getEnteredText() {
        String actualPassword = "";
        for(int i = 0; i < holder.size(); i++)
            actualPassword += (""+holder.get(i));
        return actualPassword;
    }
    
    private void makeInvisible() {
        addKeyListener(new KeyListener() {
            String tmp2 = "";
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (holder.size() > 0)
                        holder.remove(holder.size() - 1);
                }
                else {
                    String tmp = "";
                    holder.add(e.getKeyChar());
                    for(int i = 0; i < getText().length(); i++)
                        tmp += (""+'\u25CF');
                    setText(tmp);
                    tmp2 = tmp;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
               if(getText().length() != 0 && getText().charAt(getText().length()-1) != '\u25CF' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                   tmp2 += '\u25CF';
                   setText(tmp2);
               }
            }
        });
    }

}
