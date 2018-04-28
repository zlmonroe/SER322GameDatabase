package gui.general;

import backend.CurrentContext;
import backend.sql.SQLActions.StringsLike;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;

/**
 * Created by zlmonroe on 4/27/2018.
 */
public class SearchField extends JComboBox<String> implements ChangeListener {
    private List<String> terms = new ArrayList<>();
    private String table;
    private String attribute;

    public SearchField(String table, String attribute) {
        this.table = table;
        this.attribute = attribute;
        this.setEditable(true);
    }

    public void update() {
        System.out.println("UPDATED DROP DOWN");
        this.terms.addAll(this.search(this.getSelectedItem().toString()));
    }

    private Collection<String> search(String value) {
        List<String> found = new ArrayList<>();
        ResultSet rs = CurrentContext.gameServer.querry(new StringsLike(this.table, this.attribute,
                value));
        try {
            while(rs.next()) {
                found.add(rs.getString(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.update();
    }
}
