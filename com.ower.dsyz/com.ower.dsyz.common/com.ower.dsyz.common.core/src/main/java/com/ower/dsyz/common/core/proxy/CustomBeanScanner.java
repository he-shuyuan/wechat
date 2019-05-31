package com.ower.dsyz.common.core.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;
/**
 * 远程bean扫描
 * @author heshuyuan
 *
 */
@Component
public class CustomBeanScanner implements BeanDefinitionRegistryPostProcessor  {
    /**
     * 扫描位置
     */
	@SuppressWarnings("serial")
	private List<String> basePackages = new ArrayList<String>(){{
		add("com.ower.dsyz.*.api");
		}
	};
	
	private static final Logger log = LoggerFactory.getLogger(CustomBeanScanner.class);
	
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		CustomClassPathScanner scanner = new CustomClassPathScanner(registry);
		String[] packages = {"*"};
		if(getBasePackages()!=null && !getBasePackages().isEmpty()){
			packages = (String[]) getBasePackages().toArray();
		}
		Set<BeanDefinitionHolder>  set = scanner.doScan(packages);
	   
		for(BeanDefinitionHolder holder:set){
	    	if(!isHasBean(registry,holder.getBeanDefinition().getBeanClassName())){
	    		registerBean(holder.getBeanDefinition().getBeanClassName(),registry);
	    	}
	    }	
	}



	/**
	 * 注册bean
	 * @param beanClassName
	 * @param registry
	 */
	private void registerBean(String beanClassName, BeanDefinitionRegistry registry) {
		try {
			ConstructorArgumentValues values = new ConstructorArgumentValues();
			values.addIndexedArgumentValue(0, Class.forName(beanClassName));
			RootBeanDefinition bean = new RootBeanDefinition(RemoteProxyBean.class,values,null);
			registry.registerBeanDefinition(RemoteProxyBean.BEAN_NAME_PREFIX+System.currentTimeMillis(), bean);
		} catch (ClassNotFoundException e) {
			log.error("类名无效，{}",e);
		}
	
		
	}

	/**
	 * 判断该接口是否存在实现类
	 * @param registry
	 * @param n
	 * @return
	 */
	private Boolean isHasBean( BeanDefinitionRegistry registry,String n){
		for(String name:registry.getBeanDefinitionNames()){
			BeanDefinition temp = registry.getBeanDefinition(name);
			try {
				if(Class.forName(n).isAssignableFrom(Class.forName(temp.getBeanClassName()))){
					return true;
				}
			} catch (ClassNotFoundException e) {
				return true;
			}
		}
		return false;
	}

	public List<String> getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(List<String> basePackages) {
		this.basePackages = basePackages;
	}

}
