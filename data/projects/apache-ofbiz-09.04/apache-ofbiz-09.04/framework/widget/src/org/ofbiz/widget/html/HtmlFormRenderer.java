/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.ofbiz.widget.html;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javolution.util.FastList;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.StringUtil;
import org.ofbiz.base.util.UtilGenerics;
import org.ofbiz.base.util.UtilHttp;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.base.util.string.FlexibleStringExpander;
import org.ofbiz.webapp.control.RequestHandler;
import org.ofbiz.webapp.taglib.ContentUrlTag;
import org.ofbiz.widget.WidgetWorker;
import org.ofbiz.widget.form.FormStringRenderer;
import org.ofbiz.widget.form.ModelForm;
import org.ofbiz.widget.form.ModelFormField;
import org.ofbiz.widget.form.ModelFormField.CheckField;
import org.ofbiz.widget.form.ModelFormField.DateFindField;
import org.ofbiz.widget.form.ModelFormField.DateTimeField;
import org.ofbiz.widget.form.ModelFormField.DisplayEntityField;
import org.ofbiz.widget.form.ModelFormField.DisplayField;
import org.ofbiz.widget.form.ModelFormField.DropDownField;
import org.ofbiz.widget.form.ModelFormField.FileField;
import org.ofbiz.widget.form.ModelFormField.HiddenField;
import org.ofbiz.widget.form.ModelFormField.HyperlinkField;
import org.ofbiz.widget.form.ModelFormField.IgnoredField;
import org.ofbiz.widget.form.ModelFormField.ImageField;
import org.ofbiz.widget.form.ModelFormField.LookupField;
import org.ofbiz.widget.form.ModelFormField.PasswordField;
import org.ofbiz.widget.form.ModelFormField.RadioField;
import org.ofbiz.widget.form.ModelFormField.RangeFindField;
import org.ofbiz.widget.form.ModelFormField.ResetField;
import org.ofbiz.widget.form.ModelFormField.SubmitField;
import org.ofbiz.widget.form.ModelFormField.TextField;
import org.ofbiz.widget.form.ModelFormField.TextFindField;
import org.ofbiz.widget.form.ModelFormField.TextareaField;

/**
 * Widget Library - HTML Form Renderer implementation
 */
public class HtmlFormRenderer extends HtmlWidgetRenderer implements FormStringRenderer {

    public static final String module = HtmlFormRenderer.class.getName();

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected RequestHandler rh;
    protected String lastFieldGroupId = "";
    protected boolean renderPagination = true;
    protected boolean javaScriptEnabled = false;
    private StringUtil.SimpleEncoder internalEncoder;

    protected HtmlFormRenderer() {}

    public HtmlFormRenderer(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        ServletContext ctx = (ServletContext) request.getAttribute("servletContext");
        this.rh = (RequestHandler) ctx.getAttribute("_REQUEST_HANDLER_");
        this.javaScriptEnabled = UtilHttp.isJavaScriptEnabled(request);
        this.internalEncoder = StringUtil.getEncoder("string");
    }

    public boolean getRenderPagination() {
        return this.renderPagination;
    }

    public void setRenderPagination(boolean renderPagination) {
        this.renderPagination = renderPagination;
    }

    public void appendOfbizUrl(Appendable writer, String location) throws IOException {
        writer.append(this.rh.makeLink(this.request, this.response, location));
    }

    public void appendContentUrl(Appendable writer, String location) throws IOException {
        StringBuffer buffer = new StringBuffer();
        ContentUrlTag.appendContentPrefix(this.request, buffer);
        writer.append(buffer.toString());
        writer.append(location);
    }

    public void appendTooltip(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        // render the tooltip, in other methods too
        String tooltip = modelFormField.getTooltip(context);
        if (UtilValidate.isNotEmpty(tooltip)) {
            writer.append("<span class=\"");
            String tooltipStyle = modelFormField.getTooltipStyle();
            if (UtilValidate.isNotEmpty(tooltipStyle)) {
                writer.append(tooltipStyle);
            } else {
                writer.append("tooltip");
            }
            writer.append("\">");
            writer.append(tooltip);
            writer.append("</span>");
        }
    }

    public void addAsterisks(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {

        boolean requiredField = modelFormField.getRequiredField();
        if (requiredField) {
            String requiredStyle = modelFormField.getRequiredFieldStyle();

            if (UtilValidate.isEmpty(requiredStyle)) {
               writer.append("*");
            }
        }
    }

    public void appendClassNames(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        String className = modelFormField.getWidgetStyle();
        if (UtilValidate.isNotEmpty(className) || modelFormField.shouldBeRed(context)) {
            writer.append(" class=\"");
            writer.append(className);
            // add a style of red if redWhen is true
            if (modelFormField.shouldBeRed(context)) {
                writer.append(" alert");
            }
            writer.append('"');
        }
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderDisplayField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.DisplayField)
     */
    public void renderDisplayField(Appendable writer, Map<String, Object> context, DisplayField displayField) throws IOException {
        ModelFormField modelFormField = displayField.getModelFormField();
        ModelForm modelForm = modelFormField.getModelForm();

        StringBuffer str = new StringBuffer();

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName) && ("list".equals(modelForm.getType()) || "multi".equals(modelForm.getType()))) {
            idName += "_" + modelForm.getRowCount();
        }

        if (UtilValidate.isNotEmpty(modelFormField.getWidgetStyle()) || modelFormField.shouldBeRed(context)) {
            str.append("<span class=\"");
            str.append(modelFormField.getWidgetStyle());
            str.append('"');
            if (UtilValidate.isNotEmpty(idName)) {
                str.append(" id=\"");
                str.append(idName);
                str.append('"');
            }
            // add a style of red if this is a date/time field and redWhen is true
            if (modelFormField.shouldBeRed(context)) {
                str.append(" alert");
            }
            str.append('>');
        }

        if (str.length() > 0) {
            writer.append(str.toString());
        }
        String description = displayField.getDescription(context);
        //Replace new lines with <br/>
        description = description.replaceAll("\n", "<br/>");

        if (UtilValidate.isEmpty(description)) {
            this.renderFormatEmptySpace(writer, context, modelFormField.getModelForm());
        } else {
            writer.append(description);
        }

        if (str.length() > 0) {
            writer.append("</span>");
        }

        ModelFormField.InPlaceEditor inPlaceEditor = displayField.getInPlaceEditor();
        boolean ajaxEnabled = inPlaceEditor != null && this.javaScriptEnabled;

