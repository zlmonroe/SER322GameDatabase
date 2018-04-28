package gui.general;

import backend.CurrentContext;
import backend.sql.SQLActions.StringsLike;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * Created by zlmonroe on 4/27/2018.
 */
public class SearchField extends JComboBox<String> {
    private List<String> terms = new ArrayList<>();
    private String table;
    private String attribute;

    public SearchField(String table, String attribute) {
        this.table = table;
        this.attribute = attribute;
        this.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                update();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        this.setEditable(true);
        this.terms = new ArrayList<>();
    }

    public void update() {
        System.out.println("UPDATED DROP DOWN");
        String item = (String)this.getEditor().getItem();
        System.out.println(item);
        if (this.getSelectedItem() == null) {
            item = "";
        }
        this.removeAllItems();
        this.search(item);
    }

    private void search(String value) {
        if (value == null) {
            value = "";
        }
        ResultSet rs = CurrentContext.gameServer.querry(new StringsLike(this.table, this.attribute,
                value));
        try {
            while(rs.next()) {
                this.addItem(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
