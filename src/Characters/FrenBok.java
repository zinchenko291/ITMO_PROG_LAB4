package Characters;

import Entity.Entity;
import Items.Item;

public class FrenBok extends Character {
  private IGrabbable characterInHand;

  public FrenBok() {
    super("Фрекен Бок", Gender.WOMAN);
  }

  @Override
  public void moveTo(Entity obj) {
    this.setPosition(obj);
    this.setState(String.format("%s кинулась к %s", this.getGender(), obj.getName()));
  }

  @Override
  public void grab(Item obj) throws OwnershipException {
    this.itemInHand = obj;
    this.setState(String.format("%s взяла %s", this.getGender(), obj));
    if (obj.getOwner() != this && obj.getOwner() != null) {
      throw new OwnershipException(String.format("Предмет %s принадлежит %s, а не %s", obj.getName(), obj.getOwner().getName(), this.getName()));
    }
  }

  public void grabCharacter(IGrabbable character) {
    this.characterInHand = character;
    this.setState(String.format("%s схватила %s за %s", this.getGender(), character, character.getGrabbablePart()));
  }

  public void pushCharacterBehindItem(Item item) {
    this.characterInHand.moveTo(item);
    this.setState(String.format("%s вытолкнула %s за %s", this.getGender(), this.characterInHand, item));
    this.characterInHand = null;
  }
}
