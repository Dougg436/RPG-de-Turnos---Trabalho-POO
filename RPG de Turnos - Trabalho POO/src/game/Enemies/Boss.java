package game.Enemies;

import game.Items.Item;

public interface Boss {
    void EntraceText();
    Item getDropItem();
    int getDropMoney();

}
