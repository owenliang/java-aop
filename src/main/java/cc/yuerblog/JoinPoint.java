package cc.yuerblog;

import java.lang.reflect.Method;

// JoinPoint连接点
// 主要用于告知切面对象，当前是哪个对象的哪个方法被调用
public class JoinPoint {
  JoinPoint(Object obj, Method method) {
    this.object = obj;
    this.method = method;
  }

  public Method method;
  public Object object;
}
