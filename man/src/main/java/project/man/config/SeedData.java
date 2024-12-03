package project.man.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.man.Service.AccountService;
import project.man.Service.ProjectService;
import project.man.models.Account;
import project.man.models.Project;
import project.man.util.constants.Roles;
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProjectService projectService;

   @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        account01.setEmail("khushjain052@gmail.com");
        account01.setPassword("Khush"); 
        account01.setRole(Roles.ADMIN.getRole());
        account01.setAuthorities(null);
        accountService.save(account01);
        Account account02 = new Account();
        account02.setEmail("shreedeelipent@gmail.com");
        account02.setPassword("Khush"); 
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAuthorities(null);
        accountService.save(account02);  


        Project project01 = new Project();
        project01.setTitle("IOT PROJECT ");
        project01.setRequirements("Hardwareeeeeeeeeeee");
        project01.setDescription("\"17. Web MVC framework\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"17.1 Introduction to Spring Web MVC framework\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"The Spring Web model-view-controller (MVC) framework is designed around a DispatcherServlet that dispatches requests to handlers, with configurable handler mappings, view resolution, locale and theme resolution as well as support for uploading files. The default handler is based on the @Controller and @RequestMapping annotations, offering a wide range of flexible handling methods. With the introduction of Spring 3.0, the @Controller mechanism also allows you to create RESTful Web sites and applications, through the @PathVariable annotation and other features.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"“Open for extension...”\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"A key design principle in Spring Web MVC and in Spring in general is the “Open for extension, closed for modification” principle.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Some methods in the core classes of Spring Web MVC are marked final. As a developer you cannot override these methods to supply your own behavior. This has not been done arbitrarily, but specifically with this principle in mind.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"For an explanation of this principle, refer to Expert Spring Web MVC and Web Flow by Seth Ladd and others; specifically see the section \\\"A Look At Design,\\\" on page 117 of the first edition. Alternatively, see\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Bob Martin, The Open-Closed Principle (PDF)\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"You cannot add advice to final methods when you use Spring MVC. For example, you cannot add advice to the AbstractController.setSynchronizeOnSession() method. Refer to Section 9.6.1, “Understanding AOP proxies” for more information on AOP proxies and why you cannot add advice to final methods.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"In Spring Web MVC you can use any object as a command or form-backing object; you do not need to implement a framework-specific interface or base class. Spring's data binding is highly flexible: for example, it treats type mismatches as validation errors that can be evaluated by the application, not as system errors. Thus you need not duplicate your business objects' properties as simple, untyped strings in your form objects simply to handle invalid submissions, or to convert the Strings properly. Instead, it is often preferable to bind directly to your business objects.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Spring's view resolution is extremely flexible. A Controller is typically responsible for preparing a model Map with data and selecting a view name but it can also write directly to the response stream and complete the request. View name resolution is highly configurable through file extension or Accept header content type negotiation, through bean names, a properties file, or even a custom ViewResolver implementation. The model (the M in MVC) is a Map interface, which allows for the complete abstraction of the view technology. You can integrate directly with template based rendering technologies such as JSP, Velocity and Freemarker, or directly generate XML, JSON, Atom, and many other types of content. The model Map is simply transformed into an appropriate format, such as JSP request attributes, a Velocity template model.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"17.1.1 Features of Spring Web MVC\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Spring Web Flow\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Spring Web Flow (SWF) aims to be the best solution for the management of web application page flow.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"SWF integrates with existing frameworks like Spring MVC, Struts, and JSF, in both servlet and portlet environments. If you have a business process (or processes) that would benefit from a conversational model as opposed to a purely request model, then SWF may be the solution.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"SWF allows you to capture logical page flows as self-contained modules that are reusable in different situations, and as such is ideal for building web application modules that guide the user through controlled navigations that drive business processes.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"For more information about SWF, consult the Spring Web Flow website.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Spring's web module includes many unique web support features:\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Clear separation of roles. Each role — controller, validator, command object, form object, model object, DispatcherServlet, handler mapping, view resolver, and so on — can be fulfilled by a specialized object.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Powerful and straightforward configuration of both framework and application classes as JavaBeans. This configuration capability includes easy referencing across contexts, such as from web controllers to business objects and validators.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Adaptability, non-intrusiveness, and flexibility. Define any controller method signature you need, possibly using one of the parameter annotations (such as @RequestParam, @RequestHeader, @PathVariable, and more) for a given scenario.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Reusable business code, no need for duplication. Use existing business objects as command or form objects instead of mirroring them to extend a particular framework base class.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Customizable binding and validation. Type mismatches as application-level validation errors that keep the offending value, localized date and number binding, and so on instead of String-only form objects with manual parsing and conversion to business objects.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Customizable handler mapping and view resolution. Handler mapping and view resolution strategies range from simple URL-based configuration, to sophisticated, purpose-built resolution strategies. Spring is more flexible than web MVC frameworks that mandate a particular technique.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Flexible model transfer. Model transfer with a name/value Map supports easy integration with any view technology.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Customizable locale and theme resolution, support for JSPs with or without Spring tag library, support for JSTL, support for Velocity without the need for extra bridges, and so on.\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"A simple yet powerful JSP tag library known as the Spring tag library that provides support for features such as data binding and themes. The custom tags allow for maximum flexibility in terms of markup code. For information on the tag library descriptor, see the appendix entitled Appendix G, spring.tld\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"A JSP form tag library, introduced in Spring 2.0, that makes writing forms in JSP pages much easier. For information on the tag library descriptor, see the appendix entitled Appendix H, spring-form.tld\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"\\r\\n" + //
                        "\" + //\r\n" + //
                        "                                \"Beans whose lifecycle is");
                        projectService.save(project01);
    }
   
}