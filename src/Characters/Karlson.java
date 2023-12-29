package Characters;

import Entity.Entity;
import Items.Container;
import Items.Item;

public class Karlson extends Character implements IGrabbable, IFlyable {
  public Karlson() {
    super("Кралсон", Gender.MAN);
  }

  public Karlson(Feels feel) {
    this();
    this.feel = feel;
  }

  @Override
  public void moveTo(Entity obj) {
    this.setPosition(obj);
    this.setState(String.format("%s сделал шаг к %s", this.getGender(), obj));
  }

  public void flyTo(Entity obj) {
    this.moveTo(obj);
    this.setState(String.format("%s подлетел к %s", this.getGender(), obj));
  }

  @Override
  public void grab(Item obj) throws OwnershipException {
    this.itemInHand = obj;
    this.setState(String.format("%s взял %s", this.getGender(), obj));
    if (obj.getOwner() != this && obj.getOwner() != null) {
      throw new OwnershipException(String.format("Предмет %s принадлежит %s, а не %s", obj.getName(), obj.getOwner().getName(), this.getName()));
    }
  }

  public void grabFrom(Container container) throws OwnershipException {
    this.grab(container.getInnerItem());
    this.setState(String.format("%s взял одну %s из %s", this.getName(), container.getInnerItem(), container));
  }

  @Override
  public String getGrabbablePart() {
    return "шиворот";
  }

  public void stomp() {
    this.setState(String.format("%s топнул ногой", this.getName()));
  }
}
