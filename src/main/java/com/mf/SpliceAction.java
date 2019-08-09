package com.mf;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.intellij.ide.CopyProvider;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.mf.service.UtilService;
import com.mf.util.ClipBoardUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Objects;

public class SpliceAction extends AnAction {
    private SqlToolWindow sqlToolWindow;
    private ToolWindow toolWindow;
    private final String splitStr = "\n---------------------------------------------------------------------------------------------\n";

    public SpliceAction() {
        super("sql merge");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        //初始化工具栏
        instanceToolWindows(e);
        try {
            String string = getString(e);
            //初始化解析类
            UtilService instance = UtilFactory.getInstance();
            //解析出 sql和参数
            SqlInfo sqlInfo = instance.analyse(string);
            //合并sql和参数
            String merge = instance.merge(sqlInfo.getSql(), sqlInfo.getParams());
            String s = SQLUtils.format(merge, JdbcConstants.MYSQL);
            JTextArea console = sqlToolWindow.getConsole();
            //复制内容到剪贴板
            ClipBoardUtil.setSysClipboardText(s);
            if (StringUtils.isEmpty(s)) {
                s = "无法识别有效内容";
            }
            console.append(s);
            console.append(splitStr);

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    /**
     * 获取事件内容
     *
     */
    public static String getString(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        CopyProvider provider = PlatformDataKeys.COPY_PROVIDER.getData(dataContext);
        if (provider == null) {
            return null;
        }
        provider.performCopy(dataContext);
        Transferable contents = CopyPasteManager.getInstance().getContents();
        try {
            return contents == null ? null : (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception e1) {
            return null;
        }
    }

    /**
     * 初始化工具栏
     */
    public void instanceToolWindows(AnActionEvent e) {
        if (sqlToolWindow == null) {
            if (toolWindow == null) {
                ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(Objects.requireNonNull(e.getProject()));
                toolWindow = toolWindowManager.registerToolWindow("sql merge", false, ToolWindowAnchor.BOTTOM, true);
            }
            sqlToolWindow = new SqlToolWindow(toolWindow);
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content content = contentFactory.createContent(sqlToolWindow.getContent(), "sql merge", false);
            toolWindow.getContentManager().addContent(content);
            toolWindow.show(() -> {
            });
        }
    }
}
