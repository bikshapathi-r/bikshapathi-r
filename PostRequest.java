package com.ctel.tts.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PostRequest", namespace = "http://www.goap.hstracking.com/Service/")
public class PostRequest  implements Serializable{
        /**
	 * 
	 */
	private static final long serialVersionUID = 5087683049243108315L;
	

		private String objectType;

        private String locationId;

        private String locationType;

        private String sourceLocation;

        private String destinationLocation;

        private String payload;

        @XmlElement(name = "ObjectType", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getObjectType() {
                return objectType;
        }

        public void setObjectType(String objectType) {
                this.objectType = objectType;
        }

        @XmlElement(name = "LocationId", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getLocationId() {
                return locationId;
        }

        public void setLocationId(String locationId) {
                this.locationId = locationId;
        }

        @XmlElement(name = "LocationType", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getLocationType() {
                return locationType;
        }

        public void setLocationType(String locationType) {
                this.locationType = locationType;
        }

        @XmlElement(name = "SourceLocation", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getSourceLocation() {
                return sourceLocation;
        }

        public void setSourceLocation(String sourceLocation) {
                this.sourceLocation = sourceLocation;
        }

        @XmlElement(name = "DestinationLocation", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getDestinationLocation() {
                return destinationLocation;
        }

        public void setDestinationLocation(String destinationLocation) {
                this.destinationLocation = destinationLocation;
        }

        @XmlElement(name = "Payload", namespace = "http://www.goap.hstracking.com/Service/", type = String.class)
        public String getPayload() {
                return payload;
        }

        public void setPayload(String payload) {
                this.payload = payload;
        }

}