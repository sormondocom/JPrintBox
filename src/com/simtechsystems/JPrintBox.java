package com.simtechsystems;

import java.util.*;

public class JPrintBox implements PrintBox {
    private final int INFO_COLUMN_LENGTH=20;
    private final int INFO_VALUE_LENGTH=5;
    private static final char INFO_LINE_CHARACTER='=';
    private final int ITEM_VALUE_INDENT=5;
    private final int ITEM_VALUE_POSTDENT=3;
    private final int KEY_START_POSITION=2;
    private final char ITEM_VALUE_SEPARATOR=':';
    private final String INFO_LINE_FORMATTER="%-" + INFO_COLUMN_LENGTH + "s%" + INFO_VALUE_LENGTH + "s%s";
    private static final char[] LINE_SEPARATOR_CHAR_ARRAY=System.lineSeparator().toCharArray();
    private static final String LINE_SEPARATOR_STRING=System.lineSeparator();
    private static final char WHITE_SPACE_CHARACTER=' ';
    private  static final char SIDE_BOX_LINE_CHARACTER='|';
    private static final char TOP_BOX_LINE_CHARACTER='_';
    private static final char BOTTOM_BOX_LINE_CHARACTER='_';
    private static final char MID_LINE_CHARACTER='-';
    private static final int TITLE_BAR_HEIGHT=3;
    private static final int FOOTER_BAR_HEIGHT=3;
    private StringBuilder breakString;
    private int maxKeySize;
    private int maxValueSize;
    private String title=null;
    private String footer=null;
    private Map<String,String> items;

    private int currentMaxWidth;
    private int currentMaxLength;
    private int maxBoxWidth;
    public JPrintBox() {
        maxValueSize = 0;
        maxKeySize = 0;
        currentMaxWidth=0;
        currentMaxLength=0;
        breakString = new StringBuilder();
        char[] breakBars = new char[(INFO_COLUMN_LENGTH+INFO_VALUE_LENGTH)];
        for(int i =0;i<breakBars.length;i++) {
            breakString.append(INFO_LINE_CHARACTER);
        }
    }
    @Override
    public int getMaxKeySize() {
        return maxKeySize;
    }

    @Override
    public int getMaxValueSize() {
        return maxValueSize;
    }

    @Override
    public String getItemValue(String key) {
        return (items != null && !items.isEmpty()) ? items.get(key) : null;
    }

    @Override
    public int getItemCount() {
        return (items != null && !items.isEmpty()) ? items.size() : 0;
    }

    @Override
    public boolean hasItemKey(String key) {
        return (items != null && items.containsKey(key)) ? true : false;
    }

    @Override
    public boolean hasItemValue(String value) {
        return (items != null && items.containsValue(value)) ? true : false;
    }

    @Override
    public Map<String, String> getItems() {
        return (items != null && !items.isEmpty()) ? items : null;
    }


    @Override
    public int getTitleSize() {
        return title != null ? title.length() : 0;
    }

    @Override
    public int getFooterSize() {
        return footer != null ? footer.length() : 0;
    }

    @Override
    public void addTitle(String title) {
        if(this.title == null) {
            currentMaxLength+=TITLE_BAR_HEIGHT;
        }
        this.title=title;
        if(title.length() > currentMaxWidth) {
            currentMaxWidth = title.length();
        }
    }

    @Override
    public void addItem(String key, String value) {
        if(items == null) {
            items = new LinkedHashMap<String,String>();
        }
        if(key.length() > maxKeySize) {
            maxKeySize=key.length();
        }
        if(value.length() > maxValueSize) {
            maxValueSize=value.length();
        }
        if((maxKeySize + maxValueSize) > currentMaxWidth) {

            currentMaxWidth = (maxKeySize+maxValueSize);
        }
        items.put(key,value);
        currentMaxLength++;
    }

    @Override
    public void removeItem(String key) {
        if(items != null && !items.isEmpty()) {
            if(items.containsKey(key)) {
                items.remove(key);
                currentMaxLength--;
            }
        }
        resize();
    }

    @Override
    public void addFooter(String footer) {
        if(this.footer == null) {
            currentMaxLength+=FOOTER_BAR_HEIGHT;
        }
        this.footer=footer;
        if(this.footer.length() > currentMaxWidth) {
            currentMaxWidth = this.footer.length();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof JPrintBox)) {
            return false;
        }
        if(obj==this) {
            return true;
        }
        JPrintBox jPrintBox = (JPrintBox) obj;
        if(jPrintBox.getTitle().equals(this.getTitle()) &&
            jPrintBox.getFooter().equals(this.getFooter()) &&
            jPrintBox.getItems().equals(this.getItems())) {
            return true;
        }

