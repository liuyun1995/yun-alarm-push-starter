package com.liuyun.github.utils;

import com.google.common.io.Resources;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

public class TemplateUtils {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

    public static String getRenderString(String path, String fragment, Object model) {
        try {
            String template = getTemplateString(path);
            StringWriter out = new StringWriter();
            new Template(fragment, template, CONFIGURATION).process(model, out);
            return out.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTemplateString(String path) {
        try {
            List<String> lines = Resources.readLines(Resources.getResource(path), Charset.forName("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
