package com.bardackx.jcogen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bardackx.jcogen.source.ClassSource;
import com.bardackx.jcogen.source.SourceRenderer;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.Environment;

public class CommandLineInterface {

	static JAXBContext instance;

	static {
		try {
			instance = JAXBContext.newInstance(MappingConfig.class, FunctionMappingConfig.class);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void writeExampleConfig() throws FileNotFoundException, IOException, JAXBException {
		FunctionMappingConfig config = new FunctionMappingConfig();
		try (FileOutputStream fos = new FileOutputStream(new File("example.xml"))) {
			Marshaller marshaller = instance.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.marshal(config, fos);
		}
	}

	public static MappingConfig readConfig(File file) throws JAXBException {
		Unmarshaller unmarshaller = instance.createUnmarshaller();
		return (MappingConfig) unmarshaller.unmarshal(file);
	}

	public static void main(String[] args) throws JCoException, FileNotFoundException, IOException, JAXBException {

		// CLI TODO
		
		File outputDir = new File("D:/bardackx/bardackx-workspace/jcogen/src/main/java/test");

		SourceRenderer r = new SourceRenderer();

		String configPath = "testconfig.xml";

		MappingConfig config = readConfig(new File(configPath));

		JCoGen jcogen = new JCoGen();
		jcogen.setFunctionPackageName("test");
		jcogen.setModelPackageName("test");
		for (FunctionMappingConfig e : config.getFunctionMappings()) {
			jcogen.addConfig(e);
		}

		PropertyBasedDestinationProvider provider = new PropertyBasedDestinationProvider();
		Environment.registerDestinationDataProvider(provider);

		String pname = "CUSTOM";
		Properties p = new Properties();
		p.put("jco.client.lang", "HIDDEN");
		p.put("jco.destination.peak_limit", "HIDDEN");
		p.put("jco.destination.pool_capacity", "HIDDEN");
		p.put("jco.client.ashost", "HIDDEN");
		p.put("jco.client.sysnr", "HIDDEN");
		p.put("jco.client.client", "HIDDEN");
		p.put("jco.client.user", "HIDDEN");
		p.put("jco.client.passwd", "HIDDEN");
		p.put("jco.client.saprouter", "HIDDEN");

		provider.registerDestinationData(pname, p);

		System.out.println("get destination");
		JCoDestination destination = JCoDestinationManager.getDestination(pname);

		System.out.println("ping destination");
		destination.ping();

		jcogen.setDestination(destination);

		for (FunctionMappingConfig fm : config.getFunctionMappings()) {
			if (fm.isIgnore()) continue;
			System.out.println("generating execution class for " + fm.getFunctionName());
			jcogen.generateFunctionExecutionClass(fm.getFunctionName());
		}

		for (ClassSource c : jcogen.getClassSources().values()) {
			System.out.println("writing " + c.getCanonicalName());
			File outputFile = new File(outputDir, c.getName() + ".java");
			try (FileOutputStream fos = new FileOutputStream(outputFile)) {
				byte[] bytes = r.render(c, false).getBytes(Charset.forName("UTF-8"));
				fos.write(bytes);
			}
		}

		System.out.println("done");
	}
}
