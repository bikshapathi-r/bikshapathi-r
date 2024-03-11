package com.ctel.tts.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;

@Component
public class XMLConverter implements Serializable{

	// private static final Log log = LogFactory.getLog(XMLConverter.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4916295954813607108L;
	
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertFromObjectToXML(Object object, String filepath) throws IOException, JAXBException {
		System.out.println("converting From Object To XML : object " + object);

		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public Object convertFromXMLToObject(String payload) throws Exception {
		Object res = null;

		if (payload.contains("&")) {
			payload = payload.replace("&", "&amp;");
		}

		JAXBContext jaxbContext = JAXBContext.newInstance(PostRequest.class);

		res = jaxbContext.createUnmarshaller()
				.unmarshal(new StreamSource(new ByteArrayInputStream(payload.getBytes("utf-8"))));

		return res;
	}

}
