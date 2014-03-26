package cli.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface Option {
  public String opt();
  public String longOpt() default "";
  public boolean hasArg() default false;
  public boolean required() default false; 
  public String description();
  public Class<?> type() default String.class;
  public String defaultValueStr() default "";
}
