package com.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * 用于将数据转换为支持Chart.js插件的结构化json数据
 * @author Sicmatr1x
 *
 */
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
	
	/**
	 * 返回饼图json
	 * 
	 * type: 'pie',
        data: {
            datasets: [{
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                ],
                backgroundColor: [
                    window.chartColors.red,
                    window.chartColors.orange,
                    window.chartColors.yellow,
                    window.chartColors.green,
                    window.chartColors.blue,
                ],
                label: 'Dataset 1'
            }],
            labels: [
                "Red",
                "Orange",
                "Yellow",
                "Green",
                "Blue"
            ]
        },
        options: {
            responsive: true
        }
	 * 
	 * @param data 数据
	 * @param backgroundColor 各个扇形区域颜色
	 * @param labels 各个颜色对于标签名
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String getPieJson(double[] data, String[] backgroundColor, String[] labels, String title, String position) throws JsonGenerationException, JsonMappingException, IOException {
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
		
		ObjectNode positionNode = mapper.createObjectNode();
		positionNode.put("position", position);
		
		ObjectNode titleNode = mapper.createObjectNode();
		titleNode.put("display", true);
		titleNode.put("text", title);
		
		ObjectNode optionsNode = mapper.createObjectNode();
		optionsNode.put("legend", positionNode);
		optionsNode.put("title", titleNode);
		optionsNode.put("responsive", true);

		// 1级孩子
		ObjectNode root = mapper.createObjectNode();
		root.put("type", "pie");
		root.put("data", dataRoot);
		root.put("options", optionsNode);
		
		return mapper.writeValueAsString(root);
	}
	
	/**
	 * 返回极地区域图json
	 * 
	 * data: {
            datasets: [{
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                ],
                backgroundColor: [
                    color(chartColors.red).alpha(0.5).rgbString(),
                    color(chartColors.orange).alpha(0.5).rgbString(),
                    color(chartColors.yellow).alpha(0.5).rgbString(),
                    color(chartColors.green).alpha(0.5).rgbString(),
                    color(chartColors.blue).alpha(0.5).rgbString(),
                ],
                label: 'My dataset' // for legend
            }],
            labels: [
                "Red",
                "Orange",
                "Yellow",
                "Green",
                "Blue"
            ]
        },
        options: {
            responsive: true,
            legend: {
                position: 'right',
            },
            title: {
                display: true,
                text: 'Chart.js Polar Area Chart'
            },
            scale: {
              ticks: {
                beginAtZero: true
              },
              reverse: false
            },
            animation: {
                animateRotate: false,
                animateScale: true
            }
        }
	 * 
	 * @param data 各个扇形区域数据
	 * @param backgroundColor 各个扇形区域对应颜色
	 * @param labels 各个颜色对应标签名
	 * @param title 标题
	 * @param position 标题位置
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
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
	
	/**
	 * 返回雷达图json
	 * 
	 * type: 'radar',
        data: {
            labels: [["Eating", "Dinner"], ["Drinking", "Water"], "Sleeping", ["Designing", "Graphics"], "Coding", "Cycling", "Running"],
            datasets: [{
                label: "My First dataset",
                backgroundColor: color(window.chartColors.red).alpha(0.2).rgbString(),
                borderColor: window.chartColors.red,
                pointBackgroundColor: window.chartColors.red,
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor()
                ]
            }, {
                label: "My Second dataset",
                backgroundColor: color(window.chartColors.blue).alpha(0.2).rgbString(),
                borderColor: window.chartColors.blue,
                pointBackgroundColor: window.chartColors.blue,
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor()
                ]
            },]
        },
        options: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Chart.js Radar Chart'
            },
            scale: {
              ticks: {
                beginAtZero: true
              }
            }
        }
	 * 
	 * @param data
	 * @param dataLabels
	 * @param backgroundColor
	 * @param borderColor
	 * @param pointBackgroundColor
	 * @param labels
	 * @param title
	 * @param position
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String getRadarJson(double[][] data, String[] dataLabels, String[] backgroundColor, String[] borderColor, String[] pointBackgroundColor, String[] labels, String title, String position) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayNode labelsList = mapper.createArrayNode();
		for(String t : labels){
			labelsList.add(t);
		}
		
		// 添加数据
		ArrayNode datasetsList = mapper.createArrayNode();
		for(int i = 0; i < data.length; i++){
			ObjectNode datasetsNode = mapper.createObjectNode();
			datasetsNode.put("label", dataLabels[i]);
			datasetsNode.put("backgroundColor", backgroundColor[i]);
			datasetsNode.put("borderColor", borderColor[i]);
			datasetsNode.put("pointBackgroundColor", pointBackgroundColor[i]);
			ArrayNode dataList = mapper.createArrayNode();
			for(int j = 0; j < data[i].length; j++){
				dataList.add(data[i][j]);
			}
			datasetsNode.put("data", dataList);
			
			datasetsList.add(datasetsNode);
		}
		
		ObjectNode dataRoot = mapper.createObjectNode();
		dataRoot.put("labels", labelsList);
		dataRoot.put("datasets", datasetsList);
		
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
//		scaleNode.put("reverse", false);
		
		ObjectNode optionsNode = mapper.createObjectNode();
		optionsNode.put("legend", positionNode);
		optionsNode.put("title", titleNode);
		optionsNode.put("scale", scaleNode);

		// 1级孩子
		ObjectNode root = mapper.createObjectNode();
		root.put("type", "radar");
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
			ChartDataJsonCreater.getPieJson(data, backgroundColor, labels,"test","left");
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
