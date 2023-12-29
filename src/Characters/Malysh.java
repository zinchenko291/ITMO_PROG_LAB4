package Characters;

import Entity.Entity;
import Items.Item;

public class Malysh extends Character {
  public Malysh() {
    super("Малыш", Gender.MAN ,Feels.NORMAL);
  }

  public void speak(String speech) {
    if (speech == null) {
      this.setState(String.format("%s ничего не сказал", this.getGender()));
      return;
    }
    this.setState(String.format("%s сказал: \"%s\"", this.getName(), speech));
  }

  @Override
  public void moveTo(Entity obj) {
    this.setPosition(obj);
    this.setState(String.format("%s подошёл к %s", this.getGender(), obj));
  }

  @Override
  public void grab(Item obj) throws OwnershipException {
    this.itemInHand = obj;
    this.setState(String.format("%s присвоил себе %s", this.getGender(), obj));
    if (obj.getOwner() != this && obj.getOwner() != null) {
      throw new OwnershipException(String.format("Предмет %s принадлежит %s, а не %s", obj.getName(), obj.getOwner().getName(), this.getName()));
    }
  }
}
