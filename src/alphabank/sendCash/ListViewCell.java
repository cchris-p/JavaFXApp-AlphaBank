/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabank.sendCash;

import javafx.scene.control.ListCell;

/**
 *
 * @author pyles
 */
public class ListViewCell extends ListCell<String>
{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null)
        {
            Data data = new Data();
            data.setInfo(string);
            setGraphic(data.getBox());
        }
    }
}