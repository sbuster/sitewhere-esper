# sitewhere-esper
Esper plugin to the SiteWhere platform

Created against Esper 5.1

Installation:
- Download Esper 5.1 and add appropriate jars to the project classpath
- Declar the esper beans in the sitewhere-server.xml file as show below.
- Add the EsperEventProcessor bean to the list of outboundProcessorChain
- A default esper.cfg.xml file is provide with the four key events (location,measurement,command and alert) in the base systems
- 


Declare the EsperConfiguration as follows:

	<bean id="esperConfig" class="com.sitewhere.esper.EsperConfiguration">
		<property name="configFileName" value="esper.cfg.xml"/>
	</bean>
	
	Declare the EsperEventProcess bean
	<bean id="esperEventProcessor" class="com.sitewhere.esper.EsperEventProcessor">
		<property name="configuration" ref="esperConfig"></property>
	</bean>