        return false;
    }

    @Override
    public String getTitle() {
        return (title != null) ? this.title : "";
    }

    @Override
    public String getFooter() {
        return (footer != null) ? this.footer : "";
    }

    @Override
    public String toString() {
        return (boxTitleToString()+boxItemsToString()+boxFooterToString());
    }
    @Override
    public int getCurrentMaxWidth() {
        return currentMaxWidth;
    }

    @Override
    public int getCurrentMaxLength() {
        return currentMaxLength;
    }

    @Override
    public void removeTitle() {
        if(this.title != null) {
            this.title=null;
            currentMaxLength-=TITLE_BAR_HEIGHT;
            resize();
        }
    }

    @Override
    public void removeFooter() {
        if(this.footer != null) {
            this.footer=null;
            currentMaxLength-=FOOTER_BAR_HEIGHT;
            resize();
        }
    }

    @Override
    public int getMaxItemSize() {
        return (maxKeySize+maxValueSize);
    }

    @Override
    public void resize() {
        currentMaxWidth=0;
        currentMaxLength=0;
        int currentMaxKeySize=0;
        int currentMaxValueSize=0;
        if(items != null)
        {
            for (Map.Entry<String, String> entry : items.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if(key.length() > currentMaxKeySize) {
                    currentMaxKeySize=key.length();
                }
                if(value.length() > currentMaxValueSize) {
                    currentMaxValueSize=value.length();
                }
                currentMaxLength++;
        }}
        maxKeySize=currentMaxKeySize;
        maxValueSize=currentMaxValueSize;
        currentMaxWidth=maxKeySize+maxValueSize;
        if(getTitleSize()>currentMaxWidth) {
            currentMaxWidth=getTitleSize();
        }
        if(getFooterSize()>currentMaxWidth) {
            currentMaxWidth=getFooterSize();
        }
        if(getTitleSize() > 0) {
            currentMaxLength+=TITLE_BAR_HEIGHT;
        }
        if(getFooterSize() > 0) {
            currentMaxLength+=FOOTER_BAR_HEIGHT;
        }
    }

    @Override
    public String getInfoString() {
        StringBuilder infoString = new StringBuilder();
        infoString.append(String.format("%s%s",breakString.toString(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"titleSize:",getTitleSize(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"maxKeySize:",getMaxKeySize(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"valueIndentSpace:",ITEM_VALUE_INDENT,LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"valuePostdentSpace:",ITEM_VALUE_POSTDENT,LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"maxValueSize:",getMaxValueSize(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"maxItemSize:",getMaxItemSize(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"footerSize:",getFooterSize(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"currentMaxLength:",getCurrentMaxLength(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"currentMaxWidth:",getCurrentMaxWidth(),LINE_SEPARATOR_STRING));
        infoString.append(String.format(INFO_LINE_FORMATTER,"totalBoxWidth:",getTotalBoxWidth(),LINE_SEPARATOR_STRING));
        infoString.append(String.format("%s%s",breakString.toString(),LINE_SEPARATOR_STRING));
        return infoString.toString();
    }

    @Override
    public String boxTitleToString() {
        StringBuilder titleString=null;
        if(getCurrentMaxWidth() > 0) {
            int centerPosition=retrieveCenterPosition(getTitleSize(),getTotalBoxWidth());
            titleString=new StringBuilder(TITLE_BAR_HEIGHT*(getTotalBoxWidth()+LINE_SEPARATOR_CHAR_ARRAY.length));
            char[][] titleBar = new char[TITLE_BAR_HEIGHT][getTotalBoxWidth()];
            for(int line=0;line<TITLE_BAR_HEIGHT;line++) {
                for(int column=0;column<getTotalBoxWidth();column++){
                    switch(line) {
                        case 0:
                            if(column == 0 || column == (getTotalBoxWidth()-1)) {
                                titleBar[line][column]=WHITE_SPACE_CHARACTER;
                            }
                            if(column > 0 && column < getTotalBoxWidth()-2) {
                                titleBar[line][column] = TOP_BOX_LINE_CHARACTER;
                            }
                            titleString.append(titleBar[line][column]);
                            break;
                        case 1:
                            if(column ==0 || column ==(getTotalBoxWidth()-2)) {
                                titleBar[line][column]=SIDE_BOX_LINE_CHARACTER;
                            }
                            if(column > 0 && column < getTotalBoxWidth()-2) {
                                if(column>=centerPosition && column<(centerPosition+getTitleSize())) {
                                    if(getTitleSize()>0) {
                                        titleBar[line][column] = getTitle().charAt(Math.abs(centerPosition - column));
                                    }
                                }
                                else {
                                    titleBar[line][column] = WHITE_SPACE_CHARACTER;
                                }
                            }
                            titleString.append(titleBar[line][column]);
                            break;
                        case 2:
                            if(column==0 || column==(getTotalBoxWidth()-2)) {
                                titleBar[line][column]=SIDE_BOX_LINE_CHARACTER;
                            }
                            if(column>0 && column < getTotalBoxWidth()-2) {
                                titleBar[line][column]=MID_LINE_CHARACTER;
                            }
                            titleString.append(titleBar[line][column]);
                            break;
                    }
                }
                titleString.append(LINE_SEPARATOR_STRING);
            }
        }
        return (titleString != null) ? titleString.toString() : "";
    }

    @Override
    public String boxItemsToString() {
        StringBuilder itemsString=null;
        if(getCurrentMaxWidth() > 0) {
            int valueStart=getMaxKeySize()+ITEM_VALUE_INDENT;
            int line=0;
            itemsString=new StringBuilder(getItemCount()*(getTotalBoxWidth()+LINE_SEPARATOR_CHAR_ARRAY.length));
            char[][] infoLines = new char[getItemCount()][getTotalBoxWidth()];
            if(items != null) {
                for (String key : getItems().keySet()) {
                    String value = getItemValue(key);
                    for (int column = 0; column < getTotalBoxWidth(); column++) {
                        if (column == 0 || column == (getTotalBoxWidth() - 1)) {
                            infoLines[line][column] = SIDE_BOX_LINE_CHARACTER;
                            itemsString.append(infoLines[line][column]);
                        }
                        if (column > 0 && column < (getTotalBoxWidth() - KEY_START_POSITION)) {
                            if (column > 1 && column < (key.length() + KEY_START_POSITION)) {
                                infoLines[line][column] = key.charAt((Math.abs(KEY_START_POSITION - column)));
                            } else if (column == valueStart - 2) {
                                infoLines[line][column] = ITEM_VALUE_SEPARATOR;
                            } else if (column >= valueStart && column < valueStart + value.length()) {
                                infoLines[line][column] = value.charAt((Math.abs(valueStart - column)));
                            } else {
                                infoLines[line][column] = WHITE_SPACE_CHARACTER;
                            }
                            itemsString.append(infoLines[line][column]);
                        }
                    }
                    itemsString.append(LINE_SEPARATOR_STRING);
                    line++;
                }
            }
        }

        return (itemsString != null) ? itemsString.toString() : "";
    }

    @Override
    public String boxFooterToString() {
        StringBuilder footerString = null;
        if (getCurrentMaxWidth() > 0) {
            footerString = new StringBuilder(FOOTER_BAR_HEIGHT*getTotalBoxWidth() + LINE_SEPARATOR_CHAR_ARRAY.length);
            char[][] footerLines = new char[FOOTER_BAR_HEIGHT][getTotalBoxWidth()];
            for (int line=0;line <FOOTER_BAR_HEIGHT;line++) {
                for (int column = 0; column < getTotalBoxWidth(); column++) {
                    switch(line) {
                        case 0:
                            if (column == 0 || column == (getTotalBoxWidth() - 2)) {
                                footerLines[line][column] = SIDE_BOX_LINE_CHARACTER;
                            }
                            else if(column < getTotalBoxWidth()-2) {
                                footerLines[line][column] = MID_LINE_CHARACTER;
                            }
                            footerString.append(footerLines[line][column]);
                            break;
                        case 1:
                            if (column == 0 || column == (getTotalBoxWidth() - 2)) {
                                footerLines[line][column] = SIDE_BOX_LINE_CHARACTER;
                            }
                            else if(column<(getTotalBoxWidth()-KEY_START_POSITION)) {
                                if(column>1 && column <(getFooterSize()+KEY_START_POSITION)) {
                                    footerLines[line][column] = getFooter().charAt((Math.abs(KEY_START_POSITION-column)));
                                }
                                else {
                                    footerLines[line][column] = WHITE_SPACE_CHARACTER;
                                }
                            }
                            footerString.append(footerLines[line][column]);
                            break;
                        case 2:
                            if (column == 0 || column == (getTotalBoxWidth() - 2)) {
                                footerLines[line][column] = SIDE_BOX_LINE_CHARACTER;
                            }
                            else if(column < getTotalBoxWidth()-2) {
                                footerLines[line][column] = BOTTOM_BOX_LINE_CHARACTER;
                            }
                            footerString.append(footerLines[line][column]);
                            break;
                    }
                }
                footerString.append(LINE_SEPARATOR_STRING);
            }
        }
        return (footerString != null) ? footerString.toString() : "";
    }
    @Override
    public void removeAllItems() {
        if(items != null) {
            items.clear();
            resize();
        }
    }

    private int retrieveCenterPosition(int textSize,int totalWidth) {
        int center;
        center=((totalWidth/2)-(textSize/2));
        return center;
    }
    @Override
    public int getTotalBoxWidth() {
        if(getCurrentMaxWidth() > 0) {
            return getCurrentMaxWidth() + ITEM_VALUE_INDENT + ITEM_VALUE_POSTDENT;
        }
        return 0;
    }

}
