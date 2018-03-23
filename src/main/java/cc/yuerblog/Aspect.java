package cc.yuerblog;

// 切面
interface AspectInterface {
  public  void before(JoinPoint joinPoint);
  public  void after(JoinPoint joinPoint);
}

// 这里可以基于注解声明切面要连接到哪些目标, 例如（下面是伪代码）：
// @execution(class="cc.yuerblog.AspectTarget", method={"aMethod","bMethod"})
// 该注解也称为：PointCut切点，意思是切入哪些bean的哪些方法
public class Aspect implements AspectInterface {
  public void before(JoinPoint joinPoint) {
    System.out.println("before: " + joinPoint.object.getClass().getSuperclass().getName()+ "." + joinPoint.method.getName());
  }

  public void after(JoinPoint joinPoint) {
    System.out.println("after: " + joinPoint.object.getClass().getSuperclass().getName() + "." + joinPoint.method.getName());
  }
}
