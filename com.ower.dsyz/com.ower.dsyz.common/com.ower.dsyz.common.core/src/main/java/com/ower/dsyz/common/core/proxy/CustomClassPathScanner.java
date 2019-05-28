package com.bda.chongqing.core.proxy;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.Assert;


public class CustomClassPathScanner extends ClassPathBeanDefinitionScanner {

	public CustomClassPathScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	 @Override
	  public Set<BeanDefinitionHolder> doScan(String... basePackages) {
			Assert.notEmpty(basePackages, "At least one base package must be specified");
			Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
			for (String basePackage : basePackages) {
				Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
				for (BeanDefinition candidate : candidates) {
						BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate,System.currentTimeMillis()+"ABC");
						beanDefinitions.add(definitionHolder);
				}
			}
			return beanDefinitions;
		}


	  /**
	   * 必须实现IVirtualProxyService
	   */
	  @Override
	  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		  if(!beanDefinition.getMetadata().isInterface()){
			  return false;
		  }
		  for(String str:beanDefinition.getMetadata().getInterfaceNames()){
			  if(IVirtualProxyService.class.getName().equals(str)){
				  return true;
			  }
		  }
		  return false;
	  }

	  @Override
	 protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
			return true;
	}
}
