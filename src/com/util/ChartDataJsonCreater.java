package com.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class ChartDataJsonCreater {

	public static void test() throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root1 = mapper.createObjectNode();

			root1.put("nodekey1", 1);
			root1.put("nodekey2", 2);

			System.out.println(root1.toString());

			// Create the root node
			ObjectNode root = mapper.createObjectNode();
			// Create a child node
			ObjectNode node1 = mapper.createObjectNode();
			node1.put("nodekey1", 1);
			node1.put("nodekey2", 2);
			// Bind the child nodes
			root.put("child", node1);
			// Array of nodes
			ArrayNode arrayNode = mapper.createArrayNode();
			arrayNode.add(node1);
			arrayNode.add(1);
			// Bind array node
			root.put("arraynode", arrayNode);

			System.out.println(mapper.writeValueAsString(root));
			// 得到的输出信息
			// {"child":{"nodekey1":1,"nodekey2":2},"arraynode":[{"nodekey1":1,"nodekey2":2},1]}
		
	}
	
	public static String getPieJson(double[] data, String[] backgroundColor, String[] labels) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		// 添加数据
		ArrayNode dataList = mapper.createArrayNode();
		for(double t : data){
			dataList.add(t);
		}
		
		ArrayNode backgroundColorList = mapper.createArrayNode();
		for(String t : backgroundColor){
			backgroundColorList.add(t);
		}
		
		ArrayNode labelsList = mapper.createArrayNode();
		for(String t : labels){
			labelsList.add(t);
		}

		ObjectNode datasetsNode = mapper.createObjectNode();
		datasetsNode.put("data", dataList);
		datasetsNode.put("backgroundColor", backgroundColorList);
//		datasetsNode.put("label", label);
		ArrayNode datasetsList = mapper.createArrayNode();
		datasetsList.add(datasetsNode);
		ObjectNode dataRoot = mapper.createObjectNode();
		dataRoot.put("datasets", datasetsList);
		dataRoot.put("labels", labelsList);
//		System.out.println(mapper.writeValueAsString(datasetsList));
		
		ObjectNode optionsNode = mapper.createObjectNode();
		optionsNode.put("responsive", true);

		// 1级孩子
		ObjectNode root = mapper.createObjectNode();
		root.put("type", "pie");
		root.put("data", dataRoot);
		root.put("options", optionsNode);
		
		return mapper.writeValueAsString(root);
	}
	
	public static String getPolarAreaJson(double[] data, String[] backgroundColor, String[] labels, String title, String position) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		// 添加数据
		ArrayNode dataList = mapper.createArrayNode();
		for(double t : data){
			dataList.add(t);
		}
		
		ArrayNode backgroundColorList = mapper.createArrayNode();
		for(String t : backgroundColor){
			backgroundColorList.add(t);
		}
		
		ArrayNode labelsList = mapper.createArrayNode();
		for(String t : labels){
			labelsList.add(t);
		}

		ObjectNode datasetsNode = mapper.createObjectNode();
		datasetsNode.put("data", dataList);
		datasetsNode.put("backgroundColor", backgroundColorList);
//		datasetsNode.put("label", label);
		ArrayNode datasetsList = mapper.createArrayNode();
		datasetsList.add(datasetsNode);
		ObjectNode dataRoot = mapper.createObjectNode();
		dataRoot.put("datasets", datasetsList);
		dataRoot.put("labels", labelsList);
//		System.out.println(mapper.writeValueAsString(datasetsList));
		
		// 添加设置
		ObjectNode positionNode = mapper.createObjectNode();
		positionNode.put("position", position);
		
		ObjectNode titleNode = mapper.createObjectNode();
		titleNode.put("display", true);
		titleNode.put("text", title);
		
		ObjectNode ticksNode = mapper.createObjectNode();
		ticksNode.put("beginAtZero", true);
		ObjectNode scaleNode = mapper.createObjectNode();
		scaleNode.put("ticks", ticksNode);
		scaleNode.put("reverse", false);
		
		ObjectNode animationNode = mapper.createObjectNode();
		animationNode.put("animateRotate", false);
		animationNode.put("animateScale", true);
		
		ObjectNode optionsNode = mapper.createObjectNode();
		optionsNode.put("responsive", true);
		optionsNode.put("legend", positionNode);
		optionsNode.put("title", titleNode);
		optionsNode.put("scale", scaleNode);
		optionsNode.put("animation", animationNode);

		// 1级孩子
		ObjectNode root = mapper.createObjectNode();
		root.put("type", "polar-area");
		root.put("data", dataRoot);
		root.put("options", optionsNode);
		
		return mapper.writeValueAsString(root);
	}

	public static void main(String[] args) {
		double[] data = {
				10,
	            20,
	            5,
	            45,
	            20};
		
		String[] backgroundColor = {
				"rgb(255, 99, 132)",
				"rgb(255, 159, 64)",
				"rgb(255, 205, 86)",
				"rgb(75, 192, 192)",
				"rgb(54, 162, 235)"};
		String[] labels = {
				"Red",
                "Orange",
                "Yellow",
                "Green",
                "Blue"
		};
		try {
			ChartDataJsonCreater.getPieJson(data, backgroundColor, labels);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
