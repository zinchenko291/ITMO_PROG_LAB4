package StoryTeller;

import Characters.*;
import Characters.Character;
import Items.Container;
import Items.Door;
import Items.Item;

public class StoryTeller {
  // Персонажи
  private final Karlson karlson = new Karlson();
  private final FrenBok frenBok = new FrenBok();
  private final Malysh malysh = new Malysh();

  // Предметы
  private final Item kitchenHob = new Item("плита");
  private final Item meatball = new Item("тефтелька");
  private final Container fryingPan = new Container("сковородка", meatball, frenBok);
  private final Door door = new Door();

  public void play() {
    karlson.setFeel(Feels.SCARY);
    System.out.println(karlson.getState());
    karlson.setFeel(Feels.SAD);
    System.out.println(karlson.getState());
    karlson.moveTo(kitchenHob);
    System.out.println(karlson.getState());
    try {
      karlson.grabFrom(fryingPan);
      System.out.println(karlson.getState());
      frenBok.setFeel(Feels.NORMAL);
      System.out.println(frenBok.getState());
    } catch (OwnershipException e) {
      System.out.println(e.getMessage());
      System.out.println("Вот этого ему не следовало делать");
      frenBok.setFeel(Feels.RAGE);
      System.out.println(frenBok.getState());
      frenBok.moveTo(karlson);
      System.out.println(frenBok.getState());
      frenBok.grabCharacter(karlson);
      System.out.println(frenBok.getState());
      frenBok.pushCharacterBehindItem(door);
      System.out.println(frenBok.getState());
      malysh.setFeel(Feels.DEAD_INSIDE);
      System.out.println(malysh.getState());
      malysh.speak(null);
      System.out.println(malysh.getState());
      door.closeDoor();
      karlson.moveTo(door);
      System.out.println(karlson.getState());
      karlson.setFeel(Feels.ANGRY);
      System.out.println(karlson.getState());
      karlson.flyTo(frenBok);
      System.out.println(karlson.getState());
      karlson.stomp();
      System.out.println(karlson.getState());
    }
  }
}
