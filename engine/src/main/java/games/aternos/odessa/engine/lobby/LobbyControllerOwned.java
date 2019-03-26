package games.aternos.odessa.engine.lobby;

abstract public class LobbyControllerOwned {

  private LobbyController owner;

  public LobbyControllerOwned(LobbyController owner) {
    this.owner = owner;
  }

  public LobbyController getOwner() {
    return owner;
  }
}
