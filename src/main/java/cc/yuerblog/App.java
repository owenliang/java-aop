package cc.yuerblog;

public class App 
{
    public static void main( String[] args ) {
      // 1, 目标的代理对象（任何被切的目标对象都应该分配一个代理对象，从而实施切面）
      AspectProxy proxy = new AspectProxy();

      // 2, 创建一个切面
      AspectInterface aspect = new Aspect();

      // 3, 为目标的某些方法注册切面（这一步应该反射Aspect.java中注解，识别出切面要注入给哪些类）
      proxy.addAspect("aMethod", aspect);
      proxy.addAspect("bMethod", aspect);

      // 4, 生成被代理的目标对象
      AspectTarget target = proxy.buildTargetProxy(AspectTarget.class);

      // 5, 调用目标的方法，对应切面得到回调
      target.aMethod();
      target.bMethod();
      target.cMethod();

      // 步骤1,2,3均可以基于注解+运行时反射自动实现, 这里就不演示了
    }
}
