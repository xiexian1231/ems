package com.megalink.ems.surface.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.megalink.ems.model.Jobcard;
import com.megalink.ems.surface.dao.JobcardHelper;
import com.megalink.ems.surface.pojo.Equipment;
import com.megalink.ems.surface.pojo.Reference;
import com.megalink.ems.util.XmlUtilDom4j;

@Transactional
@Service("jobcardService")
public class JobcardServiceImpl implements JobcardService {

	@Autowired
	private JobcardHelper jobcardHelper;

	@Override
	public List<Jobcard> queryJobcards(HttpServletRequest request, HttpServletResponse response) {
		String cardlist = request.getParameter("cardlist");
		JSONObject jsonObj = JSONObject.parseObject(cardlist);
		// 注意前台传的数据一定要去掉空格
		String planenumber = jsonObj.getString("planenumber").trim();
		String entrydate = jsonObj.getString("entrydate").trim();
		String username = jsonObj.getString("username").trim();
		return jobcardHelper.queryJobcards(planenumber, entrydate, username);
	}

	/**
	 * 后台预处理xml的流程 1.查询所有的input的元素 2.为每一个input的标签设置xpath
	 * 3.转为html的时候把每个xml中的input标签的xpath的值也设置为html的input标签的一个属性
	 * 4.父页面点击保存后选中所有的input标签 5.遍历input标签，取出每个xpath和value值作为一个对象，组合为一个json数组传到后台
	 * 6.后台取出json数组后，根据xpath去为每个input标签设置value值
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ModelMap transformXMLToHTML(ModelMap modelMap, HttpServletRequest request, String taskTime,
			String fileName) {
		String rootPath = request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/");

		List<Reference> References = new ArrayList<Reference>();

		List<Equipment> Equipments = new ArrayList<Equipment>();

		List<String> pics = new ArrayList<String>();
		/* start to transfer xml */
		// 通过ResourceBundle获取properties文件中的路径信息
		ResourceBundle res = ResourceBundle.getBundle("com.megalink.ems.resource.xmlpath");
		String xmlurl = res.getString("jc.partition.folder") + '/' + fileName + '.' + res.getString("jc.postfix");
		File xmlFile = new File(xmlurl);

		/* 开始预处理源xml文件，设置input的xpath属性 */
		Document xmlDoc = XmlUtilDom4j.parseXML(xmlFile);
		Element ele = xmlDoc.getRootElement();
		List<Element> inputs = ele.selectNodes("//input");
		for (Element input : inputs) {
			input.addAttribute("xpath", input.getUniquePath());
		}
		XmlUtilDom4j.saveToFile(xmlDoc, xmlurl);
		/* 预处理结束 */

		String xslurl = request.getSession().getServletContext().getRealPath("/conf/xmlTransfer.xsl").replaceAll("\\\\",
				"/");
		String htmlhome = rootPath + "temp/HTMLPath";
		File htmlFile = new File(htmlhome);
		if (!htmlFile.exists()) {
			htmlFile.mkdirs();
		}
		String htmlurl = htmlhome + "/" + xmlFile.getName().replaceAll("[.][^.]+$", "") + ".html";
		transformXml(xmlurl, xslurl, htmlurl);
		/* end to transfer xml */

		/**
		 * 取得references
		 **/
		Node frontmatter = ele.selectSingleNode("./frontmatter");
		Node references = frontmatter.selectSingleNode("./references");
		List<Element> refs = references.selectNodes("./ref");
		for (Element ref : refs) {
			Element refnbr = (Element) ref.selectSingleNode("./refnbr");
			Element reftitle = (Element) ref.selectSingleNode("./reftitle");
			Element para = (Element) reftitle.selectSingleNode("./para");

			Reference reference = new Reference();
			reference.setReference(refnbr.getText());
			reference.setTitle(para.getText());

			References.add(reference);
		}
		/**
		 * 取得toolEquips
		 **/
		Node toolEquips = frontmatter.selectSingleNode("./toolEquips");
		List<Element> Equips = toolEquips.selectNodes("./toolEquip");
		for (Element Equipment : Equips) {
			Element refnbr = (Element) Equipment.selectSingleNode("./refnbr");
			Element description = (Element) Equipment.selectSingleNode("./description");
			Element para = (Element) description.selectSingleNode("./para");
			Element qty = (Element) Equipment.selectSingleNode("./qty");

			Equipment equipment = new Equipment();
			equipment.setReference(refnbr.getText());
			equipment.setDescription(para.getText());
			equipment.setQty(qty.getText());

			Equipments.add(equipment);
		}
		/**
		 * 取得graphic
		 **/
		Node figSection = ele.selectSingleNode("./figSection");
		if (figSection != null) {
			List<Element> graphics = figSection.selectNodes("./graphic");
			for (Element graphic : graphics) {
				List<Element> sheets = graphic.selectNodes("./sheet");
				for (Element sheet : sheets) {
					String gnbr = sheet.attributeValue("gnbr");
					String pic = gnbr.substring(12);
					pics.add(pic);
				}
			}
		}
		String htmlpath = htmlurl.substring(htmlurl.indexOf("/ems"));
		modelMap.addAttribute("References", References);
		modelMap.addAttribute("Equipments", Equipments);
		modelMap.addAttribute("pics", pics);
		modelMap.addAttribute("htmlurl", htmlpath);
		modelMap.addAttribute("taskTime", parseTime(taskTime));
		return modelMap;
	}

	public static void transformXml(String xmlurl, String xslurl, String htmlurl) {
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslurl);
			Transformer t = tFac.newTransformer(xslSource);
			File xmlFile = new File(xmlurl);
			File htmlFile = new File(htmlurl);
			Source source = new StreamSource(xmlFile);
			Result result = new StreamResult(htmlFile);
			t.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 秒时间格式转换时间戳格式
	 */
	public String parseTime(String str) {
		int seconds = Integer.parseInt(str);
		int temp = 0;
		StringBuffer sb = new StringBuffer();
		temp = seconds / 3600;
		sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
		temp = seconds % 3600 / 60;
		sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
		temp = seconds % 3600 % 60;
		sb.append((temp < 10) ? "0" + temp : "" + temp);
		return sb.toString();
	}

	@Override
	public String store(HttpServletRequest request, HttpServletResponse response) {
		/* start保存工时数据 */
		String id = request.getParameter("id");
		String taskTime = request.getParameter("taskTime");
		int a = updateJobcard(Integer.parseInt(id), taskTime);
		/* end */
		/* start保存数据到xml中 */
		String jsonArr = request.getParameter("jsonArr");
		String fileName = request.getParameter("fileName");
		ResourceBundle res = ResourceBundle.getBundle("com.megalink.ems.resource.xmlpath");
		String xmlurl = res.getString("jc.partition.folder") + '/' + fileName + '.' + res.getString("jc.postfix");
		Document xmlDoc = XmlUtilDom4j.parseXML(new File(xmlurl));
		Element ele = xmlDoc.getRootElement();
		JSONArray jsonArray = JSONArray.parseArray(jsonArr);
		for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext();) {
			JSONObject jsonObj = (JSONObject) iterator.next();
			Element input = (Element) ele.selectSingleNode(jsonObj.getString("xpath"));
			Attribute value = input.attribute("value");
			value.setValue(jsonObj.getString("value"));
		}
		XmlUtilDom4j.saveToFile(xmlDoc, xmlurl);
		/* end */
		if (a > 0) {
			return "保存成功";
		} else {
			return "保存失败";
		}
	}

	@Override
	public int updateJobcard(Integer id, String taskTime) {
		return jobcardHelper.updateJobcard(id, taskTime);
	}

}
