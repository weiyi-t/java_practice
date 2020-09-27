package producers;

public class DigitCombinationsProducer implements StringProducer {

  private String curr;

  public DigitCombinationsProducer() {
    curr = "";
  }

  @Override
  public String next() {
    String result = curr;
    curr = update(curr);
    return result;
  }

  private String update(String s) {
    if (s.equals("")) {
      return "4";
    }
    switch(s.charAt(s.length() - 1)) {
      case '4':
        return s.substring(0, s.length() - 1) + '5';
      case '5':
        return s.substring(0, s.length() - 1) + '6';
      case '6':
        return s.substring(0, s.length() - 1) + '7';
      case '7':
        return update(s.substring(0, s.length() - 1)) + '4';
      default:
        assert false;
        return null;
    }
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}
