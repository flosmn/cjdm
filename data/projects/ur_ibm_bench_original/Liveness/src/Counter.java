public class Counter {
  private int number;
  public Counter(int num) {
    number = num;
  }

  public void inc() {
    number++;
  }

  public void dec() {
    number--;
  }

  public int get() {
    return number;
  }
}