package games.aternos.odessa.engine.lobby;

/**
 * Super for classes owned by the LobbyController
 */
abstract public class LobbyControllerOwned {

  /**
   * The LobbyController that owns the class
   */
  private final LobbyController owner;

  /**
   * Superconstructor for lobby owned classes
   *
   * @param owner The LobbyController Owner
   */
  protected LobbyControllerOwned(LobbyController owner) {
    this.owner = owner;
  }

  /**
   * @return The owning lobby controller
   */
  public LobbyController getOwner() {
    return owner;
  }
}