        if (ajaxEnabled) {
            writer.append("<script language=\"JavaScript\" type=\"text/javascript\">");
            String url = inPlaceEditor.getUrl(context);
            Map<String, Object> fieldMap = inPlaceEditor.getFieldMap(context);
            if (fieldMap != null) {
                url += '?';
                Set<Entry<String, Object>> fieldSet = fieldMap.entrySet();
                Iterator<Entry<String, Object>> fieldIterator = fieldSet.iterator();
                int count = 0;
                while (fieldIterator.hasNext()) {
                    count++;
                    Entry<String, Object> field = fieldIterator.next();
                    url += (String) field.getKey() + '=' + (String) field.getValue();
                    if (count < fieldSet.size()) {
                        url += '&';
                    }
                }
            }
            writer.append("ajaxInPlaceEditDisplayField('");
            writer.append(idName + "', '" +url+ "', {");
            if (UtilValidate.isNotEmpty(inPlaceEditor.getParamName())) {
                writer.append("paramName: '" +inPlaceEditor.getParamName()+ "'");
            } else {
                writer.append("paramName: '" +modelFormField.getFieldName()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getCancelControl())) {
                writer.append(", cancelControl: ");
                if (!"false".equals(inPlaceEditor.getCancelControl())) {
                    writer.append("'");
                }
                writer.append(inPlaceEditor.getCancelControl());
                if (!"false".equals(inPlaceEditor.getCancelControl())) {
                    writer.append("'");
                }
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getCancelText())) {
                writer.append(", cancelText: '" +inPlaceEditor.getCancelText()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getClickToEditText())) {
                writer.append(", clickToEditText: '" +inPlaceEditor.getClickToEditText()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getFieldPostCreation())) {
                writer.append(", fieldPostCreation: ");
                if (!"false".equals(inPlaceEditor.getFieldPostCreation())) {
                    writer.append("'");
                }
                writer.append(inPlaceEditor.getFieldPostCreation());
                if (!"false".equals(inPlaceEditor.getFieldPostCreation())) {
                    writer.append("'");
                }
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getFormClassName())) {
                writer.append(", formClassName: '" +inPlaceEditor.getFormClassName()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getHighlightColor())) {
                writer.append(", highlightColor: '" +inPlaceEditor.getHighlightColor()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getHighlightEndColor())) {
                writer.append(", highlightEndColor: '" +inPlaceEditor.getHighlightEndColor()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getHoverClassName())) {
                writer.append(", hoverClassName: '" +inPlaceEditor.getHoverClassName()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getHtmlResponse())) {
                writer.append(", htmlResponse: " +inPlaceEditor.getHtmlResponse());
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getLoadingClassName())) {
                writer.append(", loadingClassName: '" +inPlaceEditor.getLoadingClassName()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getLoadingText())) {
                writer.append(", loadingText: '" +inPlaceEditor.getLoadingText()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getOkControl())) {
                writer.append(", okControl: ");
                if (!"false".equals(inPlaceEditor.getOkControl())) {
                    writer.append("'");
                }
                writer.append(inPlaceEditor.getOkControl());
                if (!"false".equals(inPlaceEditor.getOkControl())) {
                    writer.append("'");
                }
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getOkText())) {
                writer.append(", okText: '" +inPlaceEditor.getOkText()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getSavingClassName())) {
                writer.append(", savingClassName: '" +inPlaceEditor.getSavingClassName()+ "', ");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getSavingText())) {
                writer.append(", savingText: '" +inPlaceEditor.getSavingText()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getSubmitOnBlur())) {
                writer.append(", submitOnBlur: " +inPlaceEditor.getSubmitOnBlur());
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getTextBeforeControls())) {
                writer.append(", textBeforeControls: '" +inPlaceEditor.getTextBeforeControls()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getTextAfterControls())) {
                writer.append(", textAfterControls: '" +inPlaceEditor.getTextAfterControls()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getTextBetweenControls())) {
                writer.append(", textBetweenControls: '" +inPlaceEditor.getTextBetweenControls()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getUpdateAfterRequestCall())) {
                writer.append(", updateAfterRequestCall: " +inPlaceEditor.getUpdateAfterRequestCall());
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getRows())) {
                writer.append(", rows: '" +inPlaceEditor.getRows()+ "'");
            }
            if (UtilValidate.isNotEmpty(inPlaceEditor.getCols())) {
                writer.append(", cols: '" +inPlaceEditor.getCols()+ "'");
            }
            writer.append("});");
            writer.append("</script>");
        }

        if (displayField instanceof DisplayEntityField) {
            this.makeHyperlinkString(writer, ((DisplayEntityField) displayField).getSubHyperlink(), context);
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderHyperlinkField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.HyperlinkField)
     */
    public void renderHyperlinkField(Appendable writer, Map<String, Object> context, HyperlinkField hyperlinkField) throws IOException {
        this.request.setAttribute("image", hyperlinkField.getImage());
        ModelFormField modelFormField = hyperlinkField.getModelFormField();
        String description = encode(hyperlinkField.getDescription(context), modelFormField, context);

        WidgetWorker.makeHyperlinkByType(writer, hyperlinkField.getLinkType(), modelFormField.getWidgetStyle(), hyperlinkField.getTargetType(), hyperlinkField.getTarget(context),
                hyperlinkField.getParameterList(), description, hyperlinkField.getTargetWindow(context), modelFormField,
                this.request, this.response, context);

        this.appendTooltip(writer, context, modelFormField);
        //appendWhitespace(writer);
    }

    public void makeHyperlinkString(Appendable writer, ModelFormField.SubHyperlink subHyperlink, Map<String, Object> context) throws IOException {
        if (subHyperlink == null) {
            return;
        }
        if (subHyperlink.shouldUse(context)) {
            writer.append(' ');
            String description = encode(subHyperlink.getDescription(context), subHyperlink.getModelFormField(), context);
            WidgetWorker.makeHyperlinkByType(writer, subHyperlink.getLinkType(), subHyperlink.getLinkStyle(), subHyperlink.getTargetType(), subHyperlink.getTarget(context),
                    subHyperlink.getParameterList(), description, subHyperlink.getTargetWindow(context), subHyperlink.getModelFormField(),
                    this.request, this.response, context);
        }
    }

    private String encode(String value, ModelFormField modelFormField, Map<String, Object> context) {
        if (UtilValidate.isEmpty(value)) {
            return value;
        }
        StringUtil.SimpleEncoder encoder = (StringUtil.SimpleEncoder)context.get("simpleEncoder");
        if (modelFormField.getEncodeOutput() && encoder != null) {
            value = encoder.encode(value);
        } else {
            value = internalEncoder.encode(value);
        }
        return value;
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderTextField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.TextField)
     */
    public void renderTextField(Appendable writer, Map<String, Object> context, TextField textField) throws IOException {
        ModelFormField modelFormField = textField.getModelFormField();

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String value = modelFormField.getEntry(context, textField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(textField.getSize()));
        writer.append('"');

        Integer maxlength = textField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName)) {
            writer.append(" id=\"");
            writer.append(idName);
            writer.append('"');
        }

        String event = modelFormField.getEvent();
        String action = modelFormField.getAction(context);
        if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
            writer.append(" ");
            writer.append(event);
            writer.append("=\"");
            writer.append(action);
            writer.append('"');
        }

        List<ModelForm.UpdateArea> updateAreas = modelFormField.getOnChangeUpdateAreas();
        boolean ajaxEnabled = updateAreas != null && this.javaScriptEnabled;
        if (!textField.getClientAutocompleteField() || ajaxEnabled) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        this.addAsterisks(writer, context, modelFormField);

        this.makeHyperlinkString(writer, textField.getSubHyperlink(), context);

        this.appendTooltip(writer, context, modelFormField);

        if (ajaxEnabled) {
            appendWhitespace(writer);
            writer.append("<script language=\"JavaScript\" type=\"text/javascript\">");
            appendWhitespace(writer);
            writer.append("ajaxAutoCompleter('" + createAjaxParamsFromUpdateAreas(updateAreas, null, context) + "');");
            appendWhitespace(writer);
            writer.append("</script>");
        }
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderTextareaField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.TextareaField)
     */
    public void renderTextareaField(Appendable writer, Map<String, Object> context, TextareaField textareaField) throws IOException {
        ModelFormField modelFormField = textareaField.getModelFormField();

        writer.append("<textarea");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        writer.append(" cols=\"");
        writer.append(Integer.toString(textareaField.getCols()));
        writer.append('"');

        writer.append(" rows=\"");
        writer.append(Integer.toString(textareaField.getRows()));
        writer.append('"');

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName)) {
            writer.append(" id=\"");
            writer.append(idName);
            writer.append('"');
        } else if (textareaField.getVisualEditorEnable()) {
            writer.append(" id=\"");
            writer.append("htmlEditArea");
            writer.append('"');
        }

        if (textareaField.isReadOnly()) {
            writer.append(" readonly");
        }

        writer.append('>');

        String value = modelFormField.getEntry(context, textareaField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(value);
        }

        writer.append("</textarea>");

        if (textareaField.getVisualEditorEnable()) {
            writer.append("<script language=\"javascript\" src=\"/images/htmledit/whizzywig.js\" type=\"text/javascript\"></script>");
            writer.append("<script language=\"javascript\" type=\"text/javascript\"> buttonPath = \"/images/htmledit/\"; cssFile=\"/images/htmledit/simple.css\";makeWhizzyWig(\"");
            if (UtilValidate.isNotEmpty(idName)) {
                writer.append(idName);
            } else {
                writer.append("htmlEditArea");
            }
            writer.append("\",\"");
            String buttons = textareaField.getVisualEditorButtons(context);
            if (UtilValidate.isNotEmpty(buttons)) {
                writer.append(buttons);
            } else {
                writer.append("all");
            }
            writer.append("\") </script>");
        }

        this.addAsterisks(writer, context, modelFormField);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderDateTimeField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.DateTimeField)
     */
    public void renderDateTimeField(Appendable writer, Map<String, Object> context, DateTimeField dateTimeField) throws IOException {
        ModelFormField modelFormField = dateTimeField.getModelFormField();
        String paramName = modelFormField.getParameterName(context);
        String defaultDateTimeString = dateTimeField.getDefaultDateTimeString(context);

        Map<String, String> uiLabelMap = UtilGenerics.checkMap(context.get("uiLabelMap"));
        if (uiLabelMap == null) {
            Debug.logWarning("Could not find uiLabelMap in context", module);
        }
        String localizedInputTitle = "" , localizedIconTitle = "";

        // whether the date field is short form, yyyy-mm-dd
        boolean shortDateInput = ("date".equals(dateTimeField.getType()) || "time-dropdown".equals(dateTimeField.getInputMethod()) ? true : false);

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        if ("time-dropdown".equals(dateTimeField.getInputMethod())) {
            writer.append(UtilHttp.makeCompositeParam(paramName, "date"));
        } else {
            writer.append(paramName);
        }
        writer.append('"');

        // the default values for a timestamp
        int size = 25;
        int maxlength = 30;

        if (shortDateInput) {
            size = maxlength = 10;
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatDate");
            }
        } else if ("time".equals(dateTimeField.getType())) {
            size = maxlength = 8;
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatTime");
            }
        } else {
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatDateTime");
            }
        }
        writer.append(" title=\"");
        writer.append(localizedInputTitle);
        writer.append('"');

        String value = modelFormField.getEntry(context, dateTimeField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            if (value.length() > maxlength) {
                value = value.substring(0, maxlength);
            }
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(size));
        writer.append('"');

        writer.append(" maxlength=\"");
        writer.append(Integer.toString(maxlength));
        writer.append('"');

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName)) {
            writer.append(" id=\"");
            writer.append(idName);
            writer.append('"');
        }

        writer.append("/>");

        // search for a localized label for the icon
        if (uiLabelMap != null) {
            localizedIconTitle = (String) uiLabelMap.get("CommonViewCalendar");
        }

        // add calendar pop-up button and seed data IF this is not a "time" type date-time
        if (!"time".equals(dateTimeField.getType())) {
            if (shortDateInput) {
                writer.append("<a href=\"javascript:call_cal_notime(document.");
            } else {
                writer.append("<a href=\"javascript:call_cal(document.");
            }
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append('.');
            if ("time-dropdown".equals(dateTimeField.getInputMethod())) {
                writer.append(UtilHttp.makeCompositeParam(paramName, "date"));
            } else {
                writer.append(paramName);
            }
            writer.append(",'");
            writer.append(UtilHttp.encodeBlanks(modelFormField.getEntry(context, defaultDateTimeString)));
            writer.append("');\">");

            writer.append("<img src=\"");
            this.appendContentUrl(writer, "/images/cal.gif");
            writer.append("\" width=\"16\" height=\"16\" border=\"0\" alt=\"");
            writer.append(localizedIconTitle);
            writer.append("\" title=\"");
            writer.append(localizedIconTitle);
            writer.append("\"/></a>");
        }

        // if we have an input method of time-dropdown, then render two dropdowns
        if ("time-dropdown".equals(dateTimeField.getInputMethod())) {
            String className = modelFormField.getWidgetStyle();
            String classString = (className != null ? " class=\"" + className + "\" " : "");
            boolean isTwelveHour = "12".equals(dateTimeField.getClock());

            // set the Calendar to the default time of the form or now()
            Calendar cal = null;
            try {
                Timestamp defaultTimestamp = Timestamp.valueOf(modelFormField.getEntry(context, defaultDateTimeString));
                cal = Calendar.getInstance();
                cal.setTime(defaultTimestamp);
            } catch (IllegalArgumentException e) {
                Debug.logWarning("Form widget field [" + paramName + "] with input-method=\"time-dropdown\" was not able to understand the default time ["
                        + defaultDateTimeString + "]. The parsing error was: " + e.getMessage(), module);
            }

            // write the select for hours
            writer.append("&nbsp;<select name=\"" + UtilHttp.makeCompositeParam(paramName, "hour") + "\"");
            writer.append(classString + ">");

            // keep the two cases separate because it's hard to understand a combined loop
            if (isTwelveHour) {
                for (int i = 1; i <= 12; i++) {
                    writer.append("<option value=\"" + Integer.toString(i) + "\"");
                    if (cal != null) {
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        if (hour == 0) hour = 12;
                        if (hour > 12) hour -= 12;
                        if (i == hour) writer.append(" selected");
                    }
                    writer.append(">" + Integer.toString(i) + "</option>");
                }
            } else {
                for (int i = 0; i < 24; i++) {
                    writer.append("<option value=\"" + Integer.toString(i) + "\"");
                    if (cal != null && i == cal.get(Calendar.HOUR_OF_DAY)) {
                        writer.append(" selected");
                    }
                    writer.append(">" + Integer.toString(i) + "</option>");
                }
            }

            // write the select for minutes
            writer.append("</select>:<select name=\"");
            writer.append(UtilHttp.makeCompositeParam(paramName, "minutes") + "\"");
            writer.append(classString + ">");
            for (int i = 0; i < 60; i++) {
                writer.append("<option value=\"" + Integer.toString(i) + "\"");
                if (cal != null && i == cal.get(Calendar.MINUTE)) {
                    writer.append(" selected");
                }
                writer.append(">" + Integer.toString(i) + "</option>");
            }
            writer.append("</select>");

            // if 12 hour clock, write the AM/PM selector
            if (isTwelveHour) {
                String amSelected = ((cal != null && cal.get(Calendar.AM_PM) == Calendar.AM) ? "selected" : "");
                String pmSelected = ((cal != null && cal.get(Calendar.AM_PM) == Calendar.PM) ? "selected" : "");
                writer.append("<select name=\"" + UtilHttp.makeCompositeParam(paramName, "ampm") + "\"");
                writer.append(classString + ">");
                writer.append("<option value=\"" + "AM" + "\" " + amSelected + ">AM</option>");
                writer.append("<option value=\"" + "PM" + "\" " + pmSelected + ">PM</option>");
                writer.append("</select>");
            }

            // create a hidden field for the composite type, which is a Timestamp
            writer.append("<input type=\"hidden\" name=\"");
            writer.append(UtilHttp.makeCompositeParam(paramName, "compositeType"));
            writer.append("\" value=\"Timestamp\"/>");
        }

        this.addAsterisks(writer, context, modelFormField);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderDropDownField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.DropDownField)
     */
    public void renderDropDownField(Appendable writer, Map<String, Object> context, DropDownField dropDownField) throws IOException {
        ModelFormField modelFormField = dropDownField.getModelFormField();
        ModelForm modelForm = modelFormField.getModelForm();
        ModelFormField.AutoComplete autoComplete = dropDownField.getAutoComplete();
        boolean ajaxEnabled = autoComplete != null && this.javaScriptEnabled;
        List<ModelFormField.OptionValue> allOptionValues = dropDownField.getAllOptionValues(context, modelForm.getDelegator(context));

        String event = modelFormField.getEvent();
        String action = modelFormField.getAction(context);

        String currentValue = modelFormField.getEntry(context);
        // Get the current value's description from the option value. If there
        // is a localized version it will be there.
        String currentDescription = null;
        if (UtilValidate.isNotEmpty(currentValue)) {
            for (ModelFormField.OptionValue optionValue : allOptionValues) {
                if (encode(optionValue.getKey(), modelFormField, context).equals(currentValue)) {
                    currentDescription = optionValue.getDescription();
                    break;
                }
            }
        }

        if (ajaxEnabled) {
            writer.append("<input type=\"text\"");
        } else {
            writer.append("<select");
        }

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));

        String idName = modelFormField.getIdName();

        if (ajaxEnabled) {
            writer.append("_description\"");

            String textFieldIdName = idName;
            if (UtilValidate.isNotEmpty(textFieldIdName)) {
                textFieldIdName += "_description";
                writer.append(" id=\"");
                writer.append(textFieldIdName);
                writer.append('"');
            }

            if (UtilValidate.isNotEmpty(currentValue)) {
                writer.append(" value=\"");
                String explicitDescription = (currentDescription != null ? currentDescription : dropDownField.getCurrentDescription(context));
                if (UtilValidate.isNotEmpty(explicitDescription)) {
                    writer.append(encode(explicitDescription, modelFormField, context));
                } else {
                    writer.append(ModelFormField.FieldInfoWithOptions.getDescriptionForOptionKey(currentValue, allOptionValues));
                }
                writer.append('"');
            }
            writer.append("/>");

            appendWhitespace(writer);
            writer.append("<input type=\"hidden\"");
            writer.append(" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append('"');
            if (UtilValidate.isNotEmpty(idName)) {
                writer.append(" id=\"");
                writer.append(idName);
                writer.append('"');
            }

            if (UtilValidate.isNotEmpty(currentValue)) {
                writer.append(" value=\"");
                //String explicitDescription = dropDownField.getCurrentDescription(context);
                writer.append(currentValue);
                writer.append('"');
            }

            writer.append("/>");

            appendWhitespace(writer);
            writer.append("<script language=\"JavaScript\" type=\"text/javascript\">");
            appendWhitespace(writer);
            writer.append("var data = {");
            int count = 0;
            for (ModelFormField.OptionValue optionValue: allOptionValues) {
                count++;
                writer.append("" + optionValue.getKey() + ": ");
                writer.append(" '" + optionValue.getDescription() + "'");
                if (count != allOptionValues.size()) {
                    writer.append(", ");
                }
            }
            writer.append("};");
            appendWhitespace(writer);
            writer.append("ajaxAutoCompleteDropDown('" + textFieldIdName + "', '" + idName + "', data, {autoSelect: " +
                    autoComplete.getAutoSelect() + ", frequency: " + autoComplete.getFrequency() + ", minChars: " + autoComplete.getMinChars() +
                    ", choices: " + autoComplete.getChoices() + ", partialSearch: " + autoComplete.getPartialSearch() +
                    ", partialChars: " + autoComplete.getPartialChars() + ", ignoreCase: " + autoComplete.getIgnoreCase() +
                    ", fullSearch: " + autoComplete.getFullSearch() + "});");
            appendWhitespace(writer);
            writer.append("</script>");
        } else {
            writer.append('"');

            if (UtilValidate.isNotEmpty(idName)) {
                writer.append(" id=\"");
                writer.append(idName);
                writer.append('"');
            }

            if (dropDownField.isAllowMultiple()) {
                writer.append(" multiple=\"multiple\"");
            }

            int otherFieldSize = dropDownField.getOtherFieldSize();
            String otherFieldName = dropDownField.getParameterNameOther(context);
            if (otherFieldSize > 0) {
                //writer.append(" onchange=\"alert('ONCHANGE, process_choice:' + process_choice)\"");
                //writer.append(" onchange='test_js()' ");
                writer.append(" onchange=\"process_choice(this,document.");
                writer.append(modelForm.getName());
                writer.append(".");
                writer.append(otherFieldName);
                writer.append(")\" ");
            }


            if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
                writer.append(" ");
                writer.append(event);
                writer.append("=\"");
                writer.append(action);
                writer.append('"');
            }

            writer.append(" size=\"" + dropDownField.getSize() + "\">");

            // if the current value should go first, stick it in
            if (UtilValidate.isNotEmpty(currentValue) && "first-in-list".equals(dropDownField.getCurrent())) {
                writer.append("<option");
                writer.append(" selected=\"selected\"");
                writer.append(" value=\"");
                writer.append(currentValue);
                writer.append("\">");
                String explicitDescription = (currentDescription != null ? currentDescription : dropDownField.getCurrentDescription(context));
                if (UtilValidate.isNotEmpty(explicitDescription)) {
                    writer.append(encode(explicitDescription, modelFormField, context));
                } else {
                    writer.append(ModelFormField.FieldInfoWithOptions.getDescriptionForOptionKey(currentValue, allOptionValues));
                }
                writer.append("</option>");

                // add a "separator" option
                writer.append("<option value=\"");
                writer.append(currentValue);
                writer.append("\">---</option>");
            }

            // if allow empty is true, add an empty option
            if (dropDownField.isAllowEmpty()) {
                writer.append("<option value=\"\">&nbsp;</option>");
            }

            // list out all options according to the option list
            Iterator<ModelFormField.OptionValue> optionValueIter = allOptionValues.iterator();
            while (optionValueIter.hasNext()) {
                ModelFormField.OptionValue optionValue = (ModelFormField.OptionValue) optionValueIter.next();
                String noCurrentSelectedKey = dropDownField.getNoCurrentSelectedKey(context);
                writer.append("<option");
                // if current value should be selected in the list, select it
                if (UtilValidate.isNotEmpty(currentValue) && currentValue.equals(encode(optionValue.getKey(), modelFormField, context)) && "selected".equals(dropDownField.getCurrent())) {
                    writer.append(" selected=\"selected\"");
                } else if (UtilValidate.isEmpty(currentValue) && noCurrentSelectedKey != null && noCurrentSelectedKey.equals(optionValue.getKey())) {
                    writer.append(" selected=\"selected\"");
                }
                writer.append(" value=\"");
                writer.append(encode(optionValue.getKey(), modelFormField, context));
                writer.append("\">");
                writer.append(encode(optionValue.getDescription(), modelFormField, context));
                writer.append("</option>");
            }

            writer.append("</select>");


            // Adapted from work by Yucca Korpela
            // http://www.cs.tut.fi/~jkorpela/forms/combo.html
            if (otherFieldSize > 0) {

                String fieldName = modelFormField.getParameterName(context);
                Map<String, Object> dataMap = UtilGenerics.checkMap(modelFormField.getMap(context));
                if (dataMap == null) {
                    dataMap = context;
                }
                Object otherValueObj = dataMap.get(otherFieldName);
                String otherValue = (otherValueObj == null) ? "" : otherValueObj.toString();

                writer.append("<noscript>");
                writer.append("<input type='text' name='");
                writer.append(otherFieldName);
                writer.append("'/> ");
                writer.append("</noscript>");
                writer.append("\n<script type='text/javascript' language='JavaScript'><!--");
                writer.append("\ndisa = ' disabled';");
                writer.append("\nif (other_choice(document.");
                writer.append(modelForm.getName());
                writer.append(".");
                writer.append(fieldName);
                writer.append(")) disa = '';");
                writer.append("\ndocument.write(\"<input type=");
                writer.append("'text' name='");
                writer.append(otherFieldName);
                writer.append("' value='");
                writer.append(otherValue);
                writer.append("' size='");
                writer.append(Integer.toString(otherFieldSize));
                writer.append("' ");
                writer.append("\" +disa+ \" onfocus='check_choice(document.");
                writer.append(modelForm.getName());
                writer.append(".");
                writer.append(fieldName);
                writer.append(")'/>\");");
                writer.append("\nif (disa && document.styleSheets)");
                writer.append(" document.");
                writer.append(modelForm.getName());
                writer.append(".");
                writer.append(otherFieldName);
                writer.append(".style.visibility  = 'hidden';");
                writer.append("\n//--></script>");
            }
        }

        this.makeHyperlinkString(writer, dropDownField.getSubHyperlink(), context);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderCheckField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.CheckField)
     */
    public void renderCheckField(Appendable writer, Map<String, Object> context, CheckField checkField) throws IOException {
        ModelFormField modelFormField = checkField.getModelFormField();
        ModelForm modelForm = modelFormField.getModelForm();
        String currentValue = modelFormField.getEntry(context);
        Boolean allChecked = checkField.isAllChecked(context);

        List<ModelFormField.OptionValue> allOptionValues = checkField.getAllOptionValues(context, modelForm.getDelegator(context));
        String event = modelFormField.getEvent();
        String action = modelFormField.getAction(context);

        // list out all options according to the option list
        Iterator<ModelFormField.OptionValue> optionValueIter = allOptionValues.iterator();
        while (optionValueIter.hasNext()) {
            ModelFormField.OptionValue optionValue = (ModelFormField.OptionValue) optionValueIter.next();

            writer.append("<input type=\"checkbox\"");

            appendClassNames(writer, context, modelFormField);

            // if current value should be selected in the list, select it
            if (Boolean.TRUE.equals(allChecked)) {
                writer.append(" checked=\"checked\"");
            } else if (Boolean.FALSE.equals(allChecked)) {
                // do nothing
            } else if (UtilValidate.isNotEmpty(currentValue) && currentValue.equals(optionValue.getKey())) {
                writer.append(" checked=\"checked\"");
            }
            writer.append(" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append('"');
            writer.append(" value=\"");
            writer.append(encode(optionValue.getKey(), modelFormField, context));
            writer.append("\"");

            if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
                writer.append(" ");
                writer.append(event);
                writer.append("=\"");
                writer.append(action);
                writer.append('"');
            }

            writer.append("/>");

            writer.append(encode(optionValue.getDescription(), modelFormField, context));
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderRadioField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.RadioField)
     */
    public void renderRadioField(Appendable writer, Map<String, Object> context, RadioField radioField) throws IOException {
        ModelFormField modelFormField = radioField.getModelFormField();
        ModelForm modelForm = modelFormField.getModelForm();
        List<ModelFormField.OptionValue> allOptionValues = radioField.getAllOptionValues(context, modelForm.getDelegator(context));
        String currentValue = modelFormField.getEntry(context);
        String event = modelFormField.getEvent();
        String action = modelFormField.getAction(context);

        // list out all options according to the option list
        Iterator<ModelFormField.OptionValue> optionValueIter = allOptionValues.iterator();
        while (optionValueIter.hasNext()) {
            ModelFormField.OptionValue optionValue = (ModelFormField.OptionValue) optionValueIter.next();

            writer.append("<div");

            appendClassNames(writer, context, modelFormField);

            writer.append("><input type=\"radio\"");

            // if current value should be selected in the list, select it
            String noCurrentSelectedKey = radioField.getNoCurrentSelectedKey(context);
            if (UtilValidate.isNotEmpty(currentValue) && currentValue.equals(encode(optionValue.getKey(), modelFormField, context))) {
                writer.append(" checked=\"checked\"");
            } else if (UtilValidate.isEmpty(currentValue) && noCurrentSelectedKey != null && noCurrentSelectedKey.equals(optionValue.getKey())) {
                writer.append(" checked=\"checked\"");
            }
            writer.append(" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append('"');
            writer.append(" value=\"");
            writer.append(encode(optionValue.getKey(), modelFormField, context));
            writer.append("\"");

            if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
                writer.append(" ");
                writer.append(event);
                writer.append("=\"");
                writer.append(action);
                writer.append('"');
            }

            writer.append("/>");

            writer.append(encode(optionValue.getDescription(), modelFormField, context));
            writer.append("</div>");
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderSubmitField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.SubmitField)
     */
    public void renderSubmitField(Appendable writer, Map<String, Object> context, SubmitField submitField) throws IOException {
        ModelFormField modelFormField = submitField.getModelFormField();
        ModelForm modelForm = modelFormField.getModelForm();
        String event = null;
        String action = null;

        if ("text-link".equals(submitField.getButtonType())) {
            writer.append("<a");

            appendClassNames(writer, context, modelFormField);

            writer.append(" href=\"javascript:document.");
            writer.append(modelForm.getCurrentFormName(context));
            writer.append(".submit()\">");

            writer.append(encode(modelFormField.getTitle(context), modelFormField, context));

            writer.append("</a>");
        } else if ("image".equals(submitField.getButtonType())) {
            writer.append("<input type=\"image\"");

            appendClassNames(writer, context, modelFormField);

            writer.append(" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append('"');

            String title = modelFormField.getTitle(context);
            if (UtilValidate.isNotEmpty(title)) {
                writer.append(" alt=\"");
                writer.append(encode(title, modelFormField, context));
                writer.append('"');
            }

            writer.append(" src=\"");
            this.appendContentUrl(writer, submitField.getImageLocation());
            writer.append('"');

            event = modelFormField.getEvent();
            action = modelFormField.getAction(context);
            if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
                writer.append(" ");
                writer.append(event);
                writer.append("=\"");
                writer.append(action);
                writer.append('"');
            }

            writer.append("/>");
        } else {
            // default to "button"

            String formId = modelForm.getContainerId();
            List<ModelForm.UpdateArea> updateAreas = modelForm.getOnSubmitUpdateAreas();
            // This is here for backwards compatibility. Use on-event-update-area
            // elements instead.
            String backgroundSubmitRefreshTarget = submitField.getBackgroundSubmitRefreshTarget(context);
            if (UtilValidate.isNotEmpty(backgroundSubmitRefreshTarget)) {
                if (updateAreas == null) {
                    updateAreas = FastList.newInstance();
                }
                updateAreas.add(new ModelForm.UpdateArea("submit", formId, backgroundSubmitRefreshTarget));
            }

            boolean ajaxEnabled = (updateAreas != null || UtilValidate.isNotEmpty(backgroundSubmitRefreshTarget)) && this.javaScriptEnabled;
            if (ajaxEnabled) {
                writer.append("<input type=\"button\"");
            } else {
                writer.append("<input type=\"submit\"");
            }

            appendClassNames(writer, context, modelFormField);

            writer.append(" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append('"');

            String title = modelFormField.getTitle(context);
            if (UtilValidate.isNotEmpty(title)) {
                writer.append(" value=\"");
                writer.append(encode(title, modelFormField, context));
                writer.append('"');
            }


            event = modelFormField.getEvent();
            action = modelFormField.getAction(context);
            if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
                writer.append(" ");
                writer.append(event);
                writer.append("=\"");
                writer.append(action);
                writer.append('"');
            } else {
                //add single click JS onclick
                // disabling for now, using form onSubmit action instead: writer.append(singleClickAction);
            }

            if (ajaxEnabled) {
                writer.append(" onclick=\"");
                writer.append("ajaxSubmitFormUpdateAreas('");
                writer.append(formId);
                writer.append("', '" + createAjaxParamsFromUpdateAreas(updateAreas, null, context));
                writer.append("')\"");
            }

            writer.append("/>");
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderResetField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.ResetField)
     */
    public void renderResetField(Appendable writer, Map<String, Object> context, ResetField resetField) throws IOException {
        ModelFormField modelFormField = resetField.getModelFormField();

        writer.append("<input type=\"reset\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String title = modelFormField.getTitle(context);
        if (UtilValidate.isNotEmpty(title)) {
            writer.append(" value=\"");
            writer.append(encode(title, modelFormField, context));
            writer.append('"');
        }

        writer.append("/>");

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderHiddenField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.HiddenField)
     */
    public void renderHiddenField(Appendable writer, Map<String, Object> context, HiddenField hiddenField) throws IOException {
        ModelFormField modelFormField = hiddenField.getModelFormField();
        String value = hiddenField.getValue(context);
        this.renderHiddenField(writer, context, modelFormField, value);
    }

    public void renderHiddenField(Appendable writer, Map<String, Object> context, ModelFormField modelFormField, String value) throws IOException {
        writer.append("<input type=\"hidden\"");

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append("/>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderIgnoredField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.IgnoredField)
     */
    public void renderIgnoredField(Appendable writer, Map<String, Object> context, IgnoredField ignoredField) throws IOException {
        // do nothing, it's an ignored field; could add a comment or something if we wanted to
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFieldTitle(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFieldTitle(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        String tempTitleText = modelFormField.getTitle(context);
        String titleText = UtilHttp.encodeAmpersands(tempTitleText);

        if (UtilValidate.isNotEmpty(titleText)) {
            if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
                writer.append("<span class=\"");
                writer.append(modelFormField.getTitleStyle());
                writer.append("\">");
            }
            if (" ".equals(titleText)) {
                // If the title content is just a blank then render it colling renderFormatEmptySpace:
                // the method will set its content to work fine in most browser
                this.renderFormatEmptySpace(writer, context, modelFormField.getModelForm());
            } else {
                renderHyperlinkTitle(writer, context, modelFormField, titleText);
            }

            if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
                writer.append("</span>");
            }

            //appendWhitespace(writer);
        }
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFieldTitle(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderSingleFormFieldTitle(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        boolean requiredField = modelFormField.getRequiredField();
        if (requiredField) {

            String requiredStyle = modelFormField.getRequiredFieldStyle();
            if (UtilValidate.isEmpty(requiredStyle)) {
                requiredStyle = modelFormField.getTitleStyle();
            }

            if (UtilValidate.isNotEmpty(requiredStyle)) {
                writer.append("<span class=\"");
                writer.append(requiredStyle);
                writer.append("\">");
            }
            renderHyperlinkTitle(writer, context, modelFormField, modelFormField.getTitle(context));
            if (UtilValidate.isNotEmpty(requiredStyle)) {
                writer.append("</span>");
            }

            //appendWhitespace(writer);
        } else {
            renderFieldTitle(writer, context, modelFormField);
        }
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        renderBeginningBoundaryComment(writer, "Form Widget - Form Element", modelForm);
        writer.append("<form method=\"post\" ");
        String targetType = modelForm.getTargetType();
        String targ = modelForm.getTarget(context, targetType);
        // The 'action' attribute is mandatory in a form definition,
        // even if it is empty.
        writer.append(" action=\"");
        if (targ != null && targ.length() > 0) {
            //this.appendOfbizUrl(writer, "/" + targ);
            WidgetWorker.buildHyperlinkUrl(writer, targ, targetType, null, null, false, false, true, request, response, context);
        }
        writer.append("\" ");

        String formType = modelForm.getType();
        if (formType.equals("upload") ) {
            writer.append(" enctype=\"multipart/form-data\"");
        }

        String targetWindow = modelForm.getTargetWindow(context);
        if (UtilValidate.isNotEmpty(targetWindow)) {
            writer.append(" target=\"");
            writer.append(targetWindow);
            writer.append("\"");
        }

        String containerId =  modelForm.getContainerId();
        if (UtilValidate.isNotEmpty(containerId)) {
            writer.append(" id=\"");
            writer.append(containerId);
            writer.append("\"");
        }

        writer.append(" class=\"");
        String containerStyle =  modelForm.getContainerStyle();
        if (UtilValidate.isNotEmpty(containerStyle)) {
            writer.append(containerStyle);
        } else {
            writer.append("basic-form");
        }
        writer.append("\"");

        writer.append(" onSubmit=\"javascript:submitFormDisableSubmits(this)\"");

        if (!modelForm.getClientAutocompleteFields()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append(" name=\"");
        writer.append(modelForm.getCurrentFormName(context));
        writer.append("\">");

        boolean useRowSubmit = modelForm.getUseRowSubmit();
        if (useRowSubmit) {
            writer.append("<input type=\"hidden\" name=\"_useRowSubmit\" value=\"Y\"/>");
        }

        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("</form>");
        String focusFieldName = modelForm.getfocusFieldName();
        if (UtilValidate.isNotEmpty(focusFieldName)) {
            appendWhitespace(writer);
            writer.append("<script language=\"JavaScript\" type=\"text/javascript\">");
            appendWhitespace(writer);
            writer.append("document." + modelForm.getCurrentFormName(context) + ".");
            writer.append(focusFieldName + ".focus();");
            appendWhitespace(writer);
            writer.append("</script>");
        }
        appendWhitespace(writer);
        renderEndingBoundaryComment(writer, "Form Widget - Form Element", modelForm);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderMultiFormClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        Iterator<ModelFormField> submitFields = modelForm.getMultiSubmitFields().iterator();
        while (submitFields.hasNext()) {
            ModelFormField submitField = (ModelFormField) submitFields.next();
            if (submitField != null) {

                // Threw this in that as a hack to keep the submit button from expanding the first field
                // Needs a more rugged solution
                // WARNING: this method (renderMultiFormClose) must be called after the
                // table that contains the list has been closed (to avoid validation errors) so
                // we cannot call here the methods renderFormatItemRowCell*: for this reason
                // they are now commented.

                // this.renderFormatItemRowCellOpen(writer, context, modelForm, submitField);
                // this.renderFormatItemRowCellClose(writer, context, modelForm, submitField);

                // this.renderFormatItemRowCellOpen(writer, context, modelForm, submitField);

                submitField.renderFieldString(writer, context, this);

                // this.renderFormatItemRowCellClose(writer, context, modelForm, submitField);

            }
        }
        writer.append("</form>");
        appendWhitespace(writer);

        // see if there is anything that needs to be added outside of the multi-form
        Map<String, Object> wholeFormContext = UtilGenerics.checkMap(context.get("wholeFormContext"));
        Appendable postMultiFormWriter = wholeFormContext != null ? (Appendable) wholeFormContext.get("postMultiFormWriter") : null;
        if (postMultiFormWriter != null) {
            writer.append(postMultiFormWriter.toString());
            appendWhitespace(writer);
        }

        renderEndingBoundaryComment(writer, "Form Widget - Form Element (Multi)", modelForm);
    }

    public void renderFormatListWrapperOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {

        Map<String, Object> inputFields = UtilGenerics.checkMap(context.get("requestParameters"));
        Map<String, Object> queryStringMap = UtilGenerics.toMap(context.get("queryStringMap"));
        if (UtilValidate.isNotEmpty(queryStringMap)) {
            inputFields.putAll(queryStringMap);
        }
        if (modelForm.getType().equals("multi")) {
            inputFields = UtilHttp.removeMultiFormParameters(inputFields);
        }
        String queryString = UtilHttp.urlEncodeArgs(inputFields);
        context.put("_QBESTRING_", queryString);

        renderBeginningBoundaryComment(writer, "Form Widget", modelForm);

        if (this.renderPagination) {
            this.renderNextPrev(writer, context, modelForm);
        }
        writer.append(" <table cellspacing=\"0\" class=\"");
        if (UtilValidate.isNotEmpty(modelForm.getDefaultTableStyle())) {
            writer.append(modelForm.getDefaultTableStyle());
        } else {
            writer.append("basic-table form-widget-table dark-grid");
        }
        writer.append("\">");
        appendWhitespace(writer);
    }

    public void renderFormatListWrapperClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append(" </table>");

        appendWhitespace(writer);
        if (this.renderPagination) {
            this.renderNextPrev(writer, context, modelForm);
        }
        renderEndingBoundaryComment(writer, "Form Widget - Formal List Wrapper", modelForm);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatHeaderRowOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("  <tr");
        String headerStyle = modelForm.getHeaderRowStyle();
        writer.append(" class=\"");
        if (UtilValidate.isNotEmpty(headerStyle)) {
            writer.append(headerStyle);
        } else {
            writer.append("header-row");
        }
        writer.append("\">");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatHeaderRowClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("  </tr>");

        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowCellOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm, org.ofbiz.widget.form.ModelFormField, int positionSpan)
     */
    public void renderFormatHeaderRowCellOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm, ModelFormField modelFormField, int positionSpan) throws IOException {
        writer.append("   <td");
        String areaStyle = modelFormField.getTitleAreaStyle();
        if (positionSpan > 1) {
            writer.append(" colspan=\"");
            writer.append(Integer.toString(positionSpan));
            writer.append("\"");
        }
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        }
        writer.append(">");
        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatHeaderRowCellClose(Appendable writer, Map<String, Object> context, ModelForm modelForm, ModelFormField modelFormField) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    public void renderFormatHeaderRowFormCellOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("   <td");
        String areaStyle = modelForm.getFormTitleAreaStyle();
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        }
        writer.append(">");
        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowFormCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatHeaderRowFormCellClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatHeaderRowFormCellTitleSeparator(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm, boolean)
     */
    public void renderFormatHeaderRowFormCellTitleSeparator(Appendable writer, Map<String, Object> context, ModelForm modelForm, ModelFormField modelFormField, boolean isLast) throws IOException {

        String titleStyle = modelFormField.getTitleStyle();
        if (UtilValidate.isNotEmpty(titleStyle)) {
            writer.append("<span class=\"");
            writer.append(titleStyle);
            writer.append("\">");
        }
        if (isLast) {
            writer.append(" - ");
        } else {
            writer.append(" - ");
        }
        if (UtilValidate.isNotEmpty(titleStyle)) {
            writer.append("</span>");
        }
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatItemRowOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        Integer itemIndex = (Integer)context.get("itemIndex");

        writer.append("  <tr");
        if (itemIndex!=null) {

            String altRowStyles = modelForm.getStyleAltRowStyle(context);
            if (itemIndex.intValue() % 2 == 0) {
                String evenRowStyle = modelForm.getEvenRowStyle();
                if (UtilValidate.isNotEmpty(evenRowStyle)) {
                    writer.append(" class=\"");
                    writer.append(evenRowStyle);
                    if (UtilValidate.isNotEmpty(altRowStyles)) {
                        writer.append(altRowStyles);
                    }
                    writer.append("\"");
                } else {
                    if (UtilValidate.isNotEmpty(altRowStyles)) {
                        writer.append(" class=\"");
                        writer.append(altRowStyles);
                        writer.append("\"");
                    }
                }
            } else {
                String oddRowStyle = modelForm.getOddRowStyle();
                if (UtilValidate.isNotEmpty(oddRowStyle)) {
                    writer.append(" class=\"");
                    writer.append(oddRowStyle);
                    if (UtilValidate.isNotEmpty(altRowStyles)) {
                        writer.append(altRowStyles);
                    }
                    writer.append("\"");
                } else {
                    if (UtilValidate.isNotEmpty(altRowStyles)) {
                        writer.append(" class=\"");
                        writer.append(altRowStyles);
                        writer.append("\"");
                    }
                }
            }
        }
        writer.append(">");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatItemRowClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("  </tr>");

        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowCellOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatItemRowCellOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm, ModelFormField modelFormField, int positionSpan) throws IOException {
        writer.append("   <td");
        String areaStyle = modelFormField.getWidgetAreaStyle();
        if (positionSpan > 1) {
            writer.append(" colspan=\"");
            writer.append(Integer.toString(positionSpan));
            writer.append("\"");
        }
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        }
        writer.append(">");
        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatItemRowCellClose(Appendable writer, Map<String, Object> context, ModelForm modelForm, ModelFormField modelFormField) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowFormCellOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatItemRowFormCellOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("   <td");
        String areaStyle = modelForm.getFormWidgetAreaStyle();
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        }
        writer.append(">");
        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatItemRowFormCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatItemRowFormCellClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    public void renderFormatSingleWrapperOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append(" <table cellspacing=\"0\"");
        if (UtilValidate.isNotEmpty(modelForm.getDefaultTableStyle())) {
            writer.append(" class=\"" + modelForm.getDefaultTableStyle() + "\"");
        }
        writer.append(">");
        appendWhitespace(writer);
    }

    public void renderFormatSingleWrapperClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append(" </table>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatFieldRowOpen(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("  <tr>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelForm)
     */
    public void renderFormatFieldRowClose(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("  </tr>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowTitleCellOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatFieldRowTitleCellOpen(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        writer.append("   <td");
        String areaStyle = modelFormField.getTitleAreaStyle();
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        } else {
            writer.append(" class=\"label\"");
        }
        writer.append(">");
        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowTitleCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatFieldRowTitleCellClose(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowSpacerCell(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField)
     */
    public void renderFormatFieldRowSpacerCell(Appendable writer, Map<String, Object> context, ModelFormField modelFormField) throws IOException {
        // Embedded styling - bad idea
        //writer.append("<td>&nbsp;</td>");

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowWidgetCellOpen(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField, int)
     */
    public void renderFormatFieldRowWidgetCellOpen(Appendable writer, Map<String, Object> context, ModelFormField modelFormField, int positions, int positionSpan, Integer nextPositionInRow) throws IOException {
//        writer.append("<td width=\"");
//        if (nextPositionInRow != null || modelFormField.getPosition() > 1) {
//            writer.append("30");
//        } else {
//            writer.append("80");
//        }
//        writer.append("%\"");
        writer.append("   <td");
        if (positionSpan > 0) {
            writer.append(" colspan=\"");
            // do a span of 1 for this column, plus 3 columns for each spanned
            //position or each blank position that this will be filling in
            writer.append(Integer.toString(1 + (positionSpan * 3)));
            writer.append("\"");
        }
        String areaStyle = modelFormField.getWidgetAreaStyle();
        if (UtilValidate.isNotEmpty(areaStyle)) {
            writer.append(" class=\"");
            writer.append(areaStyle);
            writer.append("\"");
        }
        writer.append(">");

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFormatFieldRowWidgetCellClose(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField, int)
     */
    public void renderFormatFieldRowWidgetCellClose(Appendable writer, Map<String, Object> context, ModelFormField modelFormField, int positions, int positionSpan, Integer nextPositionInRow) throws IOException {
        writer.append("</td>");
        appendWhitespace(writer);
    }

    public void renderFormatEmptySpace(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        writer.append("&nbsp;");
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderTextFindField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.TextFindField)
     */
    public void renderTextFindField(Appendable writer, Map<String, Object> context, TextFindField textFindField) throws IOException {

        ModelFormField modelFormField = textFindField.getModelFormField();

        String defaultOption = textFindField.getDefaultOption();
        Locale locale = (Locale)context.get("locale");
        if (!textFindField.getHideOptions()) {
            String opEquals = UtilProperties.getMessage("conditional", "equals", locale);
            String opBeginsWith = UtilProperties.getMessage("conditional", "begins_with", locale);
            String opContains = UtilProperties.getMessage("conditional", "contains", locale);
            String opIsEmpty = UtilProperties.getMessage("conditional", "is_empty", locale);
            String opNotEqual = UtilProperties.getMessage("conditional", "not_equal", locale);
            writer.append(" <select name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append("_op\" class=\"selectBox\">");
            writer.append("<option value=\"equals\"" + ("equals".equals(defaultOption)? " selected": "") + ">" + opEquals + "</option>");
            writer.append("<option value=\"like\"" + ("like".equals(defaultOption)? " selected": "") + ">" + opBeginsWith + "</option>");
            writer.append("<option value=\"contains\"" + ("contains".equals(defaultOption)? " selected": "") + ">" + opContains + "</option>");
            writer.append("<option value=\"empty\"" + ("empty".equals(defaultOption)? " selected": "") + ">" + opIsEmpty + "</option>");
            writer.append("<option value=\"notEqual\"" + ("notEqual".equals(defaultOption)? " selected": "") + ">" + opNotEqual + "</option>");
            writer.append("</select>");
        } else {
            writer.append(" <input type=\"hidden\" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append("_op\" value=\"" + defaultOption + "\"/>");
        }

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String value = modelFormField.getEntry(context, textFindField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(textFindField.getSize()));
        writer.append('"');

        Integer maxlength = textFindField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        if (!textFindField.getClientAutocompleteField()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append(" <span class=\"");
            writer.append(modelFormField.getTitleStyle());
            writer.append("\">");
        }

        String ignoreCase = UtilProperties.getMessage("conditional", "ignore_case", locale);
        boolean ignCase = textFindField.getIgnoreCase();

        if (!textFindField.getHideIgnoreCase()) {
            writer.append(" <input type=\"checkbox\" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append("_ic\" value=\"Y\"" + (ignCase ? " checked=\"checked\"" : "") + "/>");
            writer.append(ignoreCase);
        } else {
            writer.append( "<input type=\"hidden\" name=\"");
            writer.append(modelFormField.getParameterName(context));
            writer.append("_ic\" value=\"" + (ignCase ? "Y" : "") + "\"/>");
        }

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append("</span>");
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderRangeFindField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.RangeFindField)
     */
    public void renderRangeFindField(Appendable writer, Map<String, Object> context, RangeFindField rangeFindField) throws IOException {

        ModelFormField modelFormField = rangeFindField.getModelFormField();
        Locale locale = (Locale)context.get("locale");
        String opEquals = UtilProperties.getMessage("conditional", "equals", locale);
        String opGreaterThan = UtilProperties.getMessage("conditional", "greater_than", locale);
        String opGreaterThanEquals = UtilProperties.getMessage("conditional", "greater_than_equals", locale);
        String opLessThan = UtilProperties.getMessage("conditional", "less_than", locale);
        String opLessThanEquals = UtilProperties.getMessage("conditional", "less_than_equals", locale);
        //String opIsEmpty = UtilProperties.getMessage("conditional", "is_empty", locale);

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld0_value\"");

        String value = modelFormField.getEntry(context, rangeFindField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(rangeFindField.getSize()));
        writer.append('"');

        Integer maxlength = rangeFindField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        if (!rangeFindField.getClientAutocompleteField()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append(" <span class=\"");
            writer.append(modelFormField.getTitleStyle());
            writer.append("\">");
        }

        String defaultOptionFrom = rangeFindField.getDefaultOptionFrom();
        writer.append(" <select name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld0_op\" class=\"selectBox\">");
        writer.append("<option value=\"equals\"" + ("equals".equals(defaultOptionFrom)? " selected": "") + ">" + opEquals + "</option>");
        writer.append("<option value=\"greaterThan\"" + ("greaterThan".equals(defaultOptionFrom)? " selected": "") + ">" + opGreaterThan + "</option>");
        writer.append("<option value=\"greaterThanEqualTo\"" + ("greaterThanEqualTo".equals(defaultOptionFrom)? " selected": "") + ">" + opGreaterThanEquals + "</option>");
        writer.append("</select>");

        writer.append("</span>");

        writer.append(" <br/> ");

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld1_value\"");

        value = modelFormField.getEntry(context);
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(rangeFindField.getSize()));
        writer.append('"');

        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        if (!rangeFindField.getClientAutocompleteField()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        String defaultOptionThru = rangeFindField.getDefaultOptionThru();
        writer.append(" <select name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld1_op\" class=\"selectBox\">");
        writer.append("<option value=\"lessThan\"" + ("lessThan".equals(defaultOptionThru)? " selected": "") + ">" + opLessThan + "</option>");
        writer.append("<option value=\"lessThanEqualTo\"" + ("lessThanEqualTo".equals(defaultOptionThru)? " selected": "") + ">" + opLessThanEquals + "</option>");
        writer.append("</select>");

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append("</span>");
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderDateFindField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.DateFindField)
     */
    public void renderDateFindField(Appendable writer, Map<String, Object> context, DateFindField dateFindField) throws IOException {
        ModelFormField modelFormField = dateFindField.getModelFormField();

        Locale locale = (Locale)context.get("locale");
        String opEquals = UtilProperties.getMessage("conditional", "equals", locale);
        String opGreaterThan = UtilProperties.getMessage("conditional", "greater_than", locale);
        String opSameDay = UtilProperties.getMessage("conditional", "same_day", locale);
        String opGreaterThanFromDayStart = UtilProperties.getMessage("conditional",
                                                "greater_than_from_day_start", locale);
        String opLessThan = UtilProperties.getMessage("conditional", "less_than", locale);
        String opUpToDay = UtilProperties.getMessage("conditional", "up_to_day", locale);
        String opUpThruDay = UtilProperties.getMessage("conditional", "up_thru_day", locale);
        String opIsEmpty = UtilProperties.getMessage("conditional", "is_empty", locale);

        Map<String, String> uiLabelMap = UtilGenerics.checkMap(context.get("uiLabelMap"));
        if (uiLabelMap == null) {
            Debug.logWarning("Could not find uiLabelMap in context", module);
        }
        String localizedInputTitle = "", localizedIconTitle = "";

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld0_value\"");

        // the default values for a timestamp
        int size = 25;
        int maxlength = 30;

        if ("date".equals(dateFindField.getType())) {
            size = maxlength = 10;
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatDate");
            }
        } else if ("time".equals(dateFindField.getType())) {
            size = maxlength = 8;
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatTime");
            }
        } else {
            if (uiLabelMap != null) {
                localizedInputTitle = (String) uiLabelMap.get("CommonFormatDateTime");
            }
        }
        writer.append(" title=\"");
        writer.append(localizedInputTitle);
        writer.append('"');

        String value = modelFormField.getEntry(context, dateFindField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            if (value.length() > maxlength) {
                value = value.substring(0, maxlength);
            }
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(size));
        writer.append('"');

        writer.append(" maxlength=\"");
        writer.append(Integer.toString(maxlength));
        writer.append('"');

        writer.append("/>");

        // search for a localized label for the icon
        if (uiLabelMap != null) {
            localizedIconTitle = (String) uiLabelMap.get("CommonViewCalendar");
        }

        // add calendar pop-up button and seed data IF this is not a "time" type date-find
        if (!"time".equals(dateFindField.getType())) {
            if ("date".equals(dateFindField.getType())) {
                writer.append("<a href=\"javascript:call_cal_notime(document.");
            } else {
                writer.append("<a href=\"javascript:call_cal(document.");
            }
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append('.');
            writer.append(modelFormField.getParameterName(context));
            writer.append("_fld0_value,'");
            writer.append(UtilHttp.encodeBlanks(modelFormField.getEntry(context, dateFindField.getDefaultDateTimeString(context))));
            writer.append("');\">");

            writer.append("<img src=\"");
            this.appendContentUrl(writer, "/images/cal.gif");
            writer.append("\" width=\"16\" height=\"16\" border=\"0\" alt=\"");
            writer.append(localizedIconTitle);
            writer.append("\" title=\"");
            writer.append(localizedIconTitle);
            writer.append("\"/></a>");
        }

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append(" <span class=\"");
            writer.append(modelFormField.getTitleStyle());
            writer.append("\">");
        }

        String defaultOptionFrom = dateFindField.getDefaultOptionFrom();
        writer.append(" <select name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld0_op\" class=\"selectBox\">");
        writer.append("<option value=\"equals\"" + ("equals".equals(defaultOptionFrom)? " selected": "") + ">" + opEquals + "</option>");
        writer.append("<option value=\"sameDay\"" + ("sameDay".equals(defaultOptionFrom)? " selected": "") + ">" + opSameDay + "</option>");
        writer.append("<option value=\"greaterThanFromDayStart\"" + ("greaterThanFromDayStart".equals(defaultOptionFrom)? " selected": "") + ">" + opGreaterThanFromDayStart + "</option>");
        writer.append("<option value=\"greaterThan\"" + ("greaterThan".equals(defaultOptionFrom)? " selected": "") + ">" + opGreaterThan + "</option>");
        writer.append("</select>");

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append(" </span>");
        }

        writer.append(" <br/> ");

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld1_value\"");

        writer.append(" title=\"");
        writer.append(localizedInputTitle);
        writer.append('"');

        value = modelFormField.getEntry(context);
        if (UtilValidate.isNotEmpty(value)) {
            if (value.length() > maxlength) {
                value = value.substring(0, maxlength);
            }
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(size));
        writer.append('"');

        writer.append(" maxlength=\"");
        writer.append(Integer.toString(maxlength));
        writer.append('"');

        writer.append("/>");

        // add calendar pop-up button and seed data IF this is not a "time" type date-find
        if (!"time".equals(dateFindField.getType())) {
            if ("date".equals(dateFindField.getType())) {
                writer.append("<a href=\"javascript:call_cal_notime(document.");
            } else {
                writer.append("<a href=\"javascript:call_cal(document.");
            }
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append('.');
            writer.append(modelFormField.getParameterName(context));
            writer.append("_fld1_value,'");
            writer.append(UtilHttp.encodeBlanks(modelFormField.getEntry(context, dateFindField.getDefaultDateTimeString(context))));
            writer.append("');\">");

            writer.append("<img src=\"");
            this.appendContentUrl(writer, "/images/cal.gif");
            writer.append("\" width=\"16\" height=\"16\" border=\"0\" alt=\"");
            writer.append(localizedIconTitle);
            writer.append("\" title=\"");
            writer.append(localizedIconTitle);
            writer.append("\"/></a>");
        }

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append(" <span class=\"");
            writer.append(modelFormField.getTitleStyle());
            writer.append("\">");
        }

        String defaultOptionThru = dateFindField.getDefaultOptionThru();
        writer.append(" <select name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append("_fld1_op\" class=\"selectBox\">");
        writer.append("<option value=\"lessThan\"" + ("lessThan".equals(defaultOptionThru)? " selected": "") + ">" + opLessThan + "</option>");
        writer.append("<option value=\"upToDay\"" + ("upToDay".equals(defaultOptionThru)? " selected": "") + ">" + opUpToDay + "</option>");
        writer.append("<option value=\"upThruDay\"" + ("upThruDay".equals(defaultOptionThru)? " selected": "") + ">" + opUpThruDay + "</option>");
        writer.append("<option value=\"empty\"" + ("empty".equals(defaultOptionThru)? " selected": "") + ">" + opIsEmpty + "</option>");
        writer.append("</select>");

        if (UtilValidate.isNotEmpty(modelFormField.getTitleStyle())) {
            writer.append("</span>");
        }

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderLookupField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.LookupField)
     */
    public void renderLookupField(Appendable writer, Map<String, Object> context, LookupField lookupField) throws IOException {
        ModelFormField modelFormField = lookupField.getModelFormField();

        writer.append("<input type=\"text\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String value = modelFormField.getEntry(context, lookupField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(lookupField.getSize()));
        writer.append('"');

        Integer maxlength = lookupField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName)) {
            writer.append(" id=\"");
            writer.append(idName);
            writer.append('"');
        }

        List<ModelForm.UpdateArea> updateAreas = modelFormField.getOnChangeUpdateAreas();
        boolean ajaxEnabled = updateAreas != null && this.javaScriptEnabled;
        if (!lookupField.getClientAutocompleteField() || ajaxEnabled) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        // add lookup pop-up button
        String descriptionFieldName = lookupField.getDescriptionFieldName();
        if (UtilValidate.isNotEmpty(descriptionFieldName)) {
            writer.append("<a href=\"javascript:call_fieldlookup3(document.");
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append('.');
            writer.append(modelFormField.getParameterName(context));
            writer.append(",'");
            writer.append(descriptionFieldName);
            writer.append(",'");
        } else {
            writer.append("<a href=\"javascript:call_fieldlookup2(document.");
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append('.');
            writer.append(modelFormField.getParameterName(context));
            writer.append(",'");
        }
        writer.append(appendExternalLoginKey(lookupField.getFormName(context)));
        writer.append("'");
        List<String> targetParameterList = lookupField.getTargetParameterList();
        for (String targetParameter: targetParameterList) {
            // named like: document.${formName}.${targetParameter}.value
            writer.append(", document.");
            writer.append(modelFormField.getModelForm().getCurrentFormName(context));
            writer.append(".");
            writer.append(targetParameter);
            writer.append(".value");
        }
        writer.append(");\">");
        writer.append("<img src=\"");
        this.appendContentUrl(writer, "/images/fieldlookup.gif");
        writer.append("\" width=\"15\" height=\"14\" border=\"0\" alt=\"Lookup\"/></a>");

        this.addAsterisks(writer, context, modelFormField);

        this.makeHyperlinkString(writer, lookupField.getSubHyperlink(), context);
        this.appendTooltip(writer, context, modelFormField);

        if (ajaxEnabled) {
            appendWhitespace(writer);
            writer.append("<script language=\"JavaScript\" type=\"text/javascript\">");
            appendWhitespace(writer);
            writer.append("ajaxAutoCompleter('" + createAjaxParamsFromUpdateAreas(updateAreas, null, context) + "');");
            appendWhitespace(writer);
            writer.append("</script>");
        }
        appendWhitespace(writer);

        //appendWhitespace(writer);
    }

    protected String appendExternalLoginKey(String target) {
        String result = target;
        String sessionId = ";jsessionid=" + request.getSession().getId();
        int questionIndex = target.indexOf("?");
        if (questionIndex == -1) {
            result += sessionId;
        } else {
            result.replace("?", sessionId + "?");
        }
        return result;
    }

    public void renderNextPrev(Appendable writer, Map<String, Object> context, ModelForm modelForm) throws IOException {
        boolean ajaxEnabled = false;
        List<ModelForm.UpdateArea> updateAreas = modelForm.getOnPaginateUpdateAreas();
        String targetService = modelForm.getPaginateTarget(context);
        if (this.javaScriptEnabled) {
            if (UtilValidate.isNotEmpty(updateAreas)) {
                ajaxEnabled = true;
            }
        }
        if (targetService == null) {
            targetService = "${targetService}";
        }
        if (UtilValidate.isEmpty(targetService) && updateAreas == null) {
            Debug.logWarning("Cannot paginate because TargetService is empty for the form: " + modelForm.getName(), module);
            return;
        }

        // get the parameterized pagination index and size fields
        int paginatorNumber = modelForm.getPaginatorNumber(context);
        String viewIndexParam = modelForm.getMultiPaginateIndexField(context);
        String viewSizeParam = modelForm.getMultiPaginateSizeField(context);

        int viewIndex = modelForm.getViewIndex(context);
        int viewSize = modelForm.getViewSize(context);
        int listSize = modelForm.getListSize(context);

        int lowIndex = modelForm.getLowIndex(context);
        int highIndex = modelForm.getHighIndex(context);
        int actualPageSize = modelForm.getActualPageSize(context);

        // if this is all there seems to be (if listSize < 0, then size is unknown)
        if (actualPageSize >= listSize && listSize >= 0) return;

        // needed for the "Page" and "rows" labels
        Map<String, String> uiLabelMap = UtilGenerics.checkMap(context.get("uiLabelMap"));
        String pageLabel = "";
        String commonDisplaying = "";
        if (uiLabelMap == null) {
            Debug.logWarning("Could not find uiLabelMap in context", module);
        } else {
            pageLabel = (String) uiLabelMap.get("CommonPage");
            Map<String, Integer> messageMap = UtilMisc.toMap("lowCount", Integer.valueOf(lowIndex + 1), "highCount", Integer.valueOf(lowIndex + actualPageSize), "total", Integer.valueOf(listSize));
            commonDisplaying = UtilProperties.getMessage("CommonUiLabels", "CommonDisplaying", messageMap, (Locale) context.get("locale"));
        }

        // for legacy support, the viewSizeParam is VIEW_SIZE and viewIndexParam is VIEW_INDEX when the fields are "viewSize" and "viewIndex"
        if (viewIndexParam.equals("viewIndex" + "_" + paginatorNumber)) viewIndexParam = "VIEW_INDEX" + "_" + paginatorNumber;
        if (viewSizeParam.equals("viewSize" + "_" + paginatorNumber)) viewSizeParam = "VIEW_SIZE" + "_" + paginatorNumber;

        String str = (String) context.get("_QBESTRING_");

        // strip legacy viewIndex/viewSize params from the query string
        String queryString = UtilHttp.stripViewParamsFromQueryString(str, "" + paginatorNumber);

        // strip parametrized index/size params from the query string
        HashSet<String> paramNames = new HashSet<String>();
        paramNames.add(viewIndexParam);
        paramNames.add(viewSizeParam);
        queryString = UtilHttp.stripNamedParamsFromQueryString(queryString, paramNames);

        String anchor = "";
        String paginateAnchor = modelForm.getPaginateTargetAnchor();
        if (paginateAnchor != null) anchor = "#" + paginateAnchor;

        // Create separate url path String and request parameters String,
        // add viewIndex/viewSize parameters to request parameter String
        String urlPath = UtilHttp.removeQueryStringFromTarget(targetService);
        String prepLinkText = UtilHttp.getQueryStringFromTarget(targetService);
        if (prepLinkText == null) {
            prepLinkText = "";
        }
        if (prepLinkText.indexOf("?") < 0) {
            prepLinkText += "?";
        } else if (!prepLinkText.endsWith("?")) {
            prepLinkText += "&amp;";
        }
        if (!UtilValidate.isEmpty(queryString) && !queryString.equals("null")) {
            prepLinkText += queryString + "&amp;";
        }
        prepLinkText += viewSizeParam + "=" + viewSize + "&amp;" + viewIndexParam + "=";
        if (ajaxEnabled) {
            // Prepare params for prototype.js
            prepLinkText = prepLinkText.replace("?", "");
            prepLinkText = prepLinkText.replace("&amp;", "&");
        }

        writer.append("<div class=\"" + modelForm.getPaginateStyle() + "\">");
        appendWhitespace(writer);
        writer.append(" <ul>");
        appendWhitespace(writer);

        String linkText;

        // First button
        writer.append("  <li class=\"" + modelForm.getPaginateFirstStyle());
        if (viewIndex > 0) {
            writer.append("\"><a href=\"");
            if (ajaxEnabled) {
                writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText + 0 + anchor, context) + "')");
            } else {
                linkText = prepLinkText + 0 + anchor;
                writer.append(rh.makeLink(this.request, this.response, urlPath + linkText));
            }
            writer.append("\">" + modelForm.getPaginateFirstLabel(context) + "</a>");
        } else {
            // disabled button
            writer.append("-disabled\">" + modelForm.getPaginateFirstLabel(context));
        }
        writer.append("</li>");
        appendWhitespace(writer);

        // Previous button
        writer.append("  <li class=\"" + modelForm.getPaginatePreviousStyle());
        if (viewIndex > 0) {
            writer.append("\"><a href=\"");
            if (ajaxEnabled) {
                writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText + (viewIndex - 1) + anchor, context) + "')");
            } else {
                linkText = prepLinkText + (viewIndex - 1) + anchor;
                writer.append(rh.makeLink(this.request, this.response, urlPath + linkText));
            }
            writer.append("\">" + modelForm.getPaginatePreviousLabel(context) + "</a>");
        } else {
            // disabled button
            writer.append("-disabled\">" + modelForm.getPaginatePreviousLabel(context));
        }
        writer.append("</li>");
        appendWhitespace(writer);

        // Page select dropdown
        if (listSize > 0 && this.javaScriptEnabled) {
            writer.append("  <li>" + pageLabel + " <select name=\"page\" size=\"1\" onchange=\"");
            if (ajaxEnabled) {
                writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText + "' + this.value", context) + ")");
            } else {
                linkText = prepLinkText;
                if (linkText.startsWith("/")) {
                    linkText = linkText.substring(1);
                }
                writer.append("location.href = '" + rh.makeLink(this.request, this.response, urlPath + linkText) + "' + this.value;");
            }
            writer.append("\">");
            // actual value
            int page = 0;
            for (int i = 0; i < listSize;) {
                if (page == viewIndex) {
                    writer.append("<option selected value=\"");
                } else {
                    writer.append("<option value=\"");
                }
                writer.append(Integer.toString(page));
                writer.append("\">");
                writer.append(Integer.toString(1 + page));
                writer.append("</option>");
                // increment page and calculate next index
                page++;
                i = page * viewSize;
            }
            writer.append("</select></li>");
        }

        //  show row count
        writer.append("<li>");
        writer.append(commonDisplaying);
        writer.append("</li>");
        appendWhitespace(writer);

        // Next button
        writer.append("  <li class=\"" + modelForm.getPaginateNextStyle());
        if (highIndex < listSize) {
            writer.append("\"><a href=\"");
            if (ajaxEnabled) {
                writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText + (viewIndex + 1) + anchor, context) + "')");
            } else {
                linkText = prepLinkText + (viewIndex + 1) + anchor;
                writer.append(rh.makeLink(this.request, this.response, urlPath + linkText));
            }
            writer.append("\">" + modelForm.getPaginateNextLabel(context) + "</a>");
        } else {
            // disabled button
            writer.append("-disabled\">" + modelForm.getPaginateNextLabel(context));
        }
        writer.append("</li>");
        appendWhitespace(writer);

        // Last button
        writer.append("  <li class=\"" + modelForm.getPaginateLastStyle());
        if (highIndex < listSize) {
            writer.append("\"><a href=\"");
            if (ajaxEnabled) {
                writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText + (listSize / viewSize) + anchor, context) + "')");
            } else {
                linkText = prepLinkText + (listSize / viewSize) + anchor;
                writer.append(rh.makeLink(this.request, this.response, urlPath + linkText));
            }
            writer.append("\">" + modelForm.getPaginateLastLabel(context) + "</a>");
        } else {
            // disabled button
            writer.append("-disabled\">" + modelForm.getPaginateLastLabel(context));
        }
        writer.append("</li>");
        appendWhitespace(writer);

        writer.append(" </ul>");
        appendWhitespace(writer);
        writer.append("</div>");
        appendWhitespace(writer);
    }

    public void renderSortField(Appendable writer, Map<String, Object> context, ModelFormField modelFormField, String titleText) throws IOException {
        boolean ajaxEnabled = false;
        ModelForm modelForm = modelFormField.getModelForm();
        List<ModelForm.UpdateArea> updateAreas = modelForm.getOnPaginateUpdateAreas();
        String targetService = modelForm.getPaginateTarget(context);
        if (this.javaScriptEnabled) {
            if (UtilValidate.isNotEmpty(updateAreas)) {
                ajaxEnabled = true;
            }
        }
        if (targetService == null) {
            targetService = "${targetService}";
        }
        if (UtilValidate.isEmpty(targetService) && updateAreas == null) {
            Debug.logWarning("Cannot sort because TargetService is empty for the form: " + modelForm.getName(), module);
            return;
        }

        String str = (String) context.get("_QBESTRING_");
        String oldSortField = modelForm.getSortField(context);
        String sortFieldStyle = modelFormField.getSortFieldStyle();

        // if the entry-name is defined use this instead of field name
        String columnField = modelFormField.getEntryName();
        if (UtilValidate.isEmpty(columnField)) {
            columnField = modelFormField.getFieldName();
        }

        // switch beetween asc/desc order
        String newSortField = columnField;
        if (UtilValidate.isNotEmpty(oldSortField)) {
            if (oldSortField.equals(columnField)) {
                newSortField = "-" + columnField;
                sortFieldStyle = modelFormField.getSortFieldStyleDesc();
            } else if (oldSortField.equals("-" + columnField)) {
                newSortField = columnField;
                sortFieldStyle = modelFormField.getSortFieldStyleAsc();
            }
        }

        //  strip sortField param from the query string
        HashSet<String> paramName = new HashSet<String>();
        paramName.add("sortField");
        String queryString = UtilHttp.stripNamedParamsFromQueryString(str, paramName);
        String urlPath = UtilHttp.removeQueryStringFromTarget(targetService);
        String prepLinkText = UtilHttp.getQueryStringFromTarget(targetService);
        if (prepLinkText == null) {
            prepLinkText = "";
        }
        if (prepLinkText.indexOf("?") < 0) {
            prepLinkText += "?";
        } else if (!prepLinkText.endsWith("?")) {
            prepLinkText += "&amp;";
        }
        if (!UtilValidate.isEmpty(queryString) && !queryString.equals("null")) {
            prepLinkText += queryString + "&amp;";
        }
        prepLinkText += "sortField" + "=" + newSortField;
        if (ajaxEnabled) {
            prepLinkText = prepLinkText.replace("?", "");
            prepLinkText = prepLinkText.replace("&amp;", "&");
        }

        writer.append("<a");
        if (UtilValidate.isNotEmpty(sortFieldStyle)) {
            writer.append(" class=\"");
            writer.append(sortFieldStyle);
            writer.append("\"");
        }

        writer.append(" href=\"");
        if (ajaxEnabled) {
            writer.append("javascript:ajaxUpdateAreas('" + createAjaxParamsFromUpdateAreas(updateAreas, prepLinkText, context) + "')");
        } else {
            writer.append(rh.makeLink(this.request, this.response, urlPath + prepLinkText));
        }
        writer.append("\">" + titleText + "</a>");
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderFileField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.FileField)
     */
    public void renderFileField(Appendable writer, Map<String, Object> context, FileField textField) throws IOException {
        ModelFormField modelFormField = textField.getModelFormField();

        writer.append("<input type=\"file\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String value = modelFormField.getEntry(context, textField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(textField.getSize()));
        writer.append('"');

        Integer maxlength = textField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        if (!textField.getClientAutocompleteField()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        this.makeHyperlinkString(writer, textField.getSubHyperlink(), context);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderPasswordField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.PasswordField)
     */
    public void renderPasswordField(Appendable writer, Map<String, Object> context, PasswordField passwordField) throws IOException {
        ModelFormField modelFormField = passwordField.getModelFormField();

        writer.append("<input type=\"password\"");

        appendClassNames(writer, context, modelFormField);

        writer.append(" name=\"");
        writer.append(modelFormField.getParameterName(context));
        writer.append('"');

        String value = modelFormField.getEntry(context, passwordField.getDefaultValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" value=\"");
            writer.append(value);
            writer.append('"');
        }

        writer.append(" size=\"");
        writer.append(Integer.toString(passwordField.getSize()));
        writer.append('"');

        Integer maxlength = passwordField.getMaxlength();
        if (maxlength != null) {
            writer.append(" maxlength=\"");
            writer.append(maxlength.toString());
            writer.append('"');
        }

        String idName = modelFormField.getIdName();
        if (UtilValidate.isNotEmpty(idName)) {
            writer.append(" id=\"");
            writer.append(idName);
            writer.append('"');
        }

        if (!passwordField.getClientAutocompleteField()) {
            writer.append(" autocomplete=\"off\"");
        }

        writer.append("/>");

        this.addAsterisks(writer, context, modelFormField);

        this.makeHyperlinkString(writer, passwordField.getSubHyperlink(), context);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.widget.form.FormStringRenderer#renderImageField(java.io.Writer, java.util.Map, org.ofbiz.widget.form.ModelFormField.ImageField)
     */
    public void renderImageField(Appendable writer, Map<String, Object> context, ImageField imageField) throws IOException {
        ModelFormField modelFormField = imageField.getModelFormField();

        writer.append("<img ");


        String value = modelFormField.getEntry(context, imageField.getValue(context));
        if (UtilValidate.isNotEmpty(value)) {
            writer.append(" src=\"");
            StringBuffer buffer = new StringBuffer();
            ContentUrlTag.appendContentPrefix(request, buffer);
            writer.append(buffer.toString());
            writer.append(value);
            writer.append('"');
        }

        writer.append(" border=\"");
        writer.append("" + imageField.getBorder());
        writer.append('"');

        Integer width = imageField.getWidth();
        if (width != null) {
            writer.append(" width=\"");
            writer.append(width.toString());
            writer.append('"');
        }

        Integer height = imageField.getHeight();
        if (height != null) {
            writer.append(" height=\"");
            writer.append(height.toString());
            writer.append('"');
        }

        String event = modelFormField.getEvent();
        String action = modelFormField.getAction(context);
        if (UtilValidate.isNotEmpty(event) && UtilValidate.isNotEmpty(action)) {
            writer.append(" ");
            writer.append(event);
            writer.append("=\"");
            writer.append(action);
            writer.append('"');
        }

        writer.append("/>");

        this.makeHyperlinkString(writer, imageField.getSubHyperlink(), context);

        this.appendTooltip(writer, context, modelFormField);

        //appendWhitespace(writer);
    }

    public void renderFieldGroupOpen(Appendable writer, Map<String, Object> context, ModelForm.FieldGroup fieldGroup) throws IOException {
        String style = fieldGroup.getStyle();
        String id = fieldGroup.getId();
        FlexibleStringExpander titleNotExpanded = FlexibleStringExpander.getInstance(fieldGroup.getTitle());
        String title = titleNotExpanded.expandString(context);
        Boolean collapsed = fieldGroup.initiallyCollapsed();
        String collapsibleAreaId = fieldGroup.getId() + "_body";

        if (UtilValidate.isNotEmpty(style) || UtilValidate.isNotEmpty(id) || UtilValidate.isNotEmpty(title)) {

            writer.append("<div class=\"fieldgroup");
            if (UtilValidate.isNotEmpty(style)) {
                writer.append(" ");
                writer.append(style);
            }
            writer.append("\"");
            if (UtilValidate.isNotEmpty(id)) {
                writer.append(" id=\"");
                writer.append(id);
                writer.append("\"");
            }
            writer.append(">");

            writer.append("<div class=\"fieldgroup-title-bar\"><table><tr><td class=\"collapse\">");

            if (fieldGroup.collapsible()) {
                String expandToolTip = null;
                String collapseToolTip = null;
                Map<String, Object> uiLabelMap = UtilGenerics.checkMap(context.get("uiLabelMap"));
                //Map<String, Object> paramMap = UtilGenerics.checkMap(context.get("requestParameters"));
                if (uiLabelMap != null) {
                    expandToolTip = (String) uiLabelMap.get("CommonExpand");
                    collapseToolTip = (String) uiLabelMap.get("CommonCollapse");
                }

                writer.append("<ul><li class=\"");
                if (collapsed) {
                    writer.append("collapsed\"><a ");
                    writer.append("onclick=\"javascript:toggleCollapsiblePanel(this, '" + collapsibleAreaId + "', '" + expandToolTip + "', '" + collapseToolTip + "');\"");
                } else {
                    writer.append("expanded\"><a ");
                    writer.append("onclick=\"javascript:toggleCollapsiblePanel(this, '" + collapsibleAreaId + "', '" + expandToolTip + "', '" + collapseToolTip + "');\"");
                }
                writer.append(">&nbsp&nbsp&nbsp</a></li></ul>");

                appendWhitespace(writer);
            }
            writer.append("</td><td>");

            if (UtilValidate.isNotEmpty(title)) {
                writer.append("<div class=\"title\">");
                writer.append(title);
                writer.append("</div>");
            }

            writer.append("</td></tr></table></div>");

            writer.append("<div id=\"" + collapsibleAreaId + "\" class=\"fieldgroup-body\"");
            if (fieldGroup.collapsible() && collapsed) {
                writer.append(" style=\"display: none;\"");
            }
            writer.append(">");
        }
    }

    public void renderFieldGroupClose(Appendable writer, Map<String, Object> context, ModelForm.FieldGroup fieldGroup) throws IOException {
        String style = fieldGroup.getStyle();
        String id = fieldGroup.getId();
        String title = fieldGroup.getTitle();
        if (UtilValidate.isNotEmpty(style) || UtilValidate.isNotEmpty(id) || UtilValidate.isNotEmpty(title)) {
            writer.append("</div>");
            writer.append("</div>");
        }
    }

    // TODO: Remove embedded styling
    public void renderBanner(Appendable writer, Map<String, Object> context, ModelForm.Banner banner) throws IOException {
        writer.append(" <table width=\"100%\">  <tr>");
        String style = banner.getStyle(context);
        String leftStyle = banner.getLeftTextStyle(context);
        if (UtilValidate.isEmpty(leftStyle)) leftStyle = style;
        String rightStyle = banner.getRightTextStyle(context);
        if (UtilValidate.isEmpty(rightStyle)) rightStyle = style;

        String leftText = banner.getLeftText(context);
        if (UtilValidate.isNotEmpty(leftText)) {
            writer.append("   <td align=\"left\">");
            if (UtilValidate.isNotEmpty(leftStyle)) {
               writer.append("<div");
               writer.append(" class=\"");
               writer.append(leftStyle);
               writer.append("\"");
               writer.append(">" );
            }
            writer.append(leftText);
            if (UtilValidate.isNotEmpty(leftStyle)) {
                writer.append("</div>");
            }
            writer.append("</td>");
        }

        String text = banner.getText(context);
        if (UtilValidate.isNotEmpty(text)) {
            writer.append("   <td align=\"center\">");
            if (UtilValidate.isNotEmpty(style)) {
               writer.append("<div");
               writer.append(" class=\"");
               writer.append(style);
               writer.append("\"");
               writer.append(">" );
            }
            writer.append(text);
            if (UtilValidate.isNotEmpty(style)) {
                writer.append("</div>");
            }
            writer.append("</td>");
        }

        String rightText = banner.getRightText(context);
        if (UtilValidate.isNotEmpty(rightText)) {
            writer.append("   <td align=\"right\">");
            if (UtilValidate.isNotEmpty(rightStyle)) {
               writer.append("<div");
               writer.append(" class=\"");
               writer.append(rightStyle);
               writer.append("\"");
               writer.append(">" );
            }
            writer.append(rightText);
            if (UtilValidate.isNotEmpty(rightStyle)) {
                writer.append("</div>");
            }
            writer.append("</td>");
        }
        writer.append("</tr> </table>");
    }

    /**
     * Renders a link for the column header fields when there is a header-link="" specified in the <field > tag, using
     * style from header-link-style="".  Also renders a selectAll checkbox in multi forms.
     * @param writer
     * @param context
     * @param modelFormField
     * @param titleText
     */
    public void renderHyperlinkTitle(Appendable writer, Map<String, Object> context, ModelFormField modelFormField, String titleText) throws IOException {
        if (UtilValidate.isNotEmpty(modelFormField.getHeaderLink())) {
            StringBuffer targetBuffer = new StringBuffer();
            FlexibleStringExpander target = FlexibleStringExpander.getInstance(modelFormField.getHeaderLink());
            String fullTarget = target.expandString(context);
            targetBuffer.append(fullTarget);
            String targetType = HyperlinkField.DEFAULT_TARGET_TYPE;
            if (UtilValidate.isNotEmpty(targetBuffer.toString()) && targetBuffer.toString().toLowerCase().startsWith("javascript:")) {
                targetType="plain";
            }
            WidgetWorker.makeHyperlinkString(writer, modelFormField.getHeaderLinkStyle(), targetType, targetBuffer.toString(), null, titleText, modelFormField, this.request, this.response, null, null);
        } else if (modelFormField.isSortField()) {
            renderSortField (writer, context, modelFormField, titleText);
        } else if (modelFormField.isRowSubmit()) {
            if (UtilValidate.isNotEmpty(titleText)) writer.append(titleText + "<br/>");
            writer.append("<input type=\"checkbox\" name=\"selectAll\" value=\"Y\" onclick=\"javascript:toggleAll(this, '");
            writer.append(modelFormField.getModelForm().getName());
            writer.append("');\"/>");
        } else {
             writer.append(titleText);
        }
    }

    /** Create an ajaxXxxx JavaScript CSV string from a list of UpdateArea objects. See
     * <code>selectall.js</code>.
     * @param updateAreas
     * @param extraParams Renderer-supplied additional target parameters
     * @param context
     * @return Parameter string or empty string if no UpdateArea objects were found
     */
    public String createAjaxParamsFromUpdateAreas(List<ModelForm.UpdateArea> updateAreas, String extraParams, Map<String, ? extends Object> context) {
        if (updateAreas == null) {
            return "";
        }
        String ajaxUrl = "";
        boolean firstLoop = true;
        for (ModelForm.UpdateArea updateArea : updateAreas) {
            if (firstLoop) {
                firstLoop = false;
            } else {
                ajaxUrl += ",";
            }
            String targetUrl = updateArea.getAreaTarget(context);
            String ajaxParams = getAjaxParamsFromTarget(targetUrl);
            if (UtilValidate.isNotEmpty(extraParams)) {
                if (ajaxParams.length() > 0 && !extraParams.startsWith("&")) {
                    ajaxParams += "&";
                }
                ajaxParams += extraParams;
            }
            ajaxUrl += updateArea.getAreaId() + ",";
            ajaxUrl += this.rh.makeLink(this.request, this.response, UtilHttp.removeQueryStringFromTarget(targetUrl));
            ajaxUrl += "," + ajaxParams;
        }
        return ajaxUrl;
    }
}
