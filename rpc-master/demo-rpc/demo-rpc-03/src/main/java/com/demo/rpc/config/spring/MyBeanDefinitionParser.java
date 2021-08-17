package com.demo.rpc.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 10:41
 */
public class MyBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    MyBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        //bean定义 类
        beanDefinition.setBeanClass(beanClass);
        //懒加载
        beanDefinition.setLazyInit(false);
        //bean 名字
        String beanName = element.getAttribute("id");
        //注册
        parserContext.getRegistry().registerBeanDefinition(beanName,beanDefinition);
        for (Method method : beanClass.getMethods()) {
            if(!isProperty(method,beanClass)){
                continue;
            }
            String name = method.getName();
            String methodName  = name.substring(3,4).toLowerCase() + name.substring(4);
            String value = element.getAttribute(methodName);
            beanDefinition.getPropertyValues().addPropertyValue(methodName,value);
        }
        return beanDefinition;
    }


    private boolean isProperty(Method method,Class beanClass){
        String methodName = method.getName();
        boolean flag = methodName.length() > 3
                && methodName.startsWith("set")
                && Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length ==1;
        Method getter = null;
        if(!flag){
            return false;
        }
        Class<?> type = method.getParameterTypes()[0];
        try
        {
            getter = beanClass.getMethod("get"+methodName.substring(3));
        }
        catch (NoSuchMethodException e)
        {

        }

        if(null == getter){
            try
            {
                //2 ?
                getter = beanClass.getMethod("is"+methodName.substring(3));
            }
            catch (NoSuchMethodException e)
            {

            }

        }

        flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());
        return flag;
    }
}
