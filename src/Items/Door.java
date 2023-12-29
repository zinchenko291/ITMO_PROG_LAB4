package Items;

public class Door extends Item {
  private boolean isOpen = false;

  public Door() {
    super("Дверь");
  }

  public void openDoor() {
    this.isOpen = true;
    System.out.println("Дверь закрылась");
  }
  public void closeDoor() {
    this.isOpen = false;
    System.out.println("Дверь распахнулась");
  }
}
