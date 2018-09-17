package com.itbank.dataCheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Data01 {

	// XPath를 활용한 xml기반 오픈 api java파싱

	public static void main(String[] args) {
		BufferedReader br = null;
		// DocumentBuilderFactory 생성

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;

		try {
			// OpenApi 호출
			// String urlstr =
			// "http://apis.data.go.kr/1300000/jBGSSCJeongBo/list?numOfRows=100&pageNo=1&ServiceKey=a8VRdtLdlYzaiyorG5b6RtWyr1EfAi%2BOtjLUiOcR6RtcL4cfue8Tzj5WcpHC%2FQmvPFroQ4Ne0uL3%2BZGTJHBViA%3D%3D";
			String urlstr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/list?numOfRows=100&pageNo=1&ServiceKey=%2FUFvHFOKlI%2F2XGwru0mHvXBRXZtBSm9EK93bX4cv75T%2FbiKyI%2F%2FImDCGyWCGn%2FJr44%2Bzgq7XKQUZZsKoVgUGuw%3D%3D";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

			// 응답 읽기
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line.trim(); // result = URL로 XML을 읽은값
			}

			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(result));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
			XPathExpression expr = xpath.compile("//items/item"); // 노드 문법입력
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			/*
			 * for(int i = 0; i < nodeList.getLength(); i++) { NodeList child =
			 * nodeList.item(i).getChildNodes();
			 * 
			 * for(int j = 0; j < child.getLength(); j++) { Node node =
			 * child.item(j); System.out.println("현재노드 이름 : " +
			 * node.getNodeName()); System.out.println("현재노드 타입 : " +
			 * node.getNodeType()); System.out.println("현재노드 값 : " +
			 * node.getTextContent()); System.out.println("현재노드 네임스페이스 : " +
			 * node.getPrefix()); System.out.println("현재노드의 다음 노드 : " +
			 * node.getNextSibling());
			 * 
			 * } }
			 */
			for (int i = 0; i < 1; i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				for (int j = 0; j < 12; j++) {
					Node node = child.item(j);
					System.out.println("현재<item>안의 노드 값 : " + node.getTextContent());

				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage()+"why?");
		}
	}

}
