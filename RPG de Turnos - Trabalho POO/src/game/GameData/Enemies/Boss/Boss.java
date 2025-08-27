package game.GameData.Enemies.Boss;

import game.GameData.Items.Item;

public interface Boss {
    void EntraceText();
    Item getDropItem();
    int getDropMoney();

}
