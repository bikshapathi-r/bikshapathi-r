package com.ctel.tts.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class JSONParser extends StdDeserializer<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6298265303499563415L;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public JSONParser() {
		this(null);
	}

	public JSONParser(Class<Date> t) {
		super(t);
	}

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		String date = parser.getText();
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object parseJsonTimeStamp(String json, Object object) {
		Object result = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		mapper.setDateFormat(df);
		try {
			result = mapper.readValue(json, object.getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Object parseJson(String json, Object object) throws Exception {
		Object result = null;
		ObjectMapper mapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
				DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("Asia/Kolkata"))));
		// javaTimeModule.addDeserializer(LocalDate.class, new
		// LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		mapper.registerModule(javaTimeModule);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// mapper.setDateFormat(df);
		result = mapper.readValue(json, object.getClass());
		return result;
	}

}
