package author.avelar.paulo.core.converter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfiguration
{
    private Set<Converter> converters = new HashSet<Converter>();

    public ConversionConfiguration()
    {
        converters.add(new MethodArgumentNotValidConverter());
    }

    @Bean
    public ConversionService conversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    public Set<Converter> getConverters()
    {
        return converters;
    }

}
