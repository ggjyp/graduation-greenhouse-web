package com.jyp.greenhouse.core.tag;




import com.jyp.greenhouse.core.util.TimeUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by oplus on 2017/3/7.
 */
public class DateTag extends TagSupport {
    private static final long serialVersionUID = 6464168398214506236L;

    private String value;
    private String pattern;

    @Override
    public int doStartTag() throws JspException {
        String vv = "" + value;
        String patt = "" + pattern;
        try {
            String s = TimeUtil.TimeStampDate(patt,vv);
            pageContext.getOut().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
