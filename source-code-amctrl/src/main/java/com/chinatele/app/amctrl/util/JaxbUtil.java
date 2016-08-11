package com.chinatele.app.amctrl.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {

    /**
     * @description 对象转换成xml
     * @author zhoudr
     * @time 2015-11-28 上午11:37:46
     * @param obj
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String convertToXml(Object obj, String encoding) throws Exception {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 格式化
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);// 编码

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        String result = writer.toString();
        return result;
    }

    /**
     * @description xml字符串转换成java对象
     * @author zhoudr
     * @time 2015-11-28 上午11:38:30
     * @param xml
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJavaBean(String xml, Class<T> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T t = (T) unmarshaller.unmarshal(new StringReader(xml));
        return t;
    }
}
