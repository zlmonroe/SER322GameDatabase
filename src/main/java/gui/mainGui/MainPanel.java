package gui.mainGui;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JPanel;

import backend.CurrentContext;
import gui.panels.*;

public class MainPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CardLayout layout;

    private JPanel home;
    private JPanel signup;
    private JPanel profile;
    private JPanel profileEdit;
    private JPanel characters;
    private JPanel community;
    private JPanel wiki;

    /**
     * Creates the panel for the center of the frame.
     * @throws IOException 
     */
    public MainPanel() throws IOException {		
        layout = new CardLayout();
        this.setLayout(layout);

        home = new HomePanel(this);
        signup = new SignupPanel(this);
        profile = new ProfilePanel(this);
        profileEdit = new ProfileEditPanel(this);
        characters = new MyCharactersPanel();
        community = new CommunityPanel();
        wiki = new GameWikiPanel();

        this.add(home, "HOME");
        this.add(signup, "SIGNUP");
        this.add(profile, "PROFILE");
        this.add(profileEdit, "EDIT");
        this.add(characters, "CHARACTERS");
        this.add(community, "COMMUNITY");
        this.add(wiki, "WIKI");

        layout.show(this, "HOME");

    }

    /**
     * Switches the panel to display the proper panel.
     * @param panelName name of the panel to switch to 
     * @throws IOException 
     */
    public void switchPanel(String panelName){
        try {
            refresh();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        layout.show(this, panelName);
    }

    public void refresh() throws IOException {
        profile = new ProfilePanel(this);
        profileEdit = new ProfileEditPanel(this);
        characters = new MyCharactersPanel();
        signup = new SignupPanel(this);
        this.add(profile, "PROFILE");
        this.add(profileEdit, "EDIT");
        this.add(characters, "CHARACTERS");
        this.add(signup, "SIGNUP");
        ((CommunityPanel)this.community).updateSQL();
    }
}
