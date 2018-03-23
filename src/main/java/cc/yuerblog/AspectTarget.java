package cc.yuerblog;

public class AspectTarget {

  // 被切入
  public String aMethod() {
    System.out.println("aMethod");
    return "aMethod";
  }

  // 被切入
  public String bMethod() {
    System.out.println("bMethod");
    return "bMethod";
  }

  // 未被切入
  public String cMethod() {
    System.out.println("cMethod");
    return "cMethod";
  }
}
