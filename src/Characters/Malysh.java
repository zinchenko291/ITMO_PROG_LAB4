package Characters;

import Entity.Entity;
import Items.Item;

public class Malysh extends Character {
  private final Mouth mouth = new Mouth(32);
  public Malysh() {
    super("Малыш", Gender.MAN, Feels.NORMAL);
  }
  private class Mouth {
    private final int numsOfTeeth;
    Mouth(int numsOfTeeth) {
      this.numsOfTeeth = numsOfTeeth;
    }
    public void speak(String speech) {
      if (speech == null) {
        Malysh.this.setState(String.format("%s ничего не сказал", Malysh.this.getGender()));
        return;
      }
      if (numsOfTeeth < 8) {
        Malysh.this.setState(String.format("%s что-то пробормотал", Malysh.this.getGender()));
        return;
      }
      Malysh.this.setState(String.format("%s сказал: \"%s\"", Malysh.this.getName(), speech));
    }
  }

  public void speak(String speach) {
    this.mouth.speak(speach);
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
