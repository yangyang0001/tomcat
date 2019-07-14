package com.inspur;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * User: YANG
 * Date: 2019/7/13-16:49
 * Description: No Description
 */
public class EmbedTomcatServer {

	public static void main(String[] args) throws Exception {
		//1.确定classPath的目录
		String userDir = System.getProperty("user.dir");
		String classPath = userDir + File.separator + "target" + File.separator + "classes";
		System.out.println(classPath);

		//2.创建Tomcat
		Tomcat tomcat = new Tomcat();

		//2.1设置端口号
		tomcat.setPort(9090);

		//2.2设置Host
		Host host = tomcat.getHost();
		host.setName("localhost");
		host.setAppBase("webapps");
		host.setAutoDeploy(true);

		//2.3设置context
		String contextPath = "/";
		//E:\study_workspace\tomcat\src\main\webapp
		String webapp = System.getProperty("user.dir") +
				File.separator + "src" +
				File.separator + "main" +
				File.separator + "webapp";
		/**
		 * <Context path="/pageinterceptor" docBase="/data/webapps/pageinterceptor" debug="0" privileged="true"></Context>
		 * 这里相当于设置<Context 的path = contextPath , docPath = webapp
		 */
		Context context = tomcat.addWebapp(contextPath, webapp);

		if (context instanceof StandardContext) {
			StandardContext standardContext = (StandardContext) context;
			//配置Tomcat启动的web.xml这里的web.xml不是Web项目的web.xml
			//E:\study_workspace\tomcat\target\classes\tomcat_config
			String webXML = classPath + File.separator + "tomcat_config" + File.separator + "web.xml";
			standardContext.setDefaultWebXml(webXML);
		}

		//2.4.配置 classPath
		Wrapper wrapper = tomcat.addServlet(contextPath, "DemoServlet", new DemoServlet());
		wrapper.addMapping("/demo");

		//2.5设置编码
		Connector connector = new Connector();
		connector.setPort(9090);
		connector.setURIEncoding("UTF-8");
		tomcat.getService().addConnector(connector);


		tomcat.start();

		//3.tomcat server强制等待避免main线程结束
		tomcat.getServer().await();

	}
}
