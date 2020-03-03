package com.accusy.robotpro.robotadmin.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author imssbora
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
          return new Class[] {WebConfig.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] {};
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
}
