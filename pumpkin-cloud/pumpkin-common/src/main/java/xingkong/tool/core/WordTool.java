package xingkong.tool.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordTool {

    public static void printTableH(String fileName) throws FileNotFoundException, IOException {
        HWPFDocument document = new HWPFDocument(new FileInputStream(fileName));
        Range range = document.getRange();

        // 获取段落数
        int paraNum = range.numParagraphs();
        System.out.println(paraNum);
        for (int i = 0; i < 10; i++) {
            // this.insertInfo(range.getParagraph(i));
            System.out.println("段落" + (i + 1) + "：" + range.getParagraph(i).text());

        }

        TableIterator talbleIter = new TableIterator(range);
        // 获取所有表格

        for (int m = 0; m < 2; m++) {
            // 获取表格的行
            if (talbleIter.hasNext()) {
                Table table = (Table)talbleIter.next();
                if (table.numRows() == 1) {
                    // 跳过空表格
                    break;
                }
                for (int i = 0; i < table.numRows(); i++) {
                    // 获取表格的每个单元格
                    TableRow tr = table.getRow(i);
                    for (int j = 0; j < tr.numCells(); j++) {
                        // 获取单元格的内容
                        TableCell cell = tr.getCell(j);
                        String text = "";
                        for (int k = 0; k < cell.numParagraphs(); k++) {
                            Paragraph para = cell.getParagraph(k);
                            text += para.text().trim() + " ";
                        }
                        System.out.print(text + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void printTable(String fileName) throws FileNotFoundException, IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(fileName));
        // 获取所有表格
        List<XWPFTable> tables = document.getTables();
        for (XWPFTable table : tables) {
            // 获取表格的行
            List<XWPFTableRow> rows = table.getRows();
            int i = 1;
            for (XWPFTableRow row : rows) {
                // 获取表格的每个单元格
                List<XWPFTableCell> tableCells = row.getTableCells();

                for (int j = 0; j < tableCells.size(); j++) {
                    XWPFTableCell cell = tableCells.get(j);
                    // 获取单元格的内容
                    String text = cell.getText();
                    if (j == (tableCells.size() - 1)) {
                        System.out.print(text);
                    } else {
                        System.out.print(text + " | ");
                    }

                }
                if (i == 1) {
                    System.out.println();
                    for (int k = 0; k < tableCells.size() - 1; k++) {

                        if (k == (tableCells.size() - 2)) {
                            System.out.print("-");
                        } else {
                            System.out.print("-|");
                        }
                    }
                    i++;
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        test();
        /*
         * try { printTableH("E:\\test4.doc"); } catch (FileNotFoundException e)
         * { // TODO Auto-generated catch block e.printStackTrace(); } catch
         * (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
    }

    public static void test() throws IOException {
        InputStream is = new FileInputStream(new File("E:\\test4.doc")); // 读取文件
        POIFSFileSystem fs = new POIFSFileSystem(is);
        @SuppressWarnings("resource")
        HWPFDocument document = new HWPFDocument(fs);
        Range range = document.getRange(); // 存储word内容到document中

        for (int i = 0; i < 100; i++) { // numparagraphs代表段落总数
            int setparagraph = i; // 记录当前段落
            Paragraph para_1 = range.getParagraph(i);// 获取第i段
            Paragraph para_2 = range.getParagraph(i + 1);// 获取第i+1段
            Paragraph para_3 = range.getParagraph(i + 2);// 获取第i+2段

            String paratext1 = para_1.text().trim().replaceAll("\r\n", ""); // 当前段落的内容并去除换行
            String paratext2 = para_2.text().trim().replaceAll("\r\n", ""); // 当前段落的内容并去除换行
            CharacterRun run1 = para_1.getCharacterRun(0);
            CharacterRun run2 = para_2.getCharacterRun(0);
            CharacterRun run3 = para_3.getCharacterRun(0); // 段落属性
            if (paratext1.length() > 0 && paratext2.length() > 0) {

                if (run1.getFontSize() > run2.getFontSize() && run2.getFontSize() > run3.getFontSize())
                    continue; // 当连续三个及以上的字体大小不同的段落存在时则跳过当前循环，直到两个段落存在（找到小标题和内容）
                String content = "";
                if (run1.getFontSize() >= run2.getFontSize()) { // 当两段内容字体大小为大于时
                                                                // 则为标题和内容
                                                                // 相等时则同为内容
                    content += paratext2; // 第i+1段为内容
                    run1 = run2;
                    run2 = run3; // 顺序重新定位段落
                    setparagraph++;
                }
                System.out.println(paratext1 + "\t" + content);
                i = setparagraph;
            }
        }
    }
}
