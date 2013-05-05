package world;

import battle.BattleBox;
import chat.ChatBox;
import data.DataBox;

public class Box {
	public final static ChatBox chat = new ChatBox();
	public final static DataBox data = new DataBox();
	public final static BattleBox battle = new BattleBox();
	public final static BoardBox board = new BoardBox();
}
