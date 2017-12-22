package com.megalink.ems.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtilDom4j {
	static final Logger log = Logger.getLogger(XmlUtilDom4j.class.getName());

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void replaceElement(Element oldEle, Element newEle) {
		List elepar = oldEle.getParent().content();
		elepar.set(elepar.indexOf(oldEle), newEle);
		elepar.remove(oldEle);
	}

	/**
	 * Parse xml document
	 *
	 * @param fileName
	 * @return document
	 */
	public static Document parseXML(String fileName) {
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEntityResolver(new IgnoreDTDEntityResolver());
			File f = new File(fileName);
			Document doc = saxReader.read(f);
			return doc;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	public static Document parseXML(File f) {
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEntityResolver(new IgnoreDTDEntityResolver());
			Document doc = saxReader.read(f);
			return doc;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	public static String getAttValue(Element e, String attName, String defaultValue) {
		if (e.attribute(attName) == null) {
			return defaultValue;
		} else {
			return e.attributeValue(attName);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getChildAttValue(Element parentEle, String tag, String attName) throws Exception {
		String uri = parentEle.getNamespaceURI();
		HashMap uriMap = new HashMap();
		uriMap.put("x", uri);
		XPath x = DocumentHelper.createXPath("./x:" + tag);
		x.setNamespaceURIs(uriMap);
		Element e = (Element) x.selectSingleNode(parentEle);
		if (e == null) {
			throw new Exception("can not find tag:" + tag + " in " + parentEle.getName());
		}
		return e.attributeValue(attName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Element selectSingleNode(Element parentEle, String tag) throws Exception {
		String uri = parentEle.getNamespaceURI();
		HashMap uriMap = new HashMap();
		uriMap.put("x", uri);
		XPath x = DocumentHelper.createXPath("./x:" + tag);
		x.setNamespaceURIs(uriMap);
		Element e = (Element) x.selectSingleNode(parentEle);
		return e;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Node> getChildrenNode(Element parentEle, String tag) throws Exception {
		String uri = parentEle.getNamespaceURI();
		HashMap uriMap = new HashMap();
		uriMap.put("x", uri);
		XPath x = DocumentHelper.createXPath("./x:" + tag);
		x.setNamespaceURIs(uriMap);
		List<Node> children = x.selectNodes(parentEle);
		return children;
	}

	public static File saveToFile(Document document, String filePath) {
		try {
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();
			outputFormat.setLineSeparator("\r\n");
			PrintWriter writer = new PrintWriter(filePath, "UTF-8");
			XMLWriter outPut = new XMLWriter(writer, outputFormat);
			outPut.write(document);
			outPut.flush();
			outPut.close();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return new File(filePath);
	}

	public static Document parseXmlByString(String content) throws Exception {
		InputStream in = new ByteArrayInputStream(content.getBytes("utf-8"), 0, content.getBytes("utf-8").length);
		return parseXML(in);
	}

	/**
	 * Parse xml document
	 *
	 * @param fileName
	 * @return document
	 */
	public static Document parseXML(InputStream in) {
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEntityResolver(new IgnoreDTDEntityResolver());
			Document doc = saxReader.read(in);
			return doc;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String parseacUser(Document doc) {
		try {
			Element root = doc.getRootElement();
			List acus = root.selectNodes("//acUser");
			String result = "";
			for (int i = 0; i < acus.size(); i++) {
				result += ((Element) acus.get(i)).attributeValue("shortName");
				result += ",,";
				result += ((Element) acus.get(i)).attributeValue("name");
				result += ";;";
			}
			return result;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	public static Element createDocument(String rootName) {
		Document document = DocumentHelper.createDocument();
		return document.addElement(rootName);
	}
}
