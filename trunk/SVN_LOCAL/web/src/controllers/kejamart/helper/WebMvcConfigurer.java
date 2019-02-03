package kejamart.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class WebMvcConfigurer implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            return bean;
    }
}