package com.greatwall.pumpkin.tool;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * markdown工具类，将markdown语法文本装换为HTML文本
 *
 */
public class MarkdownTool {
    /**
     * 将markdown语法变成html 不带表格
     * 
     * @param markDownText
     * @return
     */
    public static String markDownToHTML(String markDownText) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markDownText);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlText = renderer.render(document);
        return htmlText;
    }

    /**
     * 将markdown语法变成html 带表格
     * 
     * @param markDownText
     * @return
     */
    public static String markDownToHTMLTable(String markDownText) {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        Node document = parser.parse(markDownText);
        String htmlText = renderer.render(document);
        return htmlText;
    }

    /**
     * 将html语法文本去除标签
     *
     * @param htmlStr
     * @return
     */
    public static String getTextFromHtml(String htmlStr) {
        String textStr = "";
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

    public static String markDownToText(String markDownText) {
        return MarkdownTool.getTextFromHtml(MarkdownTool.markDownToHTMLTable(markDownText));
    }

}
