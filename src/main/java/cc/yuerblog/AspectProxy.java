package cc.yuerblog;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AspectProxy implements MethodInterceptor {
  // 注册的切面对象
  private Map<String, ArrayList<AspectInterface>>  aspectMap;

  AspectProxy() {
    aspectMap = new HashMap<String, ArrayList<AspectInterface>>();
  }

  /**
   * 创建目标的代理对象
   * @param superCls
   * @param <T>
   * @return
   */
  public <T> T buildTargetProxy(Class<T> superCls) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(superCls);
    enhancer.setCallback(this);
    return (T)enhancer.create();
  }

  /**
   * 注册一个切面到代理对象
   * @param method
   * @param aspect
   */
  public void addAspect(String method, AspectInterface aspect) {
    if (!aspectMap.containsKey(method)) {
      ArrayList list = new ArrayList();
      list.add(aspect);
      aspectMap.put(method, list);
    } else {
      aspectMap.get(method).add(aspect);
    }
  }

  /**
   * 代理对象接管目标的方法调用, 实施切面
   * @param o
   * @param method
   * @param objects
   * @param methodProxy
   * @return
   * @throws Throwable
   */
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    // joinPoint连接点: before
    ArrayList<AspectInterface> aspectArr = aspectMap.get(method.getName());
    if (aspectArr != null) {
      for (AspectInterface aspect : aspectArr) {
        aspect.before(new JoinPoint(o, method));
      }
    }

    // 调用目标的方法
    Object ret = methodProxy.invokeSuper(o, objects);

    // joinPoint连接点: after
    if (aspectArr != null) {
      for (AspectInterface aspect : aspectArr) {
        aspect.after(new JoinPoint(o, method));
      }
    }

    return ret;
  }
}
