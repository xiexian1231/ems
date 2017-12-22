package com.megalink.ems.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "\"Annotation\"")
public class AnnotationEntry {
	@Id
	@SequenceGenerator(name = "Annotation_ID_seq", allocationSize = 1, initialValue = 1, sequenceName = "Annotation_ID_seq")
	@GeneratedValue(generator = "Annotation_ID_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "\"ID\"")
	private Integer id;

	@Column(name = "\"DOCID\"")
	private String docId;

	@Column(name = "\"CREATED\"")
	private Date created;

	@Column(name = "\"CREATORID\"")
	private String creatorId;

	@Column(name = "\"MODIFIED\"")
	private Date modified;

	@Column(name = "\"MODIFIERID\"")
	private String modifierId;

	@Column(name = "\"SRC\"")
	private String src;

	@Column(name = "\"TEXT\"")
	private String text;

	@Column(name = "\"CONTEXT\"")
	private String context;

	@Column(name = "\"SHAPEJSON\"")
	private String shapeJson = "[]";

	@Column(name = "\"TYPE\"")
	private String type;

	@Column(name = "\"PERMISSIONS\"")
	private String permissions = "{}";

	@Column(name = "\"QUOTE\"")
	private String quote;

	@Column(name = "\"PAGENUMBER\"")
	private String pageNumber;

	@Column(name = "\"DESTJSON\"")
	private String destJson = "[]";

	@Column(name = "\"CATEGORY\"")
	private String category;

	@Column(name = "\"ORDERSTR\"")
	private String order;

	@Column(name = "\"ANNOTATIONID\"")
	private String annotationId;

	@SuppressWarnings("rawtypes")
	@Transient
	private List ranges;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRanges() {
		List rgs = new ArrayList();
		JSONArray arr = new JSONArray(shapeJson);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> m;
			try {
				m = mapper.readValue(obj.toString(), Map.class);
				rgs.add(m);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rgs;
	}

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("docId", docId);
		obj.put("id", annotationId);
		obj.put("src", src);
		obj.put("context", context);
		if (text == null) {
			obj.put("text", "");
		} else {
			obj.put("text", text);
		}
		if ("canvas".equalsIgnoreCase(type)) {
			obj.put("shapes", new JSONArray(shapeJson));
		} else {
			obj.put("ranges", shapeJson);
		}
		obj.put("created", created);
		obj.put("user", creatorId);
		obj.put("type", type);
		obj.put("permissions", new JSONObject(permissions));
		obj.put("quote", quote);
		obj.put("pageNumber", pageNumber);
		obj.put("dest", new JSONArray(destJson));
		obj.put("category", category);
		obj.put("order", order);
		return obj;
	}

	public AnnotationEntry() {
		super();
	}

	@SuppressWarnings("unused")
	public AnnotationEntry(String jsonStr) {
		loadFromJsonStr(jsonStr);
		JSONObject out = this.toJSONObject();
	}

	public void loadFromJsonStr(String jsonStr) {
		JSONObject obj = new JSONObject(jsonStr);
		docId = obj.has("docId") ? (String) obj.get("docId") : "";
		annotationId = obj.has("id") ? String.valueOf(obj.get("id")) : "";
		src = obj.has("src") ? (String) obj.get("src") : "";
		text = obj.has("text") ? (String) obj.get("text") : "";
		context = obj.has("context") ? (String) obj.get("context") : "";
		type = obj.has("type") ? (String) obj.get("type") : "";
		if ("canvas".equalsIgnoreCase(type)) {
			shapeJson = obj.has("shapes") ? ((JSONArray) obj.get("shapes")).toString() : "";
		} else {
			shapeJson = obj.has("ranges") ? ((JSONArray) obj.get("ranges")).toString() : "";
		}
		created = new Date();
		creatorId = obj.has("user") ? (String) obj.get("user") : "";
		permissions = obj.has("permissions") ? ((JSONObject) obj.get("permissions")).toString() : "{}";
		quote = obj.has("quote") ? (String) obj.get("quote") : "";
		pageNumber = obj.has("pageNumber") ? (String) obj.get("pageNumber") : "";
		destJson = obj.has("dest") ? ((JSONArray) obj.get("dest")).toString() : "[]";
		if (obj.has("category")) {
			category = (String) obj.get("category");
		}
		if (obj.has("order")) {
			order = (String) obj.get("order");
		}
	}

	@SuppressWarnings("rawtypes")
	public AnnotationEntry(Integer id, String docId, Date created, String creatorId, Date modified, String modifierId,
			String src, String text, String context, String shapeJson, String type, String permissions, String quote,
			String pageNumber, String destJson, String category, String order, String annotationId, List ranges) {
		super();
		this.id = id;
		this.docId = docId;
		this.created = created;
		this.creatorId = creatorId;
		this.modified = modified;
		this.modifierId = modifierId;
		this.src = src;
		this.text = text;
		this.context = context;
		this.shapeJson = shapeJson;
		this.type = type;
		this.permissions = permissions;
		this.quote = quote;
		this.pageNumber = pageNumber;
		this.destJson = destJson;
		this.category = category;
		this.order = order;
		this.annotationId = annotationId;
		this.ranges = ranges;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getShapeJson() {
		return shapeJson;
	}

	public void setShapeJson(String shapeJson) {
		this.shapeJson = shapeJson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getDestJson() {
		return destJson;
	}

	public void setDestJson(String destJson) {
		this.destJson = destJson;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAnnotationId() {
		return annotationId;
	}

	public void setAnnotationId(String annotationId) {
		this.annotationId = annotationId;
	}

	@SuppressWarnings("rawtypes")
	public void setRanges(List ranges) {
		this.ranges = ranges;
	}

}
