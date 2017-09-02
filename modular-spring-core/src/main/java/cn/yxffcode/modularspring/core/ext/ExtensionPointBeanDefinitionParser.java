package cn.yxffcode.modularspring.core.ext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author gaohang on 7/9/17.
 */
public class ExtensionPointBeanDefinitionParser implements BeanDefinitionParser {
  private final BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

  @Override
  public BeanDefinition parse(Element element, ParserContext parserContext) {
    final String extensionName = element.getAttribute("extension-name");
    final String ref = element.getAttribute("ref");

    final RootBeanDefinition bean = new RootBeanDefinition();
    bean.setBeanClass(ExtensionPointBean.class);
    bean.getConstructorArgumentValues().addIndexedArgumentValue(0, extensionName);
    bean.getConstructorArgumentValues().addIndexedArgumentValue(1, ref);

    parserContext.registerBeanComponent(new BeanComponentDefinition(new BeanDefinitionHolder(bean,
        beanNameGenerator.generateBeanName(bean, parserContext.getRegistry()))));

    return null;
  }
}
